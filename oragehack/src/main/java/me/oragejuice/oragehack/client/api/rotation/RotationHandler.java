package me.oragejuice.oragehack.client.api.rotation;

import me.oragejuice.oragehack.Oragehack;

import javax.annotation.Nonnull;

public class RotationHandler {

    public static Rotation nextServerRotation;

    public static float serverYaw; //the yaw we last sent to the server
    public static float serverPitch; //the pitch we last sent to the server;

    //Send a rotation if priority is lower or if there is not a spoofed rotation sent yet
    public static void postRotation(Rotation rotation){

        if(nextServerRotation == null){
            //nextServerRotation = rotation;
            setNextServerRotation(rotation);
            return;
        }

        if(nextServerRotation.priority >= rotation.priority){
            setNextServerRotation(rotation);
        }
    }

    private static void setNextServerRotation(Rotation rotation){
        //we need to yawstep
        if(Oragehack.INSTANCE.featureManager.rotations.isEnabled()){
            nextServerRotation = new Rotation(
                    Rotation.limitAngleChange(serverYaw, rotation.yaw, Oragehack.INSTANCE.featureManager.rotations.yawStep.getValue()),
                    Rotation.limitAngleChange(serverPitch, rotation.pitch, Oragehack.INSTANCE.featureManager.rotations.pitchStep.getValue()),
                    rotation.priority);

        } else {
            // no need to yawstep
            nextServerRotation = rotation;
        }
    }

    public static Rotation pop(){
        try {
            return nextServerRotation;
        } finally {
            nextServerRotation = null;
        }
    }

    public static boolean isSpoofing(){
        return (nextServerRotation != null);
    }
}
