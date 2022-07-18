package me.oragejuice.oragehack.client.api.settings.premade;

import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import org.lwjgl.input.Keyboard;

public class BindSetting extends GenericSetting<Integer> {

    public BindSetting(String name, Integer value) {
        super(name, value);
    }


    // SHOULD BE FORMATTED LIKE P
    @Override
    public void setValue(String value){
        Keyboard.getKeyIndex(value);
    }

}
