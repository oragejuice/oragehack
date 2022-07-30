package me.oragejuice.oragehack.mixins.entity;


import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.Globals;
import me.oragejuice.oragehack.client.api.event.CrystalTextureEvent;
import me.oragejuice.oragehack.client.api.event.RenderCrystalEvent;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderEnderCrystal.class)
public class RenderEntityEnderCrystalMixin implements Globals {

    @Final
    @Shadow
    private static ResourceLocation ENDER_CRYSTAL_TEXTURES;

    @Final
    @Shadow
    private ModelBase modelEnderCrystal;

    @Final
    @Shadow
    private ModelBase modelEnderCrystalNoBase;


    @Redirect(method = "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    private void doRender(ModelBase modelBase, Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        RenderCrystalEvent.RenderCrystalPreEvent renderCrystalEvent = new RenderCrystalEvent.RenderCrystalPreEvent(modelBase, entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        EVENT_BUS.post(renderCrystalEvent);

        if (!renderCrystalEvent.isCancelled()) {
            modelBase.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        }

        CrystalTextureEvent crystalTextureEvent = new CrystalTextureEvent();
        EVENT_BUS.post(crystalTextureEvent);
    }

    @Inject(method = "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V", at = @At("RETURN"), cancellable = true)
    public void doRender(EntityEnderCrystal entityEnderCrystal, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo info) {
        RenderCrystalEvent.RenderCrystalPostEvent renderCrystalEvent = new RenderCrystalEvent.RenderCrystalPostEvent(modelEnderCrystal, modelEnderCrystalNoBase, entityEnderCrystal, x, y, z, entityYaw, partialTicks);
        EVENT_BUS.post(renderCrystalEvent);

        if (renderCrystalEvent.isCancelled()) {
            info.cancel();
        }

        if(Oragehack.INSTANCE.featureManager.crystalChams.selective.getValue()){
            float f = (float)entityEnderCrystal.innerRotation + partialTicks;
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)x, (float)y, (float)z);
            //this.bindTexture(ENDER_CRYSTAL_TEXTURES);
            mc.getRenderManager().renderEngine.bindTexture(ENDER_CRYSTAL_TEXTURES);
            float f1 = MathHelper.sin(f * 0.2F) / 2.0F + 0.5F;
            f1 = f1 * f1 + f1;
            modelEnderCrystalNoBase.render(entityEnderCrystal, 0.0F, f * 3.0F, f1 * 0.2F, 0.0F, 0.0F, 0.0625F);
            GlStateManager.popMatrix();

        }
    }
}
