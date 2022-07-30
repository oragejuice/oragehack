package me.oragejuice.oragehack.client.features.commands;

public class CommandManager {

    //there is no reason to make this any different but i did cus im special

    Class[] commandClasses = new Class[]{
            Config.class,
            Set.class,
            Toggle.class,
            Bind.class
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
