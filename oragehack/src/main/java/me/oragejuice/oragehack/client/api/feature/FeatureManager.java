package me.oragejuice.oragehack.client.api.feature;

import me.oragejuice.oragehack.client.features.testFeature.TickListener;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class FeatureManager {

    ArrayList<Feature> features = new ArrayList<>();

    public Feature testFeature;

    public void init() {

        testFeature = new Feature("TestFeature", new TickListener());
        testFeature.keybind = Keyboard.KEY_N;
    }
}
