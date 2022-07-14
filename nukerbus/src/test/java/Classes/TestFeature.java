package Classes;

import me.oragejuice.eventbus.EventHandler;

public class TestFeature {


    @EventHandler
    public void onThing(TestEvent event){
        System.out.println("THING!!!");
    }

    @EventHandler
    public void onSecondThing(SecondTestEvent event){
        System.out.println("SECOND THING!");
    }

}
