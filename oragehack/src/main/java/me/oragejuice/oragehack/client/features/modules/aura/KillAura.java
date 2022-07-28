package me.oragejuice.oragehack.client.features.modules.aura;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.event.PlayerUpdateEvent;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.rotation.Rotation;
import me.oragejuice.oragehack.client.api.rotation.RotationHandler;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;

import java.util.Comparator;
import java.util.Optional;

public class KillAura extends Feature {

    public KillAura() {
        super("Aura", Categories.COMBAT, 4);
    }
    Comparator<Entity> compByDistance = Comparator.comparingDouble(e -> mc.player.getDistance(e));


    @EventHandler
    public void onUpdate(PlayerUpdateEvent event){
        Optional<Entity> closet = mc.world.getLoadedEntityList().stream().filter(entity -> entity instanceof EntityOtherPlayerMP).min(compByDistance);
        if (closet.isPresent()) {
            //if (closet.get().getDistance(mc.player) < 5.0F) {
                EntityLivingBase target = (EntityLivingBase) closet.get();
            RotationHandler.postRotation(new Rotation(target.getPositionVector(), 3));
                if (mc.player.getCooledAttackStrength(0F) == 1F
                        && mc.player.getHeldItemMainhand().getItem() instanceof ItemSword ) {
                    mc.playerController.attackEntity(mc.player, target);
                    mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            //}
        }
    }
}
