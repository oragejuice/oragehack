package me.oragejuice.oragehack.client.features.commands;

import me.oragejuice.commandDispatcher.ArgumentNode;
import me.oragejuice.oragehack.Oragehack;

public class Config {

    ArgumentNode save = new ArgumentNode("(?i)save", new String[]{"save"}, 1,
            ((String[] s) -> {
                Oragehack.INSTANCE.configurator.save();
            }));

    ArgumentNode load = new ArgumentNode("(?i)load", new String[]{"load"}, 1,
            ((String[] s) -> {
                Oragehack.INSTANCE.configurator.load();
            }));

    ArgumentNode config = new ArgumentNode("(?i)config", new String[]{"config"}, 0, save, load);


    public Config() {
        Oragehack.INSTANCE.commandDispatcher.register(config);
    }
}
