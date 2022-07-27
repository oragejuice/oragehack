package me.oragejuice.oragehack.mixins;

import me.oragejuice.oragehack.client.api.render.OutlineUtils;
import me.oragejuice.oragehack.client.features.modules.Esp;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.awt.*;

@Mixin(RenderLivingBase.class)
public abstract class MixinRenderLivingBase extends Render {

    @Shadow protected ModelBase mainModel;

    protected MixinRenderLivingBase(RenderManager p_i46179_1_) {
        super(p_i46179_1_);
    }

    /**
     * @author Jenni
     */
    @Overwrite
    protected <T extends EntityLivingBase> void renderModel(T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor) {
        boolean flag = !entitylivingbaseIn.isInvisible();
        boolean flag1 = !flag && !entitylivingbaseIn.isInvisibleToPlayer(Minecraft.getMinecraft().player);

        if (flag || flag1) {
            if (!this.bindEntityTexture(entitylivingbaseIn)) {
                return;
            }

            if (flag1) {
                GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }

            if(Esp.instance.isEnabled()) {
                Integer cl = Esp.instance.getColor(entitylivingbaseIn);
                if(cl != null) {
                    final Color color = new Color(cl);
                    OutlineUtils.setColor(color);
                    OutlineUtils.renderOne(Esp.instance.outlineWidth.getValue());
                    this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    OutlineUtils.setColor(color);
                    OutlineUtils.renderTwo();
                    this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    OutlineUtils.setColor(color);
                    OutlineUtils.renderThree();
                    this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    OutlineUtils.setColor(color);
                    OutlineUtils.renderFour(color);
                    this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
                    OutlineUtils.setColor(color);
                    OutlineUtils.renderFive();
                }
            }
            this.mainModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);

            if (flag1) {
                GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
    }

}
