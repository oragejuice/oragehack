package me.oragejuice.oragehack.mixins.gui;


import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.CommandTabCompleter;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiTextField;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiChat.class)
public abstract class GuiChatMixin {


    @Shadow protected GuiTextField inputField;
    CommandTabCompleter commandTabCompleter;

    @Inject(method = "initGui", at = @At("TAIL"))
    public void initGui(CallbackInfo ci){
        commandTabCompleter = new CommandTabCompleter(this.inputField, false);
    }

    //for tab completion
    @Inject(method = "keyTyped",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/util/TabCompleter;complete()V",
                    shift = At.Shift.BEFORE
            ))
    public void keyTypedHook(char typedChar, int keyCode, CallbackInfo ci){
        if(inputField.getText().startsWith("`")){
            commandTabCompleter.setCompletions(Oragehack.INSTANCE.commandDispatcher.getSuggestion(inputField.getText().replaceFirst("`", "")));
            commandTabCompleter.complete();
        }
    }
}
