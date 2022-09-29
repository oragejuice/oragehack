package me.oragejuice.oragehack.mixins;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.event.Render3dEvent;
import net.minecraft.client.renderer.EntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityRenderer.class)
public class EntityRendererMixin {

//    @Inject(method = "renderWorldPass",
//            at = @At(value = "HEAD"))
//    private void injectRenderWorldPost(int p_175068_1_, float p_175068_2_, long p_175068_3_, CallbackInfo ci) {
//        Oragehack.INSTANCE.eventBus.post(new Render3dEvent(p_175068_2_));
//    }

    @Inject(method = "renderWorldPass",
            at = @At(
                    value = "INVOKE",
                    shift = At.Shift.BEFORE,
                    target = "Lnet/minecraft/profiler/Profiler;endStartSection(Ljava/lang/String;)V",
                    ordinal = 19)) // this.mc.profiler.endStartSection("hand");
    private void renderWorldPass$preEndStartSection(int p_175068_1_, float p_175068_2_, long p_175068_3_, CallbackInfo info) {
        Oragehack.INSTANCE.eventBus.post(new Render3dEvent(p_175068_2_));
    }
}
