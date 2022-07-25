package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketCustomPayload;

public class CPacketCustomPayloadEvent extends AbstractCancelable {
    public CPacketCustomPayloadEvent(CPacketCustomPayload packet) {
        this.packet = packet;
    }

    public CPacketCustomPayload packet;
}
