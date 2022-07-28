package me.oragejuice.oragehack.client.features.modules.movement.velocity;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.event.network.server.SPacketEntityVelocityEvent;
import me.oragejuice.oragehack.client.api.event.network.server.SPacketExplosionEvent;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.settings.premade.FloatSetting;
import me.oragejuice.oragehack.mixins.network.packets.incoming.SPacketEntityVelocityAccessor;
import me.oragejuice.oragehack.mixins.network.packets.incoming.SPacketExplosionAccessor;

public class Velocity extends Feature {

    FloatSetting horizontal = new FloatSetting("horizontal", 0F, 0F, 100F);
    FloatSetting vertical = new FloatSetting("vertical", 0F, 0F, 100F);


    public Velocity() {
        super("Velocity", Categories.MOVEMENT);
        registerSettings(horizontal, vertical);
    }

    @EventHandler
    public void onVelocityEvent(SPacketEntityVelocityEvent event){
        /*
        if(horizontal.getValue() == 0F && vertical.getValue() == 0F){
            event.cancel();
            return;
        }

         */
        ((SPacketExplosionAccessor) event.packet).setMotionX( ((SPacketExplosionAccessor) event.packet).getMotionX() * horizontal.getValue());
        ((SPacketExplosionAccessor) event.packet).setMotionY( ((SPacketExplosionAccessor) event.packet).getMotionY() * vertical.getValue());
        ((SPacketExplosionAccessor) event.packet).setMotionZ( ((SPacketExplosionAccessor) event.packet).getMotionZ() * horizontal.getValue());
    }

    @EventHandler
    public void onVelocityEvent(SPacketExplosionEvent event){
        /*
        if(horizontal.getValue() == 0F && vertical.getValue() == 0F){
            event.cancel();
            return;
        }
         */
        ((SPacketEntityVelocityAccessor) event.packet).setMotionX((int) (((SPacketEntityVelocityAccessor) event.packet).getMotionX() * horizontal.getValue()));
        ((SPacketEntityVelocityAccessor) event.packet).setMotionY((int) (((SPacketEntityVelocityAccessor) event.packet).getMotionY() * vertical.getValue()));
        ((SPacketEntityVelocityAccessor) event.packet).setMotionZ((int) (((SPacketEntityVelocityAccessor) event.packet).getMotionZ() * horizontal.getValue()));
    }


}
