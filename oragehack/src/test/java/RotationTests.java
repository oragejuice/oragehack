import me.oragejuice.oragehack.client.api.rotation.Rotation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class RotationTests {

    public static float getAngleDifference(final float a, final float b) {
        return ((((a - b) % 360F) + 540F) % 360F) - 180F;
    }

    public static float limitAngleChange(final float currentRotation, final float targetRotation, final float turnSpeed) {
        final float angleDifference = getAngleDifference(targetRotation, currentRotation);
        return currentRotation + (angleDifference > turnSpeed ? turnSpeed : Math.max(angleDifference, -turnSpeed));
    }

    @Test
    @DisplayName("YawStep Test")
    void yawstepCheck(){

        float f = limitAngleChange(-10,180,20);
        Assertions.assertEquals(-30,f);
    }
}
