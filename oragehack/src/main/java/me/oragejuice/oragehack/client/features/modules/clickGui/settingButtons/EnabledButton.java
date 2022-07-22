package me.oragejuice.oragehack.client.features.modules.clickGui.settingButtons;

import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.render.DrawHelper;
import me.oragejuice.oragehack.client.api.render.gui.AbstractRect;

import java.awt.*;

public class EnabledButton extends AbstractRect {

    Feature feature;

    final Color panelColor = new Color(0xf2f1f7);


    public EnabledButton(Feature feature, int x, int y) {
        this.feature = feature;
        this.x = x;
        this.y = y;
        width = 25;
        height = 16;

    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {
        if(feature.isEnabled()){
            DrawHelper.drawFilledRoundedRect(x,y,x+width,y+height,8, Color.GREEN);              //background
            DrawHelper.drawFilledRoundedRect(x + 2,y + 2,x+width-height-4,y+height-2,6, Color.WHITE); //button
        } else {
            DrawHelper.drawFilledRoundedRect(x,y,x+width,y+height,8, panelColor);                  //background
            DrawHelper.drawFilledRoundedRect(x + 2,y + 2,x+height - 4,y+height-2,6, Color.WHITE); //button

        }
    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int state) {
        if(isInside(mouseX, mouseY)){
            feature.toggle();
        }

    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY, int state) {

    }

    @Override
    public void onKeyTyped(char typedChar, int keyCode) {

    }

}
