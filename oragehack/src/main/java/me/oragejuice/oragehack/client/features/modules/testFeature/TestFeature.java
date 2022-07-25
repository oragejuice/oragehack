package me.oragejuice.oragehack.client.features.modules.testFeature;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import me.oragejuice.oragehack.client.api.settings.SettingBuilder;
import me.oragejuice.oragehack.client.api.settings.premade.BooleanSetting;
import me.oragejuice.oragehack.client.api.settings.premade.FloatSetting;
import net.minecraft.network.play.client.CPacketPlayer;
import org.lwjgl.input.Keyboard;

public class TestFeature extends Feature {

    BooleanSetting booleanSetting = new BooleanSetting("testBooleanSetting", true);
    BooleanSetting otherBooleanSetting = new BooleanSetting("testBooleanSetting2", true);
    GenericSetting<Float> floatSetting = new SettingBuilder<Float>("floatSetting", 1.0f).setParent(otherBooleanSetting);
    GenericSetting<Float> otherFloatSetting = new SettingBuilder<Float>("otherFloatSetting", 2.0F).setParent(otherBooleanSetting);
    GenericSetting<Float> subSubSubSettingFloat = new SettingBuilder<Float>("subSubSetting", 3.0F).setParent(floatSetting);



    public TestFeature() {
        super("Test Feature", Categories.CLIENT, 10, new TickListener());
        this.keybind = Keyboard.KEY_N;
        this.registerSettings(otherBooleanSetting, booleanSetting);
    }



}
