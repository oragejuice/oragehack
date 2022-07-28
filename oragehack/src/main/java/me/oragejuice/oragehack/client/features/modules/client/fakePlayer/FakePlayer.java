package me.oragejuice.oragehack.client.features.modules.client.fakePlayer;

import com.mojang.authlib.GameProfile;
import me.oragejuice.oragehack.client.api.IListener;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import net.minecraft.client.entity.EntityOtherPlayerMP;

import java.util.UUID;

public class FakePlayer extends Feature {

    public FakePlayer() {
        super("Fake Player", Categories.COMBAT, 11);
    }

    private EntityOtherPlayerMP fakePlayer;

    @Override
    public void onEnable(){
        super.onEnable();
        if(mc.player == null || mc.world == null) return;

        GameProfile profile = new GameProfile(UUID.fromString("069a79f4-44e9-4726-a5be-fca90e38aaf5"), "Notch");
        fakePlayer = new EntityOtherPlayerMP(mc.world, profile);
        fakePlayer.setPosition(mc.player.posX, mc.player.posY, mc.player.posZ);
        mc.world.addEntityToWorld(-420, fakePlayer);
    }

    @Override
    public void onDisable() {
        if (mc.world.getLoadedEntityList().contains(fakePlayer)) {
            mc.world.removeEntity(fakePlayer);
        }
    }
}
