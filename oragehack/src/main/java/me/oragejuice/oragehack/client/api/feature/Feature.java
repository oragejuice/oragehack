package me.oragejuice.oragehack.client.api.feature;


import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.INameable;
import me.oragejuice.oragehack.client.api.IListener;
import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import me.oragejuice.oragehack.client.event.KeypressEvent;
import org.lwjgl.input.Keyboard;

import java.util.Vector;

public abstract class Feature implements IListener, INameable {

    final String name;
    public int keybind = Keyboard.KEY_NONE;
    private IListener[] listeners;
    private boolean enabled = false;
    private Vector<GenericSetting> settings = new Vector<>();

    public Feature(String name, IListener... listeners) {
        this.listeners = listeners;
        this.name = name;
        //Oragehack.INSTANCE.eventBus.subscribe(this);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if(listeners == null) return;
        if (this.enabled) {
            for (IListener listener : listeners) {
                Oragehack.INSTANCE.eventBus.subscribe(listener);
            }
        } else {
            for (IListener listener : listeners) {
                Oragehack.INSTANCE.eventBus.unsubscribe(listener);
            }
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

}
