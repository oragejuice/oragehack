package me.oragejuice.oragehack.client.features.commands;

public class CommandManager {

    Class[] commandClasses = new Class[]{
            Config.class,
            Set.class
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
