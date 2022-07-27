package me.oragejuice.oragehack.mixins;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Render.class)
public abstract class MixinRender {

    @Shadow
    protected abstract <T extends Entity> boolean bindEntityTexture(T entity);

}
