package me.oragejuice.oragehack.mixins.network;

import me.oragejuice.oragehack.client.api.rotation.Rotation;
import me.oragejuice.oragehack.client.api.rotation.RotationCache;
import me.oragejuice.oragehack.client.api.rotation.RotationFactory;
import me.oragejuice.oragehack.mixins.network.packets.CPacketPlayerAccessor;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetHandlerPlayClient.class)
public class NetHandlerPlayClientMixin {

    @Inject(method = "sendPacket", at = @At("HEAD"))
    public void sendPacketHook(Packet<?> packetIn, CallbackInfo ci){
        if (packetIn instanceof CPacketPlayer && !(packetIn instanceof  CPacketPlayer.Position)) {
            //spoof the packets
            Rotation rotation = RotationFactory.postRotation();
            ((CPacketPlayerAccessor) packetIn).setYaw(rotation.yaw);
            ((CPacketPlayerAccessor) packetIn).setPitch(rotation.pitch);

            //update the cache
            RotationCache.serverYaw = ((CPacketPlayerAccessor) packetIn).getYaw();
            RotationCache.serverPitch = ((CPacketPlayerAccessor) packetIn).getPitch();
        }
    }
}
