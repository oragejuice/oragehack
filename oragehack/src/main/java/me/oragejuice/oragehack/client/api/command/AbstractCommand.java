package me.oragejuice.oragehack.client.api.command;

import me.oragejuice.oragehack.client.api.INameable;

public abstract class AbstractCommand implements INameable {

    final String name;
    final String[][] arguments;

    public AbstractCommand(String name, String[][] arguments) {
        this.name = name;
        this.arguments = arguments;
    }


    public void dispatch(String[] arguments){

    }

    @Override
    public String getName() {
        return name;
    }
}
