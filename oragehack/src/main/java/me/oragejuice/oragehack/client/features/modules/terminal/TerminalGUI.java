package me.oragejuice.oragehack.client.features.modules.terminal;

import me.oragejuice.oragehack.Oragehack;
import net.minecraft.client.gui.GuiScreen;

import java.awt.*;
import java.io.IOException;

public class TerminalGUI extends GuiScreen {

    TerminalWindow terminalWindow = new TerminalWindow(10,10,500,500);

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        //this.drawGradientRect(1,1,20,20, Color.RED.getRGB(), Color.ORANGE.getRGB());
        terminalWindow.draw(mouseX, mouseY, partialTicks);
        mc.fontRenderer.drawStringWithShadow("oragehack", 10,10, Color.WHITE.getRGB());
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        terminalWindow.keyTyped(typedChar, keyCode);
        Oragehack.LOGGER.info(typedChar);
    }


    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        terminalWindow.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        terminalWindow.mouseReleased(mouseX, mouseY, state);
    }

}
