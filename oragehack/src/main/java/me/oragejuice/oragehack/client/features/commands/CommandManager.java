package me.oragejuice.oragehack.client.features.commands;

import java.util.ArrayList;

public class CommandManager {

    Class[] commandClasses = new Class[]{
            Config.class
    };


    public void init(){
        for (Class commandClass : commandClasses) {
            try {
                commandClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
