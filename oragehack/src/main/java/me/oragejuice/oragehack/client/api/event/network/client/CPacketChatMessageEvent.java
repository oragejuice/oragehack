package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketChatMessage;

public class CPacketChatMessageEvent extends AbstractCancelable {
    public CPacketChatMessageEvent(CPacketChatMessage packet) {
        this.packet = packet;
    }

    public CPacketChatMessage packet;

}
