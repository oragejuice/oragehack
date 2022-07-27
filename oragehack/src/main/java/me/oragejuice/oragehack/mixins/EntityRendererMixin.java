package me.oragejuice.oragehack.mixins;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.event.PlayerUpdateEvent;
import me.oragejuice.oragehack.client.api.event.Render3dEvent;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

    @Inject(at = @At("HEAD"), method = "renderWorldPass")
    private void injectRenderWorldPost(int p_175068_1_, float p_175068_2_, long p_175068_3_, CallbackInfo ci) {
        Oragehack.INSTANCE.eventBus.post(new Render3dEvent(p_175068_2_));
    }

    // .endStartSection("hand");
}
