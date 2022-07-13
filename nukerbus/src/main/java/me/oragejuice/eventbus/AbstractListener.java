package me.oragejuice.eventbus;

import java.util.function.Consumer;

public abstract class AbstractListener implements Consumer<Object> {

    protected Class target;
    int priority = 0;

    public Class getTarget(){
        return target;
    }
}
