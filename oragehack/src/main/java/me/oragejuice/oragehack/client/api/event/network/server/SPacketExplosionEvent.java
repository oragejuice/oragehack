package me.oragejuice.oragehack.client.api.event.network.server;

import me.oragejuice.oragehack.client.api.event.PacketEvent;
import net.minecraft.network.play.server.SPacketExplosion;

public class SPacketExplosionEvent extends PacketEvent.Incoming {


    public SPacketExplosionEvent(SPacketExplosion packet) {
        super(packet);
    }
}
