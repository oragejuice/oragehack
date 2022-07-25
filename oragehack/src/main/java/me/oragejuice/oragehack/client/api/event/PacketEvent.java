package me.oragejuice.oragehack.client.api.event;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.play.INetHandlerPlayServer;

public class PacketEvent extends AbstractCancelable {
    public Packet<?> packet;
    public PacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    public static class Outgoing extends AbstractCancelable{
        public Packet<?> packet;
        public Outgoing(Packet<?> packet) {
            this.packet = packet;
        }
    }

    public static class Incoming extends AbstractCancelable{
        public Packet<?> packet;
        public Incoming(Packet<?> packet) {
            this.packet = packet;
        }
    }
}
