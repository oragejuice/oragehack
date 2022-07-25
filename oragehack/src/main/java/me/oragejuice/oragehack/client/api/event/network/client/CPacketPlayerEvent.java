package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketPlayer;

public class CPacketPlayerEvent extends AbstractCancelable {

    public CPacketPlayerEvent(CPacketPlayer packet) {
        this.packet = packet;
    }

    public CPacketPlayer packet;
}
