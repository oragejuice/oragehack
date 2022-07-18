import Classes.TestEvent;
import Classes.TestFeature;
import me.oragejuice.eventbus.ASMListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
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
            Assertions.fail();
        }
        ASMListener asmListener = new ASMListener(feature, method);
    }

    /*
    @Test
    @DisplayName("ASMListener createEnvoker")
    void testInvokerCreator(){

        TestFeature feature = new TestFeature();

        Method method = null;

        try {
            method = feature.getClass().getMethod("onThing", TestEvent.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Assertions.fail();
        }

        try {
            Assertions.assertNotNull(ASMListener.class.getMethod("createInvoker", Method.class).invoke(method));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            Assertions.fail();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            Assertions.fail();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
    */


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


