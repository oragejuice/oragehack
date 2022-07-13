package me.oragejuice.oragehack.client.api.settings;

import me.oragejuice.oragehack.client.api.INameable;

public class GenericSetting<T> implements INameable {

    GenericSetting parent;

    String name;
    T value;

    // for values that need ranges set
    T min;
    T max;

    public GenericSetting(String name, T value){
        this.name = name;
        this.value = value;
    }

    public void map(SettingMonad operator){
        this.value = (T) operator.map(this).value;
    }


    public void setMax(T max) {
        this.max = max;
    }

    public void setMin(T min) {
        this.min = min;
    }

    @Override
    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    public T getMin() {
        return min;
    }

    public T getMax() {
        return max;
    }



}
