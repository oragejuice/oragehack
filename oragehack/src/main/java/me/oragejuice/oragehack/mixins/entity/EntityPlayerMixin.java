package me.oragejuice.oragehack.mixins.entity;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.event.PlayerUpdateEvent;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayer.class)
public abstract class EntityPlayerMixin {

    @Inject(method = "updateSize", at = @At("TAIL"))
    public void updateHook(CallbackInfo ci){
        Oragehack.INSTANCE.eventBus.post(new PlayerUpdateEvent());
    }
}
