package me.oragejuice.oragehack.client.features.modules.terminal;

import net.minecraft.client.gui.Gui;

public class AbstractWindow extends Gui implements IWindow {

    int x;
    int y;
    int w;
    int h;

    public AbstractWindow(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {

    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {

    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {

    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {

    }

    public boolean isInside(int mx, int my){
        return x < mx && mx < x+w && y < my && my < y+h;
    }

    public boolean isInside(int mx, int my, int x, int y, int w, int h){
        return x < mx && mx < x+w &&
                y < my && my < y+h;
    }

}
