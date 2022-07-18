
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.settings.Configurator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lwjgl.input.Keyboard;

public class ConfigTest {

    Oragehack oragehack;

    @Test
    @DisplayName("Saving and loading config")
    public void loadOragehack(){
        oragehack = new Oragehack();
        oragehack.INSTANCE.init();
    }

    @Test
    @DisplayName("match check")
    public void bloop(){
        String s = "\t\tfloatSetting: 1.0";
        Assertions.assertTrue(s.contains("\u0009\u0009"));
    }

    @Test
    @DisplayName("Count occurences")
    public void count(){
        String s = "\t\ttestBooleanSetting2: true";
        Assertions.assertEquals(Configurator.countTabs(s), 2);

        String s1 = "\ttestBooleanSetting2: true";
        Assertions.assertEquals(Configurator.countTabs(s1), 1);

        String s2 = "\t\t\ttestBooleanSetting2: true";
        Assertions.assertEquals(Configurator.countTabs(s2), 3);
    }

    @Test
    @DisplayName("keybaord")
    public void getKey(){
        Assertions.assertEquals(Keyboard.getKeyIndex("P"), 25);
    }




}
