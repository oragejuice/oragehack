package me.oragejuice.oragehack.client.api.event.network.client;

import me.oragejuice.eventbus.AbstractCancelable;
import net.minecraft.network.play.client.CPacketConfirmTransaction;

public class CPacketConfirmTransactionEvent extends AbstractCancelable {
    public CPacketConfirmTransactionEvent(CPacketConfirmTransaction packet) {
        this.packet = packet;
    }

    public CPacketConfirmTransaction packet;
}
