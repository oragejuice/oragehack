

import Classes.TestEvent;
import Classes.TestFeature;
import me.oragejuice.eventbus.EventManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


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
    void speed(){
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

}
