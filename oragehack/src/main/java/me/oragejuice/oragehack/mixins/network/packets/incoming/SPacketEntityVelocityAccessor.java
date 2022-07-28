package me.oragejuice.oragehack.mixins.network.packets.incoming;

import net.minecraft.network.play.server.SPacketEntityVelocity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SPacketEntityVelocity.class)
public interface SPacketEntityVelocityAccessor {

    @Accessor("motionX")
    int getMotionX();

    @Accessor("motionY")
    int getMotionY();

    @Accessor("motionZ")
    int getMotionZ();

    @Accessor("motionX")
    void setMotionX(int x);

    @Accessor("motionY")
    void setMotionY(int y);

    @Accessor("motionZ")
    void setMotionZ(int z);
}