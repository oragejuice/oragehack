package me.oragejuice.oragehack.client.features.testFeature;

import me.oragejuice.oragehack.client.api.feature.Feature;
import org.lwjgl.input.Keyboard;

public class TestFeature extends Feature {

    public TestFeature() {
        super("TestFeature", new TickListener());
        this.keybind = Keyboard.KEY_N;
    }

}
