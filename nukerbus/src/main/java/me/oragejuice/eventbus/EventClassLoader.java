package me.oragejuice.eventbus;

public class EventClassLoader extends ClassLoader {

    public EventClassLoader(ClassLoader parent){
        super(parent);
    }

    public Class defineClass(String name, byte[] data ){
        return defineClass(name, data, 0, data.length);
    }
}
