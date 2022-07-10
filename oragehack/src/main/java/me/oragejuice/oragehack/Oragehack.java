package me.oragejuice.oragehack;


import com.google.common.eventbus.EventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Oragehack {

    public static final Logger LOGGER = LogManager.getLogger("oragehack");
    public EventBus eventBus;
    public static Oragehack INSTANCE = new Oragehack();

    public Oragehack() {}

    public void init(){
        LOGGER.info("OrageHack main");
        eventBus = new EventBus();
    }


}
