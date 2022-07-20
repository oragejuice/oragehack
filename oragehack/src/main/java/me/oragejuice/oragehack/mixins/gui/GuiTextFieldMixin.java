package me.oragejuice.oragehack.mixins.gui;


import jdk.internal.org.objectweb.asm.Opcodes;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.render.DrawHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.RenderHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.awt.*;
import java.util.Arrays;

@Mixin(GuiTextField.class)
public abstract class GuiTextFieldMixin {


    @Shadow public int x;
    @Shadow public int y;
    @Shadow public int width;
    @Shadow public int height;
    @Shadow public abstract void setEnableBackgroundDrawing(boolean enableBackgroundDrawingIn);
    @Shadow private String text;

    @Shadow private boolean isEnabled;
    @Shadow private int enabledColor;
    @Shadow private int disabledColor;
    @Shadow private int cursorPosition;
    @Shadow private int lineScrollOffset;
    @Shadow private boolean enableBackgroundDrawing;
    @Shadow public abstract int getWidth();


    @Shadow public abstract boolean textboxKeyTyped(char typedChar, int keyCode);

    String suggestions = "";
    float spacing = 0;


    @Inject(method = "drawTextBox",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiTextField;getEnableBackgroundDrawing()Z",
                    shift = At.Shift.BEFORE
            ))
    public void drawBox(CallbackInfo ci){
        //if is command and starts with grave ( ` )
        if(this.text.startsWith("`")){
            DrawHelper.drawRect(this.x - 1, this.y - 1, this.x + this.width - 1, this.y + this.height - 1, Color.ORANGE);


            //its called pasting dad, its something normal
            int i = this.isEnabled ? this.enabledColor : this.disabledColor;
            int j = this.cursorPosition - this.lineScrollOffset;
            String s = Minecraft.getMinecraft().fontRenderer.trimStringToWidth(this.text.substring(this.lineScrollOffset), getWidth());
            boolean flag = j >= 0 && j <= s.length();
            int l = this.enableBackgroundDrawing ? this.x + 4 : this.x;
            int i1 = this.enableBackgroundDrawing ? this.y + (this.height - 8) / 2 : this.y;

            if (!s.isEmpty())
            {
                String s1 = flag ? s.substring(0, j) : s;


                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(suggestions, (float)l + spacing, (float)i1, Color.ORANGE.getRGB());
            }

        }
    }

    /*
    //TODO BUG fix what happens if no suggestions are found :|
    @Inject(method = "writeText", at = @At("HEAD"))
    public void keyTypedHook(String textToWrite, CallbackInfo ci){
        if(text.startsWith("`")) {
            String command = text.replaceFirst("`", "");
            command = command.trim();
            String[] p = command.split(" ");

            int lastIndex = command.lastIndexOf(" ");
            if (lastIndex != -1) {
                //multiple words
                String firstWords = text.substring(0, lastIndex+1);
                Oragehack.LOGGER.info("firstwords: {}: \t command: {}", firstWords, command);
                spacing = Minecraft.getMinecraft().fontRenderer.getStringWidth("`" + firstWords + " ");

            } else {
                //if there is only 1 word
                //suggestions = "`" + Oragehack.INSTANCE.commandDispatcher.getSuggestion(command)[0];
                spacing = Minecraft.getMinecraft().fontRenderer.getStringWidth("`");
            }

            if (p.length >= 1 && text.endsWith(" ")){
                spacing = Minecraft.getMinecraft().fontRenderer.getStringWidth(text);

            }

            if (!(Oragehack.INSTANCE.commandDispatcher.getSuggestion(command).length == 0)){
                suggestions = Oragehack.INSTANCE.commandDispatcher.getSuggestion(command)[0];
            }
        }
    }

     */

    @Inject(method = {"textboxKeyTyped"}, at = @At("RETURN"))
    public void textBoxKeyTypedHook(char typedChar, int keyCode, CallbackInfoReturnable<Boolean> cir){
        if(text.startsWith("`")){
            String[] s = new String[]{};
            String command = text.replaceFirst("`", "");
            int i = text.lastIndexOf(" ");
            if(i != -1){
                // space found, must be multiple words
                Oragehack.LOGGER.info("text: {} \t index: {} \t subString: {}", text, i, text.substring(0,i));
                s = Oragehack.INSTANCE.commandDispatcher.getSuggestion(command);
                spacing = Minecraft.getMinecraft().fontRenderer.getStringWidth("`" + text.substring(0,i));
            } else {
                s = Oragehack.INSTANCE.commandDispatcher.getSuggestion(command);
                spacing = Minecraft.getMinecraft().fontRenderer.getStringWidth("`");
            }
            if (s.length > 0){
                suggestions = s[0];
            }
        }
    }

}
