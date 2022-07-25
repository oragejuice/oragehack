package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketCloseWindow;

public class CPacketCloseWindowEvent extends AbstractCancelable {
    public CPacketCloseWindowEvent(CPacketCloseWindow packet) {
        this.packet = packet;
    }

    public CPacketCloseWindow packet;

}
