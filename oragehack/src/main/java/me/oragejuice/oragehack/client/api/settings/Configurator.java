package me.oragejuice.oragehack.client.api.settings;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.feature.Feature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Configurator {

    private final File mainDirectory = new File("oragehack/");
    private final File configFile = new File("oragehack/config.yaml");
    FileWriter fw;

    ArrayList<ISubSetting> subNodes = new ArrayList<>();

    public void save(){

        Oragehack.LOGGER.info("Saving Config");
        validateFolder();
        validateFile();

        try {
            this.fw = new FileWriter(configFile);
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

        try {
            Scanner reader = new Scanner(configFile);
            Feature lastFeature = null;
            GenericSetting lastSetting = null;
            String lastline = "";
            String[] v;
            while (reader.hasNextLine()){
                String data = reader.nextLine();

                //this regex should Match for when there are no leading Tabs and it ends in a colon, to find Feature names
                if(data.matches("^[^\\u0009]*:$")){
                    lastFeature = Oragehack.INSTANCE.featureManager.getFeatureByName(data.replace(":", ""));
                    subNodes.clear();
                    subNodes.add(lastFeature);
                } else {
                    //If somehow the last feature ended up being null or something
                    if(lastFeature == null){
                        Oragehack.LOGGER.info("feature was null");
                        continue;
                    }

                    //if the line could likely be a setting
                    if(data.contains(":") && data.startsWith("\u0009")){
                        switch (isChildOf(lastline, data)){

                            //is a child of last setting
                            //e.g
                            //-thing: true
                            //--new_thing: 1.0
                            case (1):
                                //in a feature
                                v = data.replace("\t", "").split(":");
                                if(subNodes.size() < 2){
                                    GenericSetting s = getSettingByName(subNodes.get(0).getSettings(), v[0]);
                                    if(s == null) {
                                        Oragehack.LOGGER.info("You fucked up L");
                                        continue;
                                    }
                                    s.setValue(v[1]);
                                } else {
                                    //sub setting (child of another setting)
                                    subNodes.add(lastSetting);

                                    GenericSetting s = getSettingByName(subNodes.get(subNodes.size()-1).getSettings(), v[0]);
                                    if(s == null) {
                                        Oragehack.LOGGER.info("{} wasnt able to load case(1)", v[0]);
                                        continue;
                                    }
                                    s.setValue(v[1]);
                                }

                                break;

                            //is a child of parent node
                            //e.g
                            // -thing: true
                            // -otherThing: 1.0
                            case (0):
                                v = data.replace("\t", "").split(":");
                                GenericSetting s = getSettingByName(subNodes.get(subNodes.size()-1).getSettings(), v[0]);
                                if(s == null) {
                                    Oragehack.LOGGER.info("{} wasnt able to load case(0)", v[0]);
                                    continue;
                                }
                                s.setValue(v[1]);
                                break;

                            //is a parent node
                            case (-1):
                                subNodes.remove(subNodes.size()-1);
                                v = data.replace("\t", "").split(":");
                                s = getSettingByName(subNodes.get(subNodes.size()-1).getSettings(), v[0]);
                                if(s == null) {
                                    Oragehack.LOGGER.info("{} wasnt able to load case(-1)", v[0]);
                                    continue;
                                }
                                s.setValue(v[1]);
                                break;
                        }


                    }
                }
                lastline = data;
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
                mainDirectory.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void writeline(String message) throws IOException {
        fw.write(message + "\n");
    }

    /*
    if returns 1 then it is a child
    if returns 0 then is of same subset
    if returns -1 then is escaping subsetting
     */

    private int isChildOf(String parent, String child){
        return countTabs(child) - countTabs(parent) ;
    }

    private static GenericSetting getSettingByName(ArrayList<GenericSetting> features, String name){
        for (GenericSetting feature : features) {
            Oragehack.LOGGER.info("{} searching settings", feature.getName());
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


