package me.oragejuice.oragehack.client.features.testFeature;

import com.google.common.eventbus.Subscribe;
import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.IListener;
import me.oragejuice.oragehack.client.event.PlayerUpdateEvent;
import net.minecraft.client.Minecraft;

public class TickListener implements IListener {

    @Override
    public boolean shouldListen() {
        return Oragehack.INSTANCE.featureManager.testFeature.isEnabled();
    }

    @EventHandler
    public void onTick(PlayerUpdateEvent event){
        Minecraft.getMinecraft().player.sendChatMessage("UPDATE!!!!");
    }
}
