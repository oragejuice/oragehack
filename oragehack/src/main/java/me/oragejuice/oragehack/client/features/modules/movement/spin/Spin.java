package me.oragejuice.oragehack.client.features.modules.movement.spin;

import me.oragejuice.eventbus.EventHandler;
import me.oragejuice.oragehack.client.api.event.PlayerUpdateEvent;
import me.oragejuice.oragehack.client.api.feature.Categories;
import me.oragejuice.oragehack.client.api.feature.Feature;
import me.oragejuice.oragehack.client.api.rotation.Rotation;
import me.oragejuice.oragehack.client.api.rotation.RotationHandler;
import net.minecraft.util.math.MathHelper;

public class Spin extends Feature {

    public Spin() {
        super("Spin", Categories.CLIENT);
    }

    float x;

    @EventHandler
    public void onUpdate(PlayerUpdateEvent event){
        RotationHandler.postRotation(new Rotation(MathHelper.wrapDegrees(x += 5F), 0, 0));
    }
}
