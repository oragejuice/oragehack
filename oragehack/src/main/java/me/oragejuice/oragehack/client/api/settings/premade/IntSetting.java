package me.oragejuice.oragehack.client.api.settings.premade;

import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import me.oragejuice.oragehack.client.api.settings.ISetting;

public class IntSetting extends GenericSetting<Integer> implements ISetting<Integer> {

    public IntSetting(String name, Integer value, Integer min, Integer max) {
        super(name, value);
    }

    @Override
    public void setValue(Integer value) {
        if(getMax() != null && getMin() != null) {
            this.value = Math.min(getMax(), Math.max(getMin(), value));
        } else {
            this.value = value;
        }
    }

    @Override
    public Integer getValue(){
        return this.value;
    }

}
