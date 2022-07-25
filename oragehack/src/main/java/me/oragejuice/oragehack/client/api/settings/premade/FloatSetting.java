package me.oragejuice.oragehack.client.api.settings.premade;

import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import me.oragejuice.oragehack.client.api.settings.ISetting;

public class FloatSetting extends GenericSetting<Float> implements ISetting<Float> {

    /**
     * @param name setting name
     * @param value value
     * @param min min
     * @param max max
     */
    public FloatSetting(String name, Float value, Float min, Float max) {
        super(name, value);
        super.setMax(max);
        super.setMin(min);
    }

    /**
     * @param name setting name
     * @param value setting valye
     * @param children list of child settings
     */
    public FloatSetting(String name, Float value, GenericSetting... children) {
        super(name, value);
        registerChild(children);
    }

    /**
     * @param name setting name
     * @param value setting value
     * @param parent parent of setting
     */
    public FloatSetting(String name, Float value, GenericSetting parent) {
        super(name, value);
        setParent(parent);
    }


    @Override
    public void setValue(Float value) {
        if(getMax() != null && getMin() != null) {
            setValue(Math.min(getMax(), Math.max(getMin(), value)));
        } else {
            setValue(value);
        }
    }

}
