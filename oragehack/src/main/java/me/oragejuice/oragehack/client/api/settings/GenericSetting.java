package me.oragejuice.oragehack.client.api.settings;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.INameable;

import java.util.ArrayList;

public class GenericSetting<T> implements INameable, ISubSetting {

    GenericSetting parent;

    String name;
    T value;

    T min;
    T max;

    public GenericSetting(String name, T value){
        this.name = name;
        this.value = value;
    }

    private ArrayList<GenericSetting> childrenSettings = new ArrayList<GenericSetting>();

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

    public void setValue(T value){
        this.value = value;
    }

    public void registerChild(GenericSetting... children){
        for (GenericSetting child : children) {
            childrenSettings.add(child);
            child.parent = this;
        }
    }

    public GenericSetting getParent() {
        return parent;
    }

    @Override
    public ArrayList<GenericSetting> getSettings() {
        return childrenSettings;
    }

    public void setValue(String string){

        if(value instanceof Double){
            this.value = (T) Double.valueOf(string);
        } else if(value instanceof Float){
            this.value = (T) Float.valueOf(string);
        } else if(value instanceof Boolean){
            this.value = (T) Boolean.valueOf(string);
        }
        Oragehack.LOGGER.info("value set to: {} - {}", value, this.name);
    }

    @Override
    public String toString(){
        return this.name;
    }

}
