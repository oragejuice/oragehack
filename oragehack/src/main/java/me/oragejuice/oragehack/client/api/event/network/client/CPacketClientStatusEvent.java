package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketClientStatus;

public class CPacketClientStatusEvent extends AbstractCancelable {
    public CPacketClientStatusEvent(CPacketClientStatus packet) {
        this.packet = packet;
    }

    public CPacketClientStatus packet;

}
