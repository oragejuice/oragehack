package me.oragejuice.oragehack.client.features.modules.render;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.event.Render3dEvent;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.render.RenderHelper;
import me.oragejuice.oragehack.mixins.RenderManagerAccessor;
import net.minecraft.util.math.AxisAlignedBB;

import java.awt.*;

// not the best code, but it works
// this is just to show oragejuice that my render hook works
public class TestESP extends Feature {
    public TestESP() {
        super("TestESP", Categories.RENDER, 1);
    }

    @EventHandler
    public void onRenderWorld(Render3dEvent event) {
        AxisAlignedBB bb = new AxisAlignedBB(mc.player.getPosition())
                .offset(-((RenderManagerAccessor) mc.getRenderManager()).getRenderPosX(),
                        -((RenderManagerAccessor) mc.getRenderManager()).getRenderPosY(),
                        -((RenderManagerAccessor) mc.getRenderManager()).getRenderPosZ());

        RenderHelper.begin3D();
        RenderHelper.drawFilledBox(bb, new Color(255, 255, 255, 80).getRGB());
        RenderHelper.end3D();
    }
}
