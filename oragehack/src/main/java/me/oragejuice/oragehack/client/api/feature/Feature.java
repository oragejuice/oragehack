package me.oragejuice.oragehack.client.api.feature;


import com.google.common.eventbus.Subscribe;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.INameable;
import me.oragejuice.oragehack.client.api.event.IListener;
import me.oragejuice.oragehack.client.event.KeypressEvent;
import org.lwjgl.input.Keyboard;

public class Feature implements IListener, INameable {

    final String name;
    int keybind = Keyboard.KEY_NONE;
    final IListener[] listeners;
    private boolean enabled = false;

    public Feature(String name, IListener...listeners) {
        this.name = name;
        this.listeners = listeners;
        Oragehack.INSTANCE.eventBus.register(this);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        if (this.enabled) {
            for (IListener listener : listeners) {
                Oragehack.INSTANCE.eventBus.register(listener);
            }
        } else {
            for (IListener listener : listeners) {
                Oragehack.INSTANCE.eventBus.unregister(listener);
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

    @Subscribe
    public void onKeyEvent(KeypressEvent event){
        Oragehack.LOGGER.info("Key event");
        if(event.keycode == this.keybind){
            Oragehack.LOGGER.info("Should toggle to be {} %1", this.enabled );
            this.toggle();
        }
    }

}
