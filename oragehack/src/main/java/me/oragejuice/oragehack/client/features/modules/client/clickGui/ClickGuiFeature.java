package me.oragejuice.oragehack.client.features.modules.client.clickGui;

import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import org.lwjgl.input.Keyboard;

public class ClickGuiFeature extends Feature {

    public ClickGuiFeature() {
        super("Click Gui", Categories.CLIENT, 10);
        super.keybind = Keyboard.KEY_SEMICOLON;
    }

    @Override
    public void onEnable(){
        mc.displayGuiScreen(new ClickGui());
    }
}
