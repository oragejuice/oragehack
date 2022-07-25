package me.oragejuice.oragehack.client.api.settings;

public interface ISetting<T> {

    void setValue(T value);

    T getValue();

}
