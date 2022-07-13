package Classes;

import me.oragejuice.eventbus.EventHandler;

public class TestFeature {


    @EventHandler
    public void onThing(TestEvent event){
        System.out.println("THING!!!");
    }
}
