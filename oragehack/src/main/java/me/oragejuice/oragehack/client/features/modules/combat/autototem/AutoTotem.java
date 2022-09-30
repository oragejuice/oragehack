package me.oragejuice.oragehack.client.features.modules.combat.autototem;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.StopWatch;
import me.oragejuice.oragehack.client.api.event.PacketEvent;
import me.oragejuice.oragehack.client.api.event.PlayerUpdateEvent;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.settings.premade.BooleanSetting;
import me.oragejuice.oragehack.client.api.settings.premade.EnumSetting;
import me.oragejuice.oragehack.client.api.settings.premade.IntSetting;
import net.minecraft.init.Items;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraft.network.play.server.SPacketSetSlot;
import java.util.Optional;

public class AutoTotem extends Feature {

    IntSetting delay = new IntSetting("delay", 0, 0, 100);
    StopWatch timer = new StopWatch();
    BooleanSetting sprint = new BooleanSetting("sprint", true);
    BooleanSetting interactions = new BooleanSetting("interactions", false);
    EnumSetting<Mode> modeSetting = new EnumSetting<>("mode", Mode.TOTEM);

    public AutoTotem() {
        super("AutoTotem", Categories.COMBAT, 0);
        this.registerSettings(delay, sprint, modeSetting);
    }

    @EventHandler
    public void onPacket(PacketEvent event) {
        //the packet is updating one of inventory slots
        if (event.getPacket() instanceof SPacketSetSlot) {
            doUpdate();
        }
    }

    @EventHandler
    public void onUpdate(PlayerUpdateEvent event) {
        doUpdate();
    }

    private void doUpdate() {
        // no totem in offhand
        if (!(mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING)) {
            Optional<Integer> slot = getPredicateSlot(item -> item.getItem() == Items.TOTEM_OF_UNDYING);
            slot.ifPresent(i -> {
                boolean wasSprinting = mc.player.isSprinting();
                //NCP doesn't flag inv move if in the air
                if (mc.player.onGround || wasSprinting || !mc.player.isInWater() || !mc.player.isInLava()) {
                    mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                }
                //if our hand is active NCP
                //TODO if its only got 350ms of interaction left dont cancel
                if (mc.player.isHandActive() && interactions.getValue()) {
                    mc.player.resetActiveHand();
                }
                moveItems(i);
                if (wasSprinting)
                    mc.player.connection.sendPacket(new CPacketEntityAction(mc.player, CPacketEntityAction.Action.START_SPRINTING));

            });
        }

        // if other item in offhand we should return it to an empty slot
        // also check for timer to not flag fastClick
        if (!mc.player.inventory.getItemStack().isEmpty() && timer.passed(delay.getValue())) {
            getPredicateSlot(ItemStack::isEmpty).ifPresent(emptySlot -> {
                mc.playerController.windowClick(0, emptySlot,0, ClickType.PICKUP, mc.player );
            });
        }
    }

    private void moveItems(int slot){
        //if we haven't yet picked up an item
        if (mc.player.inventory.getItemStack().isEmpty()) {
            // picking up the item
            mc.playerController.windowClick(0, slot < 9 ? slot + 36 : slot, 0, ClickType.PICKUP, mc.player);
            //we need to reset the timer as we've started the autoTotem action
            timer.reset();
        }

        if (timer.passed(delay.getValue())) {
            //move to offhand
            mc.playerController.windowClick(0, 45, 0, ClickType.PICKUP, mc.player);
            timer.reset();
        }

    }

    private Optional<Integer> getPredicateSlot(ItemPredicate predicate) {
        //loop over inventory missing out the
        for (int i = 9; i <= 44; i++) {
            if (predicate.compare(mc.player.inventoryContainer.getSlot(i).getStack())) {
                return Optional.of(i);
            }
        }
        return Optional.empty();
    }


    enum Mode {
        TOTEM,
        GAPPLE,
        CRYSTAL

    }
}
