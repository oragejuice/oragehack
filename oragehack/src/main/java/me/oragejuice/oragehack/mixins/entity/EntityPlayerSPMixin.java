package me.oragejuice.oragehack.mixins.entity;


import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.Globals;
import me.oragejuice.oragehack.client.api.event.PlayerUpdateEvent;
import me.oragejuice.oragehack.client.api.rotation.RotationHandler;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.math.AxisAlignedBB;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author Oragejuice
 * @since 28/6/2022
 */

@Mixin(EntityPlayerSP.class)
public abstract class EntityPlayerSPMixin implements Globals {


    @Shadow @Final public NetHandlerPlayClient connection;
    @Shadow double lastReportedPosY;
    @Shadow double lastReportedPosZ;
    @Shadow float lastReportedYaw;
    @Shadow float lastReportedPitch;
    @Shadow int positionUpdateTicks;
    @Shadow double lastReportedPosX;
    @Shadow boolean prevOnGround;

    @Inject(method = "onUpdate", at = @At("HEAD"))
    public void onUpdateHook(CallbackInfo ci){
        //Oragehack.INSTANCE.eventBus.post(new PlayerUpdateEvent());
    }



    @Inject(method = "onUpdateWalkingPlayer",
            at = @At(value = "FIELD",
                    target = "Lnet/minecraft/client/entity/EntityPlayerSP;prevOnGround:Z",
            ordinal = 1,
            shift = At.Shift.BEFORE))
    public void onUpdateWalkingPlayer(CallbackInfo ci) {

        AxisAlignedBB axisalignedbb = mc.player.getEntityBoundingBox();

        //thanks mixins *enterprise code*
        double d0 = mc.player.posX - this.lastReportedPosX;
        double d1 = axisalignedbb.minY - this.lastReportedPosY;
        double d2 = mc.player.posZ - this.lastReportedPosZ;
        double d3 = (double)(mc.player.rotationYaw - this.lastReportedYaw);
        double d4 = (double)(mc.player.rotationPitch - this.lastReportedPitch);
        ++this.positionUpdateTicks;
        boolean flag2 = d0 * d0 + d1 * d1 + d2 * d2 > 9.0E-4D || this.positionUpdateTicks >= 20;
        boolean flag3 = d3 != 0.0D || d4 != 0.0D;

        if(RotationHandler.isSpoofing()
                && (!mc.player.isRiding() && !flag2 && !flag3
                && !(this.prevOnGround != mc.player.onGround))){ //making sure the packet hasnt been sent already this tick

            connection.sendPacket(new CPacketPlayer.PositionRotation(
                    mc.player.posX,                             //send a normal packet
                    mc.player.getEntityBoundingBox().minY,
                    mc.player.posZ,
                    RotationHandler.nextServerRotation.yaw,      // but change the rotations
                    RotationHandler.nextServerRotation.pitch,
                    mc.player.onGround));
        }
    }

    @Inject(method = "sendChatMessage", at = @At("HEAD"), cancellable = true)
    public void onChatSend(String message, CallbackInfo ci){
        if(message.startsWith("`")) {
            Oragehack.INSTANCE.commandDispatcher.dispatch(message.replaceFirst("`", ""));
            ci.cancel();
        }
    }

}
