package me.oragejuice.oragehack.client.api.event.network.server;

import me.oragejuice.oragehack.client.api.event.PacketEvent;
import net.minecraft.network.play.server.SPacketEntityVelocity;

public class SPacketEntityVelocityEvent extends PacketEvent.Incoming {
    public SPacketEntityVelocityEvent(SPacketEntityVelocity packet) {
        super(packet);
    }
}
