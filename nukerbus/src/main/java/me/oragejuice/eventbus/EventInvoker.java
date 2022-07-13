package me.oragejuice.eventbus;

public interface EventInvoker {

    void invoke(Object owner, Object event);
}
