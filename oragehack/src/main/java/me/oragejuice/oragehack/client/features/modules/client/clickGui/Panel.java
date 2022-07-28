package me.oragejuice.oragehack.client.features.modules.client.clickGui;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.render.DrawHelper;
import me.oragejuice.oragehack.client.api.render.gui.AbstractRect;

import java.awt.*;
import java.util.ArrayList;

public class Panel extends AbstractRect {

    final Color panelColor = new Color(0xf2f1f7);

    private final Categories category;
    private boolean dragging = false;

    ArrayList<Feature> features = new ArrayList<>();
    ArrayList<ModuleButton> moduleButtons = new ArrayList<>();

    public Panel(Categories category, int x) {
        this.category = category;
        this.x = x;
        this.width = 100 + 6;
        this.height = font.getHeight() + 6;

        features = Oragehack.INSTANCE.featureManager.getFeature(category);
        ModuleButtonFactory.reset();
        ModuleButtonFactory.setValues(x, y+height, width, 30);
        for (Feature f : features) {
            moduleButtons.add(
                    ModuleButtonFactory.newModuleButton(f)
            );
        }
    }

    @Override
    public void draw(int mouseX, int mouseY, float partialTicks) {

        if(dragging){
            updatePosition(mouseX, mouseY);
        }

        DrawHelper.drawFilledRect(x, y, x + width , y + height + moduleButtons.size()*height + 2, panelColor);
        font.drawString(category.toString(),x + 3, y + 3, Color.BLACK);

        if(!moduleButtons.isEmpty()) {
            DrawHelper.drawFilledRoundedRect(x + 3, y+height + 1, x + width - 3, y + height + 1 + moduleButtons.size()*height, 5F, Color.WHITE);
            for (int i = 0; i < moduleButtons.size(); i++) {
                moduleButtons.get(i).updatePosition(this.x + 3, this.y + height + (i * height));
                moduleButtons.get(i).draw(mouseX, mouseY, partialTicks);
            }
        }

    }

    @Override
    public void onMouseClicked(int mouseX, int mouseY, int state) {
        if(isInside(mouseX, mouseY)){
            dragging = true;
        }

        for (ModuleButton moduleButton : moduleButtons) {
            moduleButton.onMouseClicked(mouseX, mouseY, state);
        }
    }

    @Override
    public void onMouseReleased(int mouseX, int mouseY, int state) {
        dragging = false;

        for (ModuleButton moduleButton : moduleButtons) {
            moduleButton.onMouseReleased(mouseX, mouseY, state);
        }
    }



}
