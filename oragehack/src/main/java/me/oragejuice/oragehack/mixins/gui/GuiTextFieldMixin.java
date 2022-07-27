package me.oragejuice.oragehack.mixins.gui;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.render.DrawHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiTextField;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
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
    int spacing = 0;
    int i = 0;


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
            int l = this.x;
            int i1 = this.enableBackgroundDrawing ? this.y + (this.height - 8) / 2 : this.y;

            if (!s.isEmpty())
            {
                String s1 = flag ? s.substring(0, j) : s;

                Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(suggestions, l + spacing, (float)i1, Color.DARK_GRAY.getRGB());
            }

        }
    }


    @Inject(method = {"textboxKeyTyped"}, at = @At("RETURN"))
    public void textBoxKeyTypedHook(char typedChar, int keyCode, CallbackInfoReturnable<Boolean> cir){
        if(text.startsWith("`")){

            String[] s = new String[]{};
            String command = text.replaceFirst("`", "");
            i = text.lastIndexOf(" ");
            if(i != -1){
                // space found, must be multiple words
                //Oragehack.LOGGER.info("text: {} \t index: {} \t subString: {}", text, i, text.substring(0,i));
                s = Oragehack.INSTANCE.commandDispatcher.getSuggestion(command);
                spacing = Minecraft.getMinecraft().fontRenderer.getStringWidth("`" + text.substring(0,i)) + 1;
            } else {
                s = Oragehack.INSTANCE.commandDispatcher.getSuggestion(command);
                spacing = Minecraft.getMinecraft().fontRenderer.getStringWidth("`");
            }
            if (s.length > 0){
                suggestions = s[0];
            }
        }

        /* TODO tab completion for suggestions
        if(keyCode == Keyboard.KEY_TAB){
            i = text.lastIndexOf(" ");
            if(!suggestions.isEmpty() && i != -1){
                this.text = text.substring(0,i-1) + " " + suggestions;
            }
        }

         */
    }

}
