package me.oragejuice.oragehack.client.api.feature;

import me.oragejuice.oragehack.client.features.testFeature.TestFeature;
import me.oragejuice.oragehack.client.features.testFeature.TickListener;
import org.lwjgl.input.Keyboard;

import java.util.ArrayList;

public class FeatureManager {

    public ArrayList<Feature> features = new ArrayList<>();

    public TestFeature testFeature;

    public void init() {
        testFeature = new TestFeature();
        features.add(testFeature);
    }
}
