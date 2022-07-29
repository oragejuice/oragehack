package me.oragejuice.oragehack.mixins.entity;


import me.oragejuice.oragehack.client.Globals;
import me.oragejuice.oragehack.client.api.event.CrystalTextureEvent;
import me.oragejuice.oragehack.client.api.event.RenderCrystalEvent;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderEnderCrystal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.ResourceLocation;
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
    }
}
