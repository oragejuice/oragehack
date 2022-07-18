package me.oragejuice.oragehack.client.api.settings;

public class SettingBuilder<T> {

    GenericSetting<T> genericSetting;

    public SettingBuilder(String name, T value){
        genericSetting = new GenericSetting<>(name, value);
    }

    public GenericSetting<T> setMax(T max) {
        genericSetting.setMax(max);
        return genericSetting;
    }

    public GenericSetting<T> setMin(T min) {
        genericSetting.setMin(min);
        return genericSetting;
    }

    public GenericSetting<T> setRange(T min, T max){
        genericSetting.setMin(min);
        genericSetting.setMax(max);
        return genericSetting;
    }

    public GenericSetting<T> setParent(GenericSetting parent) {
        parent.registerChild(genericSetting);
        return genericSetting;
    }


}
