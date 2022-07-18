

import Classes.SecondTestEvent;
import Classes.SpeedFeature;
import Classes.TestEvent;
import Classes.TestFeature;
import me.oragejuice.eventbus.ASMListener;
import me.oragejuice.eventbus.EventInvoker;
import me.oragejuice.eventbus.EventManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


class EventBusTest {

    EventManager EVENT_BUS = new EventManager();

    @Test
    @DisplayName("Event Testing!")
    void testSubscriber() {
        EVENT_BUS.subscribe(new TestFeature());
        System.out.println("Subscribed TestFeature :D");
        EVENT_BUS.post(new TestEvent());
    }

    @Test
    @DisplayName("Doing multiple calls tessting")
    void multi(){
        EVENT_BUS.subscribe(new TestFeature());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.subscribe(new TestFeature());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.subscribe(new TestFeature());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.post(new TestEvent());
    }

    @Test
    @DisplayName("Doing multiple events tessting")
    void twoEvents(){
        EVENT_BUS.subscribe(new TestFeature());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.post(new SecondTestEvent());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.subscribe(new TestFeature());
        EVENT_BUS.post(new SecondTestEvent());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.subscribe(new TestFeature());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.post(new TestEvent());
        EVENT_BUS.post(new SecondTestEvent());
    }

    @Test
    @DisplayName("subscribe and unsubscrie")
    void both(){
        System.out.println("subscribe and unsubscrie");
        EventManager EVENT_BUS_NEW = new EventManager();
        TestFeature specialFeature = new TestFeature();
        EVENT_BUS_NEW.post(new TestEvent());
        EVENT_BUS_NEW.subscribe(specialFeature);
        EVENT_BUS_NEW.post(new TestEvent());
        EVENT_BUS_NEW.unsubscribe(specialFeature);
        EVENT_BUS_NEW.post(new TestEvent());
        System.out.println("Should only have 1 here");
    }

    @Test
    @DisplayName("Speed Test")
    void speed(){
        EVENT_BUS.subscribe(new TestFeature());
        System.out.println("1 million events");
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            EVENT_BUS.post(new SpeedFeature());
        }
        long diff = time - System.currentTimeMillis();
        System.out.println(String.format("Took %d millis", diff));

        System.out.println("Context");
        time = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            int x = 1+1;
        }
        diff = time - System.currentTimeMillis();
        System.out.println(String.format("Took %d millis", diff));
    }

    /**
     * @throws NoSuchMethodException
     */

    @Test
    @DisplayName("Opti Speed Test")
    void speedOpti() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //EventManager E = new EventManager();
       SpeedFeature s = new SpeedFeature();
        ASMListener a = new ASMListener(new SpeedFeature(), SpeedFeature.class.getMethod("onThing", TestEvent.class));
        EventInvoker p = a.getInvoker(SpeedFeature.class.getMethod("onThing", TestEvent.class));
        //EventInvoker eventInvoker = SpeedFeature::onThing;.
        System.out.println("1 million events");
        TestEvent t = new TestEvent();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            p.invoke(s, t);
        }
        long diff = time - System.currentTimeMillis();
        System.out.println(String.format("Took %d millis", diff));
    }

}
