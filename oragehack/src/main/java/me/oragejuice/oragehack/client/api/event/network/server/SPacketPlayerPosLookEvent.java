package me.oragejuice.oragehack.client.api.event.network.server;

import me.oragejuice.eventbus.AbstractCancelable;
import me.oragejuice.oragehack.client.api.event.PacketEvent;
import net.minecraft.network.play.server.SPacketPlayerPosLook;

public class SPacketPlayerPosLookEvent extends PacketEvent.Incoming {
    public SPacketPlayerPosLookEvent(SPacketPlayerPosLook packet) {
        super(packet);
    }



}
