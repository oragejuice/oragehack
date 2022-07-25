package me.oragejuice.oragehack.client.api.rotation;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.Globals;
import net.minecraft.util.math.MathHelper;

public class RotationFactory implements Globals{

    private static Rotation rotation;

    public static Rotation getCurrentRotation(){
        return rotation;
    }

    public static Rotation postRotation(){
        try {
            if(Oragehack.INSTANCE.featureManager.rotations.isEnabled()){
                rotation.yaw = limitAngle(RotationCache.serverYaw,
                        rotation.yaw,
                        Oragehack.INSTANCE.featureManager.rotations.yawStep.getValue()
                        );

                rotation.pitch = limitAngle(RotationCache.serverPitch,
                        rotation.pitch,
                        Oragehack.INSTANCE.featureManager.rotations.pitchStep.getValue()
                );
                return rotation;
            } else {
                //no spoofing do nothing
                return new Rotation(mc.player.rotationYaw, mc.player.rotationPitch, -1);
            }
        } finally {
            rotation = new Rotation(mc.player.rotationYaw, mc.player.rotationPitch,-1);
        }
    }

    public static float limitAngle(float current, float target, float step){

        float diffYaw = MathHelper.wrapDegrees((float) (target - current));
        float diff = MathHelper.wrapDegrees((float) (target - current));

        if (diffYaw > step) {
            diffYaw = (float) (step);
        } else if (diffYaw < -step) {
            diffYaw = (float) (-step);
        }

        return current + Math.min(diffYaw, Math.abs(diff));
    }




}
