package me.oragejuice.oragehack.mixins.entity;

import me.oragejuice.oragehack.client.Globals;
import me.oragejuice.oragehack.client.api.rotation.Rotation;
import me.oragejuice.oragehack.client.api.rotation.RotationHandler;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderPlayer.class)
public abstract class RenderPlayerMixin implements Globals {

    private float renderPitch, renderYaw, renderHeadYaw, prevRenderHeadYaw, prevRenderPitch, prevRenderYawOffset, prevPrevRenderYawOffset;

    @Inject(method = "doRender", at = @At("HEAD"))
    private void doRenderPre(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo info) {
        if (mc.player.equals(entity)) {
            prevRenderHeadYaw = entity.prevRotationYawHead;
            prevRenderPitch = entity.prevRotationPitch;
            renderPitch = entity.rotationPitch;
            renderYaw = entity.rotationYaw;
            renderHeadYaw = entity.rotationYawHead;
            prevPrevRenderYawOffset = entity.prevRenderYawOffset;
            prevRenderYawOffset = entity.renderYawOffset;


            float sYaw = RotationHandler.serverYaw;
            float sPitch = RotationHandler.serverPitch;

            //if (RotationHandler.isSpoofing()) {
                entity.rotationYaw = sYaw;
                entity.rotationYawHead = sYaw;
                entity.prevRotationYawHead = sYaw;
                entity.prevRenderYawOffset = sYaw;
                entity.renderYawOffset = sYaw;
                entity.rotationPitch = sPitch;
                entity.prevRotationPitch = sPitch;
            //}
        }
    }

    @Inject(method = "doRender", at = @At("RETURN"))
    private void doRenderPost(AbstractClientPlayer entity, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo info) {
        if (mc.player.equals(entity)) {
            entity.rotationPitch = renderPitch;
            entity.rotationYaw = renderYaw;
            entity.rotationYawHead = renderHeadYaw;
            entity.prevRotationYawHead = prevRenderHeadYaw;
            entity.prevRotationPitch = prevRenderPitch;
            entity.renderYawOffset = prevRenderYawOffset;
            entity.prevRenderYawOffset = prevPrevRenderYawOffset;
        }
    }

}
