package me.oragejuice.oragehack.client.api.rotation;

import me.oragejuice.oragehack.client.Globals;
import me.oragejuice.oragehack.client.api.feature.Feature;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.Currency;

public class Rotation extends PureRotation implements Globals {

    public float yaw;
    public float pitch;
    public int priority;

    public Rotation(float yaw, float pitch, int priority){
        super(MathHelper.wrapDegrees(yaw), MathHelper.wrapDegrees(pitch));
        this.priority = priority;
    }

    public Rotation(float yaw, float pitch, Feature feature){
        this(yaw, pitch, feature.getPriority());
    }

    public Rotation(PureRotation pureRotation, int priority){
        this(pureRotation.yaw, pureRotation.pitch, priority);
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

    public static float getAngleDifference(final float a, final float b) {
        return ((((a - b) % 360F) + 540F) % 360F) - 180F;
    }

    public static PureRotation limitAngleChange(final PureRotation currentRotation, final PureRotation targetRotation, final float turnSpeed) {
        final float yawDifference = getAngleDifference(targetRotation.yaw, currentRotation.yaw);
        final float pitchDifference = getAngleDifference(targetRotation.pitch, currentRotation.pitch);

        return new PureRotation(
                currentRotation.yaw + (yawDifference > turnSpeed ? turnSpeed : Math.max(yawDifference, -turnSpeed)),
                currentRotation.pitch + (pitchDifference > turnSpeed ? turnSpeed : Math.max(pitchDifference, -turnSpeed))
                );
    }

    public static float limitAngleChange(final float currentRotation, final float targetRotation, final float turnSpeed) {
        final float angleDifference = getAngleDifference(targetRotation, currentRotation);
        return currentRotation + (angleDifference > turnSpeed ? turnSpeed : Math.max(angleDifference, -turnSpeed));
    }


}
