package me.oragejuice.oragehack.client.features.modules.terminal;

import java.io.IOException;

public interface IWindow {

    void draw(int mouseX, int mouseY, float partialTicks);

    void keyTyped(char typedChar, int keyCode);

    void mouseClicked(int mouseX, int mouseY, int mouseButton);

    void mouseReleased(int mouseX, int mouseY, int state);

}