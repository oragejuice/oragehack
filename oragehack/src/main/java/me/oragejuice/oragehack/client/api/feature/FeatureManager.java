package me.oragejuice.oragehack.client.api.feature;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.features.modules.testFeature.TestFeature;
import me.oragejuice.oragehack.client.features.modules.watermark.Watermark;

import java.util.ArrayList;

public class FeatureManager {

    private ArrayList<Feature> features = new ArrayList<>();

    public TestFeature testFeature;
    public Watermark watermark;

    public void init() {
        testFeature = new TestFeature();
        watermark = new Watermark();


        for (Feature feature : features) {
            Oragehack.LOGGER.info(feature.getName() + " loaded");
        }
    }

    public ArrayList<Feature> getFeatures(){
        return this.features;
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
