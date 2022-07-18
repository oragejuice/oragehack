package me.oragejuice.oragehack.client.api.settings.premade;

import me.oragejuice.oragehack.client.api.settings.GenericSetting;

public class BooleanSetting extends GenericSetting<Boolean> {

    public BooleanSetting(String name, Boolean value) {
        super(name, value);
    }

    @Override
    public void setValue(String value){
        this.setValue(Boolean.parseBoolean(value));
    }

    public void toggle() {
        this.setValue(!this.getValue());
    }
}
