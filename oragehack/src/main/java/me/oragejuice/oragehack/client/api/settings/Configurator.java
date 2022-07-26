package me.oragejuice.oragehack.client.api.settings;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.INameable;
import me.oragejuice.oragehack.client.api.feature.Feature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Configurator {

    //TODO add descriptions
    private final File mainDirectory = new File("oragehack/");
    private final File configFile = new File("oragehack/config.yaml");
    static FileWriter fw;


    public void save(){

        Oragehack.LOGGER.info("Saving Config");
        validateFolder();
        validateFile();

        try {
            this.fw = new FileWriter(configFile, false);
            this.generateFeatureConfig();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateFeatureConfig() throws IOException {

        if(Oragehack.INSTANCE.featureManager.getFeatures().isEmpty()) Oragehack.LOGGER.info("FEATURES ARE EMPTY");
        for (Feature feature : Oragehack.INSTANCE.featureManager.getFeatures()) {
            writeline(feature.getName() + ":");
            writeline("\tenabled: " + feature.isEnabled());
            writeline("\tkeybind: "+ feature.keybind);

            for (GenericSetting setting : feature.getSettings()) {
                saveSetting("\t", setting);
            }
        }
    }

    public void saveSetting(String tabs, GenericSetting setting) throws IOException {
        writeline(tabs + setting.getName() + ": " + setting.getValue().toString());
        if(!(setting.getSettings().isEmpty())){
            for (Object childSetting : setting.getSettings()) {
                saveSetting(tabs + "\t", (GenericSetting) childSetting);
            }
        }
    }


    public void load(){
        Oragehack.LOGGER.info("Loading config..");
        validateFolder();
        validateFile();

        ArrayList<ISubSetting> subNodes = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(configFile);
            String line = "";
            int tabs = 0;
            int lastTab = 0;
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                tabs = countTabs(line);
                if(tabs == 0) {
                    //then this is not a setting but a feature
                    Feature f = Oragehack.INSTANCE.featureManager.getFeatureByName(line.replace(":", ""));
                    if(f == null){
                        Oragehack.LOGGER.error("{} Module not found! please remove from your config file!", line);
                        continue;
                    }
                    subNodes.clear(); // we dont need to remmeber all previous settngs
                    subNodes.add(f);
                } else {

                    if(tabs - lastTab < 0){
                        for (int i = 0; i < lastTab - tabs; i++) {
                            subNodes.remove(subNodes.size()-1);
                        }
                    }
                    String[] data = line.replace("\t", "").split(":");

                    //if the line is referencing a primitive setting of the feature
                    if(subNodes.size() == 1 && data[0].matches("^(enabled|keybind)")){
                        Feature f = (Feature) subNodes.get(0);
                        if(line.contains("enabled")) {
                            f.setEnabled(Boolean.valueOf(data[1].replace(" ", "")));
                        } else{
                            f.keybind = (Integer.valueOf(data[1].replace(" ", "")));
                        }
                        continue;
                    } else {
                        //must be referencing a setting
                        GenericSetting s = getSettingByName(subNodes.get(subNodes.size() - 1).getSettings(), data[0]);
                        if (s != null) {

                            // was able to find setting in from the top of the subnode stack
                            //as it has children it must be added to stack
                            s.setValue(data[1].trim());

                            if (!s.getSettings().isEmpty()) {
                                subNodes.add(s);
                            }
                        } else {
                            Oragehack.LOGGER.info("Couldnt find setting {} - parent {}", data[0], ((INameable) subNodes.get(subNodes.size() - 1)).getName());
                        }
                    }

                }
                lastTab = tabs;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void validateFolder(){
        if(!mainDirectory.exists()){
            Oragehack.LOGGER.info("Directory didn't exist, creating!");
            mainDirectory.mkdirs();
        }
    }

    private void validateFile(){
        if(!configFile.exists()){
            Oragehack.LOGGER.info("File didn't exist, creating!");
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeline(String message) throws IOException {
        fw.write(message + "\n");
    }

    private static GenericSetting getSettingByName(ArrayList<GenericSetting> features, String name){
        for (GenericSetting feature : features) {
            if(feature.getName().equals(name)){
                return feature;
            }
        }
        return null;
    }

    public static int countTabs(String s){
        int v = 0;
        for (char c : s.toCharArray()) {
            if (c == '\t'){
                v++;
            }
        }
        return v;
    }

}


