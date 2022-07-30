package me.oragejuice.oragehack.client.features.modules.render.crystalChams;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.IListener;
import me.oragejuice.oragehack.client.api.event.RenderCrystalEvent;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glPopMatrix;

public class CrystalChams extends Feature {


    public CrystalChams() {
        super("CrystalChams", Categories.RENDER);
        registerSettings(walls, transparent, blend, lighting, width, depth, colour, selective);
    }

    GenericSetting<Boolean> walls = new GenericSetting<>("walls", true);
    GenericSetting<Boolean> transparent = new GenericSetting<>("transparent", true);
    GenericSetting<Boolean> blend = new GenericSetting<>("blend", true);
    GenericSetting<Boolean> lighting = new GenericSetting<>("lighting", true);
    GenericSetting<Float> width = new GenericSetting<>("width", 1F);
    GenericSetting<Float> animationSpeed = new GenericSetting<>("animation_speed", 1F);
    GenericSetting<Boolean> depth = new GenericSetting<>("depth", true);
    GenericSetting<Integer> colour = new GenericSetting<>("colour", 0xF0F0F0);
    public GenericSetting<Boolean> selective = new GenericSetting<>("selective", true);


    @EventHandler
    public void onRenderCrystalPre(RenderCrystalEvent.RenderCrystalPreEvent event) {
        // cancel vanilla model rendering
        event.cancel();
    }

    @EventHandler
    public void onRenderCrystalPost(RenderCrystalEvent.RenderCrystalPostEvent event) {
        // make the model transparent
        if (transparent.getValue()) {
            GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
        }

        glPushMatrix();
        glPushAttrib(GL_ALL_ATTRIB_BITS);

        // model rotations
        float rotation = (event.getEntityEnderCrystal().innerRotation + event.getPartialTicks()) * animationSpeed.getValue();
        float rotationMoved = MathHelper.sin(rotation * 0.2F) / 2 + 0.5F;
        rotationMoved += StrictMath.pow(rotationMoved, 2);

        // scale and translate the model
        glTranslated(event.getX(), event.getY(), event.getZ());
        //glScaled(scale.getValue(), scale.getValue(), scale.getValue());

        // remove the texture
        glDisable(GL_TEXTURE_2D);

        // blend the textures
        if (blend.getValue()) {
            glEnable(GL_BLEND);
        }

        // remove lighting
        if (lighting.getValue()) {
            glDisable(GL_LIGHTING);
        }

        // remove visual depth
        if (depth.getValue()) {
            glDepthMask(false);
        }

        // remove depth
        if (walls.getValue()) {
            glDisable(GL_DEPTH_TEST);
        }

        // update the rendering mode of the polygons
        //glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        // anti-alias
        glEnable(GL_LINE_SMOOTH);
        glHint(GL_LINE_SMOOTH_HINT, GL_NICEST);
        glLineWidth(width.getValue().floatValue());
        Color color = new Color(colour.getValue());


        // color the model (walls)
        glColor4d(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 0.2);

        // render the model
        if (event.getEntityEnderCrystal().shouldShowBottom()) {
            event.getModelBase().render(event.getEntityEnderCrystal(), 0, rotation * 3, rotationMoved * 0.2F, 0, 0, 0.0625F);
        }

        else {
            event.getModelNoBase().render(event.getEntityEnderCrystal(), 0, rotation * 3, rotationMoved * 0.2F, 0, 0, 0.0625F);
        }

        // re-enable depth
        if (walls.getValue()) {
            glEnable(GL_DEPTH_TEST);
        }

        // change to outline polygon mode for wire and model
        glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
        glColor4d(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F,  1);

        if (event.getEntityEnderCrystal().shouldShowBottom()) {
            event.getModelBase().render(event.getEntityEnderCrystal(), 0, rotation * 3, rotationMoved * 0.2F, 0, 0, 0.0625F);
        }

        else {
            event.getModelNoBase().render(event.getEntityEnderCrystal(), 0, rotation * 3, rotationMoved * 0.2F, 0, 0, 0.0625F);
        }

        // reset lighting
        if (lighting.getValue()) {
            glEnable(GL_LIGHTING);
        }

        // reset visual depth
        if (depth.getValue()) {
            glDepthMask(true);
        }

        // reset blend
        if (blend.getValue()) {
            glDisable(GL_BLEND);
        }

        // change to outline polygon mode for wire and model
        if (walls.getValue()) {
            glEnable(GL_DEPTH_TEST);
        }
        // reset lighting
        glEnable(GL_LIGHTING);// reset scale
        glScaled(1 , 1 , 1 );

        glPopAttrib();
        glPopMatrix();
    }


}
