package me.oragejuice.oragehack.mixins;


import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.event.PlayerUpdateEvent;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.client.event.GuiScreenEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSPMixin {


    @Inject(method = "onUpdate", at = @At("HEAD"))
    public void onUpdate(CallbackInfo ci) {
        Oragehack.INSTANCE.eventBus.post(new PlayerUpdateEvent());
    }

}
