
import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.settings.Configurator;
import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.lwjgl.input.Keyboard;

public class ConfigTest {

    Oragehack oragehack;

    @Test
    @Order(0)
    @DisplayName("Saving and loading config")
    public void loadOragehack(){
        oragehack = new Oragehack();
        oragehack.INSTANCE.init();
    }

    @Test
    @Order(1)
    @DisplayName("Flatten test")
    void flatten(){
        Assertions.assertNotNull(Oragehack.INSTANCE.featureManager, "featureManager is null");
        Assertions.assertNotNull(Oragehack.INSTANCE.featureManager.testFeature, "testFeature is null");
        Assertions.assertNotNull(Oragehack.INSTANCE.featureManager.testFeature.getFlattenedSettings(), "Flattened strings is null");
        for (GenericSetting flattenedSetting : Oragehack.INSTANCE.featureManager.testFeature.getFlattenedSettings()) {
            if(flattenedSetting.hasParent()){
                System.out.println(flattenedSetting.getName() + "-> child of ->" + flattenedSetting.getParent().getName());
            } else {
                System.out.println(flattenedSetting.getName() + "is an oprhan");
            }
        }
    }

    @Test
    @Order(5)
    void fullyQalifiedName(){
        for (GenericSetting flattenedSetting : Oragehack.INSTANCE.featureManager.testFeature.getFlattenedSettings()) {
            System.out.println(flattenedSetting.getFullyQualifiedName());
        }
    }

    @Test
    @Order(2)
    @DisplayName("match check")
    public void bloop(){
        String s = "\t\tfloatSetting: 1.0";
        Assertions.assertTrue(s.contains("\u0009\u0009"));
    }

    @Test
    @Order(3)
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
    @Order(4)
    @DisplayName("keybaord")
    public void getKey(){
        Assertions.assertEquals(Keyboard.getKeyIndex("P"), 25);
    }




}
