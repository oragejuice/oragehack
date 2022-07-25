package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketCreativeInventoryAction;

public class CPacketCreativeInventoryActionEvent extends AbstractCancelable {
    public CPacketCreativeInventoryActionEvent(CPacketCreativeInventoryAction packet) {
        this.packet = packet;
    }

    public CPacketCreativeInventoryAction packet;

}
