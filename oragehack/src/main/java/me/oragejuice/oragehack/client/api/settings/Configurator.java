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

    private final File mainDirectory = new File("oragehack/");
    private final File configFile = new File("oragehack/config.yaml");
    FileWriter fw;


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
                    subNodes.clear(); // we dont need to remmeber all previous settngs
                    subNodes.add(f);
                } else {

                    if(tabs - lastTab < 0){
                        for (int i = 0; i < lastTab - tabs; i++) {
                            subNodes.remove(subNodes.size()-1);
                        }
                    }

                    String[] data = line.replace("\t", "").split(":");
                    GenericSetting s = getSettingByName(subNodes.get(subNodes.size()-1).getSettings(), data[0]);
                    if(s != null){
                        // was able to find setting in from the top of the subnode stack
                        Oragehack.LOGGER.info("able find setting {} - parent {}", data[0], ((INameable) subNodes.get(subNodes.size()-1)).getName());

                        //as it has children it must be added to stack
                        if(!s.getSettings().isEmpty()){
                            subNodes.add(s);
                        }
                    } else {
                        Oragehack.LOGGER.info("Couldnt find setting {} - parent {}", data[0], ((INameable) subNodes.get(subNodes.size()-1)).getName());
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
                mainDirectory.createNewFile();
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


