package me.oragejuice.oragehack.client.features.testFeature;

import com.google.common.eventbus.Subscribe;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.event.IListener;
import me.oragejuice.oragehack.client.event.PlayerUpdateEvent;
import net.minecraft.client.Minecraft;

public class TickListener implements IListener {

    @Override
    public boolean shouldListen() {
        return Oragehack.INSTANCE.featureManager.testFeature.isEnabled();
    }

    @Subscribe
    public void onTick(PlayerUpdateEvent event){
        Minecraft.getMinecraft().player.sendChatMessage("UPDATE!!!!");
    }
}
