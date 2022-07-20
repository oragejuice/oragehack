package me.oragejuice.oragehack.client.api.feature;


import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.Globals;
import me.oragejuice.oragehack.client.api.INameable;
import me.oragejuice.oragehack.client.api.IListener;
import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import me.oragejuice.oragehack.client.api.settings.ISubSetting;
import me.oragejuice.oragehack.client.api.event.KeypressEvent;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public abstract class Feature implements IListener, INameable, Globals, ISubSetting {
    final String name;
    public int keybind = Keyboard.KEY_NONE;
    private IListener[] listeners;
    private boolean enabled = false;



    private final ArrayList<GenericSetting> settings = new ArrayList<GenericSetting>();


    public Feature(String name, IListener... listeners) {
        this.listeners = listeners;
        this.name = name;
        Oragehack.INSTANCE.featureManager.registerFeature(this);
    }

    protected void onEnable(){};

    protected void onDisable(){};


    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(listeners == null) return;
        if (this.enabled) {
            for (IListener listener : listeners) {
                Oragehack.INSTANCE.eventBus.subscribe(listener);
            }
            onEnable();
        } else {
            for (IListener listener : listeners) {
                Oragehack.INSTANCE.eventBus.unsubscribe(listener);
            }
            onDisable();
        }
    }

    public boolean isEnabled(){
        return this.enabled;
    }

    public void toggle() {
        setEnabled(!enabled);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean shouldListen() {
        return true;
    }

    @EventHandler
    public void onKeyEvent(KeypressEvent event){
        Oragehack.LOGGER.info("Key event");
        if(event.keycode == this.keybind){
            Oragehack.LOGGER.info("Should toggle to be {}", this.enabled );
            this.toggle();
        }
    }

    public IListener[] getListeners() {
        return listeners;
    }

    protected void registerSettings(@Nonnull GenericSetting... settings){
        for (GenericSetting setting : settings) {
            this.settings.add(setting);
        }
    }

    @Override
    public ArrayList<GenericSetting> getSettings() {
        return settings;
    }

    public GenericSetting getSetting(String name){
        for (GenericSetting setting : settings) {
            if (setting.getName().equals(name)){
                return  setting;
            }
        }
        return null;
    }


}
