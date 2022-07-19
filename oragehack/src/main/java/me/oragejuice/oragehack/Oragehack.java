package me.oragejuice.oragehack;


import me.oragejuice.commandDispatcher.CommandDispatcher;
import me.oragejuice.eventbus.EventManager;
import me.oragejuice.oragehack.client.api.event.BindingManager;
import me.oragejuice.oragehack.client.api.feature.FeatureManager;
import me.oragejuice.oragehack.client.api.settings.Configurator;
import me.oragejuice.oragehack.client.features.commands.CommandManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Oragehack {

    public static final Logger LOGGER = LogManager.getLogger("oragehack");
    public EventManager eventBus;
    public static Oragehack INSTANCE = new Oragehack();
    public FeatureManager featureManager;
    public Configurator configurator;
    public CommandDispatcher commandDispatcher;

    public Oragehack() {
        featureManager = new FeatureManager();
        commandDispatcher = new CommandDispatcher();
    }

    public void init(){
        LOGGER.info("OrageHack main");
        eventBus = new EventManager();
        featureManager.init();
        CommandManager commandManager = new CommandManager();
        commandManager.init();

        BindingManager bindingHandler = new BindingManager();
        eventBus.subscribe(bindingHandler);

        configurator = new Configurator();
        configurator.load();
        configurator.save();
    }


}
