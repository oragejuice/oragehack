import Classes.TestEvent;
import Classes.TestFeature;
import me.oragejuice.eventbus.ASMListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;


public class TestASMListener {


    @Test
    @DisplayName("ASMListener Constructor")
    void testConstructor(){
        TestFeature feature = new TestFeature();

        Method method = null;

        try {
            method = feature.getClass().getMethod("onThing", TestEvent.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        ASMListener asmListener = new ASMListener(feature, method);
    }

    @Test
    @DisplayName("ASMListener createEnvoker")
    void testInvokerCreator(){

        TestFeature feature = new TestFeature();

        Method method = null;

        try {
            method = feature.getClass().getMethod("onThing", TestEvent.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        /**
         * Checking that it's not null
         */
        Assertions.assertNotNull(ASMListener.createInvoker(method));
    }

    @Test
    @DisplayName("getInvoker non null")
    void getInvokerTest(){
        TestFeature feature = new TestFeature();
        Method method = null;
        try {
            method = feature.getClass().getMethod("onThing", TestEvent.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        ASMListener asmListener = new ASMListener(feature, method);
        Assertions.assertNotNull(asmListener.getInvoker(method));

    }


}


