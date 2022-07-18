package me.oragejuice.oragehack.client.api.event;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.event.KeypressEvent;

public class BindingManager {

    @EventHandler
    public void onKey(KeypressEvent event){
        for (Feature feature : Oragehack.INSTANCE.featureManager.getFeatures()) {
            if(feature.keybind == event.keycode){
                Oragehack.LOGGER.info("Toggled feature %s", feature.getName());
                feature.toggle();
            }
        }
    }
}
