package me.oragejuice.oragehack.mixins;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.event.KeypressEvent;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyBinding.class)
public abstract class KeyBindingMixin {

    @Inject(method = "onTick", at = @At("HEAD"))
    private static void onTick(int keyCode, CallbackInfo ci){
        Oragehack.INSTANCE.eventBus.post(new KeypressEvent(keyCode));
        //Oragehack.LOGGER.info(Keyboard.getKeyName(keyCode));
    }
}
