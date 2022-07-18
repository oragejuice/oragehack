package me.oragejuice.oragehack.client.features.watermark;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.event.RenderOverlayEvent;

import java.awt.*;

public class Watermark extends Feature {

    public Watermark() {
        super("Watermark");
        this.setEnabled(true);
    }

    @EventHandler
    public void onRender2D(RenderOverlayEvent event) {
        mc.fontRenderer.drawStringWithShadow("oragehack-0.1b", 1,1, Color.ORANGE.getRGB());
    }
}
