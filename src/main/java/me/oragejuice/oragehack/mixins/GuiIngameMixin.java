package me.oragejuice.oragehack.mixins;


import me.oragejuice.oragehack.SimpleTweaker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(GuiIngame.class)
public abstract class GuiIngameMixin {

    @Shadow @Final protected Minecraft mc;

    @Inject(method = "renderGameOverlay", at = @At("HEAD"))
    public void onRenderGameOverlay(float partialTicks, CallbackInfo ci){
        mc.fontRenderer.drawStringWithShadow("oragehack", 5, 5, Color.ORANGE.getRGB());
        SimpleTweaker.LOGGER.info("Hello!");
    }
}
