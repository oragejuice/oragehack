package me.oragejuice.oragehack.mixins.network;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.Globals;
import me.oragejuice.oragehack.client.api.rotation.Rotation;
import me.oragejuice.oragehack.client.api.rotation.RotationHandler;
import me.oragejuice.oragehack.mixins.network.packets.CPacketPlayerAccessor;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin implements Globals {

    @Inject(method = "sendPacket", at = @At("HEAD"))
    public void sendPacketHook(Packet<?> packetIn, CallbackInfo ci){
        if (packetIn instanceof CPacketPlayer) {

            //if vanilla is sending a Position packet but we also need to rotate, overwrite it and send a Position and rotation packet
            if (packetIn instanceof CPacketPlayer.Position && RotationHandler.isSpoofing()){
                packetIn = new CPacketPlayer.PositionRotation(
                        ((CPacketPlayer.Position) packetIn).getX(mc.player.posX), // Copy original packet
                        ((CPacketPlayer.Position) packetIn).getY(mc.player.getEntityBoundingBox().minY), // Why does MC send a packet with this Y value?
                        ((CPacketPlayer.Position) packetIn).getZ(mc.player.posZ),
                        RotationHandler.nextServerRotation.yaw, //set rotations to be the spoofed ones
                        RotationHandler.nextServerRotation.pitch,
                        ((CPacketPlayer.Position) packetIn).isOnGround() // Copy original packet
                        );
            }

            //if rotation is spoofing
            if (RotationHandler.isSpoofing()) {

                //spoof the packets
                Rotation rotation = RotationHandler.pop();
                ((CPacketPlayerAccessor) packetIn).setYaw(rotation.yaw);
                ((CPacketPlayerAccessor) packetIn).setPitch(rotation.pitch);
            }

            //update the cache
            //if is Position, then rotation is null and it will set it to 0 (funky business)
            if(!(packetIn instanceof CPacketPlayer.Position)) {
                RotationHandler.serverYaw = ((CPacketPlayerAccessor) packetIn).getYaw();
                RotationHandler.serverPitch = ((CPacketPlayerAccessor) packetIn).getPitch();
            }
        }
    }
}
