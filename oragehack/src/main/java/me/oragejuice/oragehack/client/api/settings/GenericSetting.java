package me.oragejuice.oragehack.client.api.settings;

import me.oragejuice.oragehack.Oragehack;
import me.oragejuice.oragehack.client.api.INameable;

import java.util.ArrayList;

public class GenericSetting<T> implements INameable, ISubSetting, ISetting<T> {

    GenericSetting parent;

    String name;
    protected T value;

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

    @Override
    public void setValue(T value){
        this.value = value;
    }


    public void registerChild(GenericSetting... children){
        for (GenericSetting child : children) {
            if(!childrenSettings.contains(child)) {
                childrenSettings.add(child);
                child.parent = this;
            }
        }
    }

    public GenericSetting getParent() {
        return parent;
    }

    public boolean hasParent(){
        return this.parent != null;
    }

    @Override
    public ArrayList<GenericSetting> getSettings() {
        return childrenSettings;
    }

    public void setValue(String string){

        if(value instanceof Double){
            setValue((T) Double.valueOf(string));
        } else if(value instanceof Float){
            setValue((T) Float.valueOf(string));
        } else if(value instanceof Boolean) {
            setValue((T) Boolean.valueOf(string));
        } else if (value instanceof Integer){
            setValue((T) Integer.valueOf(string));
        } else {
            Oragehack.LOGGER.error("couldntt set: {} - {}", value, this.name);
            return;
        }
    }

    public void setParent(GenericSetting parent) {
        this.parent = parent;
        parent.registerChild(this);
    }

    @Override
    public String toString(){
        return this.name;
    }

    public String getFullyQualifiedName(){
        if(hasParent()){
            return parent.getFullyQualifiedName() + "." + this.name;
        } else {
            return name;
        }
    }

}
