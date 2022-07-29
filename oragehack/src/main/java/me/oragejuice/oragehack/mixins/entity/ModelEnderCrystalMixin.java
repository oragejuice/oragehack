package me.oragejuice.oragehack.mixins.entity;

import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ModelEnderCrystal.class)
public abstract class ModelEnderCrystalMixin {

    @Inject(method = "render", at = @At("HEAD"))
    public void renderHook(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, CallbackInfo ci){
        ageInTicks = ageInTicks * 0.1F;
    }
}
