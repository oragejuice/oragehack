package me.oragejuice.oragehack.mixins;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.event.RenderOverlayEvent;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GuiNewChat.class)
public abstract class GuiNewChatMixin {

    @Inject(method = "drawChat", at = @At("HEAD"))
    public void drawChat(int updateCounter, CallbackInfo ci){
        Oragehack.INSTANCE.eventBus.post(new RenderOverlayEvent());
    }
}
