package me.oragejuice.oragehack.mixins;


import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.event.PlayerUpdateEvent;
import net.minecraft.client.entity.EntityPlayerSP;
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

    @Inject(method = "sendChatMessage", at = @At("HEAD"))
    public void onChatSend(String message, CallbackInfo ci){
        if(message.startsWith(";")) {
            Oragehack.LOGGER.info("COMMAND DISPATCHED");
            return;
        }

    }

}
