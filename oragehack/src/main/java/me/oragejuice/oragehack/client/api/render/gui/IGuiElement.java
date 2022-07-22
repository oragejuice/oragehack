package me.oragejuice.oragehack.client.api.render.gui;

public interface IGuiElement {

    void draw(int mouseX, int mouseY, float partialTicks);

    void onMouseClicked(int mouseX, int mouseY, int state);

    void onMouseReleased(int mouseX, int mouseY, int state);

    void onKeyTyped(char typedChar, int keyCode);
}
