package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketAnimation;

public class CPacketAnimationEvent extends AbstractCancelable {
    public CPacketAnimationEvent(CPacketAnimation packet) {
        this.packet = packet;
    }

    public CPacketAnimation packet;
}
