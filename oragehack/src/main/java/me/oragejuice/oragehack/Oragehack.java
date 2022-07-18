package me.oragejuice.oragehack;


import me.oragejuice.eventbus.EventManager;
import me.oragejuice.oragehack.client.api.event.BindingManager;
import me.oragejuice.oragehack.client.api.feature.FeatureManager;
import me.oragejuice.oragehack.client.api.settings.Configurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Oragehack {

    public static final Logger LOGGER = LogManager.getLogger("oragehack");
    public EventManager eventBus;
    public static Oragehack INSTANCE = new Oragehack();
    public FeatureManager featureManager;

    public Oragehack() {
        featureManager = new FeatureManager();
    }

    public void init(){
        LOGGER.info("OrageHack main");
        eventBus = new EventManager();
        featureManager.init();

        BindingManager bindingHandler = new BindingManager();
        eventBus.subscribe(bindingHandler);

        Configurator configurator = new Configurator();
        configurator.save();
        configurator.load();
    }


}
