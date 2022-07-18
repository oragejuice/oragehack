package me.oragejuice.oragehack.client.features.testFeature;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import me.oragejuice.oragehack.client.api.settings.SettingBuilder;
import org.lwjgl.input.Keyboard;

public class TestFeature extends Feature {

    GenericSetting<Boolean> booleanSetting = new GenericSetting<>("testBooleanSetting", true);
    GenericSetting<Boolean> otherBooleanSetting = new GenericSetting<>("testBooleanSetting2", true);
    GenericSetting<Float> floatSetting = new SettingBuilder<Float>("floatSetting", 1.0f).setParent(otherBooleanSetting);
    GenericSetting<Float> otherFloatSetting = new SettingBuilder<Float>("otherFloatSetting", 2.0F).setParent(otherBooleanSetting);



    public TestFeature() {
        super("TestFeature", new TickListener());
        this.keybind = Keyboard.KEY_N;
        this.registerSettings(otherBooleanSetting, booleanSetting);
    }

}
