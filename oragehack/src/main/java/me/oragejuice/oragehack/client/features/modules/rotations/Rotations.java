package me.oragejuice.oragehack.client.features.modules.rotations;

import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.settings.premade.FloatSetting;

public class Rotations extends Feature {

    public FloatSetting yawStep = new FloatSetting("Yaw Step", 40.0F, 1.0F, 180F);
    public FloatSetting pitchStep = new FloatSetting("Pitch Step", 40.0F, 1.0F, 180F);

    public Rotations() {
        super("Rotations", Categories.CLIENT, 0);
        this.registerSettings(yawStep, pitchStep);
    }

}
