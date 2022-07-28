package me.oragejuice.oragehack.client.api.feature;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.features.modules.Esp;
import me.oragejuice.oragehack.client.features.modules.clickGui.ClickGuiFeature;
import me.oragejuice.oragehack.client.features.modules.rotations.Rotations;
import me.oragejuice.oragehack.client.features.modules.spin.Spin;
import me.oragejuice.oragehack.client.features.modules.testFeature.TestFeature;
import me.oragejuice.oragehack.client.features.modules.watermark.Watermark;

import java.util.ArrayList;

public class FeatureManager {

    private ArrayList<Feature> features = new ArrayList<>();

    public Rotations rotations;
    public TestFeature testFeature;
    public Watermark watermark;
    public ClickGuiFeature clickGuiFeature;
    public Esp esp;
    public Spin spin;

    public void init() {
        testFeature = new TestFeature();
        watermark = new Watermark();
        esp = new Esp();
        clickGuiFeature = new ClickGuiFeature();
        rotations = new Rotations();
        spin = new Spin();

    }

    public ArrayList<Feature> getFeatures(){
        return this.features;
    }

    //Can return null
    public ArrayList<Feature> getFeature(Categories category){
        ArrayList<Feature> ret = new ArrayList<>();
        for (Feature feature : features) {
            if(feature.getCategory() == (category)){
                 ret.add(feature);
            }
        }
        return ret;
    }

    //Can return null
    public Feature getFeatureByName(String name){
        for (Feature feature : features) {
            if(feature.getName().equals(name)){
                return feature;
            }
        }
        return null;
    }

    public void registerFeature(Feature feature) {
        this.features.add(feature);
    }
}
