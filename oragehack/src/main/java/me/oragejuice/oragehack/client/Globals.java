package me.oragejuice.oragehack.client;

import me.oragejuice.eventbus.EventManager;
import me.oragejuice.oragehack.Oragehack;
import net.minecraft.client.Minecraft;

public interface Globals {

    Minecraft mc = Minecraft.getMinecraft();

    EventManager EVENT_BUS = Oragehack.INSTANCE.eventBus;
}
