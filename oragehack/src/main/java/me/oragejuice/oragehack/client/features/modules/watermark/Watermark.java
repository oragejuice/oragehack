package me.oragejuice.oragehack.client.features.modules.watermark;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import me.oragejuice.oragehack.client.api.event.RenderOverlayEvent;

import java.awt.*;

public class Watermark extends Feature {

    GenericSetting<Boolean> watermarktestBoolean = new GenericSetting<Boolean>("wat", false);

    public Watermark() {
        super("Watermark", Categories.RENDER);
        this.setEnabled(true);

        this.registerSettings(watermarktestBoolean);
    }

    @EventHandler
    public void onRender2D(RenderOverlayEvent event) {
        mc.fontRenderer.drawStringWithShadow("oragehack-0.1b", 1,1, Color.ORANGE.getRGB());
    }
}
