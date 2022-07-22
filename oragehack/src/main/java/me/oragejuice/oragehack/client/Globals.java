package me.oragejuice.oragehack.client;

import me.oragejuice.eventbus.EventManager;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.render.font.CFontRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public interface Globals {

    Minecraft mc = Minecraft.getMinecraft();

    EventManager EVENT_BUS = Oragehack.INSTANCE.eventBus;

    CFontRenderer font = new CFontRenderer(new Font("Arial", Font.PLAIN, 35), true, true);


}
