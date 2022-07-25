package me.oragejuice.oragehack.client.api.rotation;

import me.oragejuice.oragehack.client.api.feature.Feature;
import net.minecraft.util.math.MathHelper;

public class Rotation {

    public int getPriority() {
        return priority;
    }

    private int priority;

    public float yaw;
    public float pitch;


    public Rotation(float yaw, float pitch, int priority) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.priority = priority;
    }

    public void update(float yaw, float pitch, int priority) {
        //if the event posting's event is lower then update, otherwise ignore
        if( priority < this.priority){
            this.yaw = yaw;
            this.pitch = pitch;
            this.priority = priority;
        }
    }

    public Rotation(float yaw, float pitch, Feature feature) {
        this(yaw, pitch, feature.getPriority());
    }


}
