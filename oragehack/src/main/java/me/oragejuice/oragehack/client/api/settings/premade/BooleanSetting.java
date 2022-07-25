package me.oragejuice.oragehack.client.api.settings.premade;

import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import me.oragejuice.oragehack.client.api.settings.ISetting;

public class BooleanSetting extends GenericSetting<Boolean> implements ISetting<Boolean> {

    public BooleanSetting(String name, Boolean value, GenericSetting... children) {
        super(name, value);
        registerChild(children);

    }

    @Override
    public void setValue(String value){
        this.setValue(Boolean.parseBoolean(value));
    }

    public void toggle() {
        this.setValue(!this.getValue());
    }
}
