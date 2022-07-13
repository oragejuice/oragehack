package me.oragejuice.eventbus;

import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class EventManager {
    /*
    If I start to see issues with Concurrency then replace with Concorruent hashmaps
     */
    private final ConcurrentHashMap<Class<?>, ArrayList<AbstractListener>> listenerMultimap = new ConcurrentHashMap<>();
    private final Multimap<Object, AbstractListener> parentListeners = Multimaps.newSetMultimap(new ConcurrentHashMap<>(), ConcurrentHashMap::newKeySet);
    public static final Multimap<String, AbstractListener> cmListeners = Multimaps.newSetMultimap(new ConcurrentHashMap<>(), ConcurrentHashMap::newKeySet);

    public void post(Object event) {
        final ArrayList<AbstractListener> listenerList = listenerMultimap.get(event.getClass());
        if (listenerList != null) {
            for (AbstractListener listener : listenerList) {
                try {
                    listener.accept(event);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void subscribe(Object obj) {

        Arrays.stream(obj.getClass().getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(EventHandler.class) && m.getParameterCount() == 1)
                .forEach(m -> registerAsListener(obj, m, m.getAnnotation(EventHandler.class).value()));
        if (cmListeners.containsKey(obj.getClass().getName())) {
            for (AbstractListener listener : cmListeners.get(obj.getClass().getName())) {
                if (!parentListeners.containsEntry(obj, listener)) {
                    parentListeners.get(obj).add(listener);
                }
                if (!listenerMultimap.containsKey(listener.getTarget())) {
                    listenerMultimap.put(listener.getTarget(), new ArrayList<>());
                }
                listenerMultimap.get(listener.getTarget()).add(listener);
                listenerMultimap.get(listener.getTarget()).sort(Comparator.comparingInt(abstractListener -> abstractListener.priority));
            }
        }
    }

    private void registerAsListener(Object object, Method method, int priority) {
        AbstractListener listener = new ASMListener(object, method);
        listener.priority = priority;

        if (!parentListeners.containsEntry(object, listener)) {
            parentListeners.get(object).add(listener);
        }
        if (!listenerMultimap.containsKey(listener.getTarget())) {
            listenerMultimap.put(listener.getTarget(), new ArrayList<>());
        }
        listenerMultimap.get(listener.getTarget()).add(listener);
        listenerMultimap.get(listener.getTarget()).sort((first, second) -> second.priority - first.priority);
    }

    public void unsubscribe(Object Object) {
        parentListeners.get(Object).forEach(l -> {
            ArrayList<Class<?>> classList = new ArrayList<>(listenerMultimap.keySet());
            for (Class<?> clazz : classList) {
                List list = listenerMultimap.get(clazz);
                if (list != null) {
                    list.remove(l);
                }
            }
        });
        parentListeners.removeAll(Object);
    }
}
