package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketClientSettings;

public class CPacketClientSettingsEvent extends AbstractCancelable {
    public CPacketClientSettingsEvent(CPacketClientSettings packet) {
        this.packet = packet;
    }

    public CPacketClientSettings packet;
}
