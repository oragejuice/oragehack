package me.oragejuice.oragehack.client.features.modules.clickGui;

import me.oragejuice.oragehack.client.api.feature.Categories;
import net.minecraft.client.gui.GuiScreen;

import java.io.IOException;

public class ClickGui extends GuiScreen {

    Panel[] panels = new Panel[Categories.values().length];

    public ClickGui() {
        int x = 10;
        for (int i = 0; i < Categories.values().length; i++) {
            panels[i] = new Panel(Categories.values()[i], x += 160);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks){
        for (Panel panel : panels) {
            panel.draw(mouseX, mouseY, partialTicks);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int state){
        for (Panel panel : panels) {
            panel.onMouseClicked(mouseX, mouseY, state);
        }
    }
    @Override
    public void mouseReleased(int mouseX, int mouseY, int state){
        for (Panel panel : panels) {
            panel.onMouseReleased(mouseX, mouseY, state);
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode){
        for (Panel panel : panels) {
            panel.onKeyTyped(typedChar, keyCode);
        }
        try {
            super.keyTyped(typedChar, keyCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }





}
