package me.oragejuice.oragehack.client.api.rotation;

import me.oragejuice.oragehack.client.Globals;
import me.oragejuice.oragehack.client.api.feature.Feature;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class Rotation implements Globals {

    public float yaw;
    public float pitch;
    public int priority;

    public Rotation(float yaw, float pitch, int priority){
        this.yaw = MathHelper.wrapDegrees(yaw);
        this.pitch = MathHelper.wrapDegrees(pitch);
        this.priority = priority;
    }

    public Rotation(float yaw, float pitch, Feature feature){
        this(yaw, pitch, feature.getPriority());
    }

    public Rotation(Float[] rot, int priority){
        this(rot[0], rot[1], priority);
    }

    public Rotation(Vec3d vec3d, int priority){
        this(calculateAngles(vec3d), priority);
    }

    public static Float[] calculateAngles(Vec3d to) {
        float yaw = (float) (Math.toDegrees(Math.atan2(to.subtract(mc.player.getPositionEyes(1)).z, to.subtract(mc.player.getPositionEyes(1)).x)) - 90);
        float pitch = (float) Math.toDegrees(-Math.atan2(to.subtract(mc.player.getPositionEyes(1)).y, Math.hypot(to.subtract(mc.player.getPositionEyes(1)).x, to.subtract(mc.player.getPositionEyes(1)).z)));
        return new Float[]{MathHelper.wrapDegrees(yaw), MathHelper.wrapDegrees(pitch)};
    }


}
