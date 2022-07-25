package me.oragejuice.oragehack.client.api.event.network.server;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.server.SPacketPlayerPosLook;

public class SPacketPlayerPosLookEvent extends AbstractCancelable {
    public SPacketPlayerPosLookEvent(SPacketPlayerPosLook packet) {
        this.packet = packet;
    }

    public SPacketPlayerPosLook packet;
}
