package me.oragejuice.oragehack.mixins.network;


import io.netty.channel.ChannelHandlerContext;
import me.oragejuice.oragehack.client.Globals;
import me.oragejuice.oragehack.client.api.event.PacketEvent;
import me.oragejuice.oragehack.client.api.event.network.server.SPacketEntityVelocityEvent;
import me.oragejuice.oragehack.client.api.event.network.server.SPacketExplosionEvent;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketExplosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NetworkManager.class)
public abstract class NetworkManagerMixin implements Globals {


    @Inject(method = "sendPacket(Lnet/minecraft/network/Packet;)V", at = @At("HEAD"), cancellable = true)
    private void sendPacket(Packet<?> packet, CallbackInfo callbackInfo) {
        if (mc.player == null) return;
        PacketEvent.Outgoing event = new PacketEvent.Outgoing(packet);
        EVENT_BUS.post(event);
        if( event.isCancelled()) callbackInfo.cancel();

    }

    @Inject(method = "channelRead0", at = @At("HEAD"), cancellable = true)
    private void onChannelRead(ChannelHandlerContext context, Packet<?> packet, CallbackInfo callbackInfo) {
        if (mc.player == null) return;
        PacketEvent.Incoming event;

        //individual packet events
        if(packet instanceof SPacketEntityVelocity){
            event = new SPacketEntityVelocityEvent((SPacketEntityVelocity) packet);
        } else if(packet instanceof SPacketExplosion)  {
            event = new SPacketExplosionEvent((SPacketExplosion) packet);
        } else {
            event = new PacketEvent.Incoming(packet);
        }
        EVENT_BUS.post(event);
        if( event.isCancelled()) callbackInfo.cancel();
    }
}
