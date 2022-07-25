package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketConfirmTeleport;

public class CPacketConfirmTeleportEvent extends AbstractCancelable {
    public CPacketConfirmTeleportEvent(CPacketConfirmTeleport packet) {
        this.packet = packet;
    }

    public CPacketConfirmTeleport packet;
}
