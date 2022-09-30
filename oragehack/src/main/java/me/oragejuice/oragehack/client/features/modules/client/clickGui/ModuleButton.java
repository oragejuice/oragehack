package me.oragejuice.oragehack.client.features.modules.client.clickGui;

import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.render.DrawHelper;
import me.oragejuice.oragehack.client.api.render.font.CFontRenderer;
import me.oragejuice.oragehack.client.api.render.gui.AbstractRect;
import me.oragejuice.oragehack.client.features.modules.client.clickGui.settingButtons.EnabledButton;

import java.awt.*;

public class ModuleButton extends AbstractRect {

    static CFontRenderer fontRenderer = new CFontRenderer(new Font("Tahoma", Font.PLAIN, 20), true, true);

    Feature feature;
    EnabledButton enabledButton;

    public ModuleButton(Feature feature, int x, int y, int width, int height) {
        this.feature = feature;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        enabledButton = new EnabledButton(feature, x+width-35, y+5);
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        DrawHelper.drawLine(x, y+height - 2, x+width, y+height - 2, Color.DARK_GRAY);
        fontRenderer.drawString(feature.getName(),x + 3, y + 15 - (fontRenderer.getHeight() / 2), Color.BLACK);
        enabledButton.updatePosition(x+width-35, y+5);
        enabledButton.draw(mouseX,  mouseY, partialTicks);

    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int state) {
        if(isInside(mouseX, mouseY)){
            //feature.toggle();
        }
        enabledButton.onMouseClicked(mouseX, mouseY, state);
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY, int state) {
    }
}
