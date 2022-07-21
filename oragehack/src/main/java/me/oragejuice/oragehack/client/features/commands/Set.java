package me.oragejuice.oragehack.client.features.commands;

import me.oragejuice.commandDispatcher.ArgumentNode;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.settings.GenericSetting;

import java.util.Arrays;

public class Set {

    Feature[] features = Oragehack.INSTANCE.featureManager.getFeatures().stream().toArray(Feature[]::new);
    String[] featureNames = Arrays.stream(features).map(feature -> feature.getName()).toArray(String[]::new);


    ArgumentNode set = new ArgumentNode("(?i)set", new String[]{"set"}, 0, generateFeatureArgumentNodes());

    public Set(){
        Oragehack.INSTANCE.commandDispatcher.register(set);
    }


    //So you see, this is what awful code has to be written when you need to write code that generates itself
    private static ArgumentNode[] generateFeatureArgumentNodes(){
        Feature[] features = Oragehack.INSTANCE.featureManager.getFeatures().stream().toArray(Feature[]::new);
        ArgumentNode[] featureNodes = new ArgumentNode[features.length];

        for (int i = 0; i < featureNodes.length; i++) {
            featureNodes[i] = new ArgumentNode(
                    "(?i)" + features[i].getName(),
                    new String[]{features[i].getName()},
                    1,
                    generateSettingArgumentNodes(features[i])
            );
        }
        return featureNodes;
    }

    private static ArgumentNode[] generateSettingArgumentNodes(Feature feature){

        ArgumentNode[] nodes = new ArgumentNode[feature.getFlattenedSettings().size()];

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new ArgumentNode(
                    "(?i)" + feature.getFlattenedSettings().get(i).getFullyQualifiedName(),
                    new String[]{feature.getFlattenedSettings().get(i).getFullyQualifiedName()},
                    2,
                    generateValueNode(feature.getFlattenedSettings().get(i)))
                    ;
        }
        return nodes;

    }

    private static ArgumentNode[] generateValueNode(GenericSetting setting){
        if(setting.getValue() instanceof Boolean){
            return new ArgumentNode[] {
                    new ArgumentNode("(?i)(true|false)",
                            new String[]{"true", "false"},
                            3,
                            ((String[] s) -> setting.setValue(s[3]))
                    )};
        } else {
            return new ArgumentNode[] {
                    new ArgumentNode(".*",
                            new String[]{setting.getValue().toString()},
                            3,
                            ((String[] s) -> setting.setValue(s[3]))
                    )};
        }
    }



}
