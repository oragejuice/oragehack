package me.oragejuice.oragehack.mixins.network.packets;

import net.minecraft.network.play.client.CPacketPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CPacketPlayer.class)
public interface CPacketPlayerAccessor {

    @Accessor("x")
    double getX();

    @Accessor("y")
    double getY();

    @Accessor("z")
    double getZ();

    @Accessor("yaw")
    float getYaw();

    @Accessor("pitch")
    float getPitch();

    @Accessor("x")
    void setX(double x);

    @Accessor("y")
    void setY(double y);

    @Accessor("z")
    void setZ(double z);

    @Accessor("yaw")
    void setYaw(float yaw);

    @Accessor("pitch")
    void setPitch(float pitch);

    @Accessor("onGround")
    void setOnGround(boolean onGround);

    @Accessor("moving")
    boolean isMoving();

    @Accessor("rotating")
    boolean isRotating();
}
