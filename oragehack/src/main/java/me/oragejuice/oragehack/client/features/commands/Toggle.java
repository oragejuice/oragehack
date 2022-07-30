package me.oragejuice.oragehack.client.features.commands;

import me.oragejuice.commandDispatcher.ArgumentNode;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.feature.Feature;

public class Toggle {

    public Toggle() {
        Oragehack.INSTANCE.commandDispatcher.register(toggle);
    }


    ArgumentNode toggle = new ArgumentNode("(?i)(t|toggle)", new String[]{"toggle", "t"}, 0, generateFeatureArgumentNodes());



    private static ArgumentNode[] generateFeatureArgumentNodes(){
        Feature[] features = Oragehack.INSTANCE.featureManager.getFeatures().stream().toArray(Feature[]::new);
        ArgumentNode[] featureNodes = new ArgumentNode[features.length];

        for (int i = 0; i < featureNodes.length; i++) {
            Feature f = features[i];
            featureNodes[i] = new ArgumentNode(
                    "(?i)" + f.getName(),
                    new String[]{f.getName().replaceAll(" ", "_")},
                    1,
                    ((String[] s) -> f.toggle())
            );
        }
        return featureNodes;
    }
}
