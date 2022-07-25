package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketClickWindow;

public class CPacketClickWindowEvent extends AbstractCancelable {
    public CPacketClickWindowEvent(CPacketClickWindow packet) {
        this.packet = packet;
    }

    public CPacketClickWindow packet;

}
