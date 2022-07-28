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
            nextServerRotation = rotation;
            //Oragehack.LOGGER.info("null rotation needed to be overrired");
            return;
        }

        if(nextServerRotation.priority >= rotation.priority){
            nextServerRotation = rotation;
            //Oragehack.LOGGER.info("prio override for rotation");
        }
    }

    //return the next rotation to be sent and reset to vanilla
    public static Rotation pop(){
        try {
            return nextServerRotation;
        } finally {
            nextServerRotation = null;
        }
    }

    // if nextServerRotation is null, it means we havent set a rotation to spoof yet
    public static boolean isSpoofing(){
        return (nextServerRotation != null);
    }
}
