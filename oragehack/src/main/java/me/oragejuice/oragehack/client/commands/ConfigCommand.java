package me.oragejuice.oragehack.client.commands;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.command.AbstractCommand;

public class ConfigCommand extends AbstractCommand {

    public ConfigCommand() {
        super("config",
                new String[][]
                        {
                                {"config"},
                                {"load", "save"}
                        });
    }

    @Override
    public void dispatch(String[] args) {
        if(args[1].equals("load")) Oragehack.INSTANCE.configurator.load();
        if(args[1].equals("save")) Oragehack.INSTANCE.configurator.save();
    }
}
