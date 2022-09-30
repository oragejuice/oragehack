package me.oragejuice.oragehack.client.api.settings.premade;

import me.oragejuice.oragehack.client.api.settings.GenericSetting;
import me.oragejuice.oragehack.client.api.settings.ISetting;

import java.util.Arrays;

public class EnumSetting<T extends Enum<?> > extends GenericSetting<T> implements ISetting<T> {

    public EnumSetting(String name, T value) {
        super(name, value);
    }

    public void increment() {
        Enum<?> enumVal = (Enum<?>) value;
        // search all values
        String[] values = Arrays.stream(enumVal.getClass().getEnumConstants()).map(Enum::name).toArray(String[]::new);
        int index = enumVal.ordinal() + 1 > values.length - 1 ? 0 : enumVal.ordinal() + 1;

        // use value index
        this.value =  (T) Enum.valueOf(enumVal.getClass(), values[index]);
    }

    public void decrement() {
        Enum<?> enumVal = (Enum<?>) value;
        // search all values
        String[] values = Arrays.stream(enumVal.getClass().getEnumConstants()).map(Enum::name).toArray(String[]::new);
        int index = enumVal.ordinal() - 1 < 0 ? values.length - 1 : enumVal.ordinal() - 1;

        // use value index
        this.value =  (T) Enum.valueOf(enumVal.getClass(), values[index]);
    }

    @Override
    public String toString() {
        return this.value.toString().toLowerCase();
    }

    @Override
    public void setValue(String value){
        Enum<?> enumVal = (Enum<?>) this.value;
        //String[] values = Arrays.stream(enumVal.getClass().getEnumConstants()).map(Enum::name).toArray(String[]::new);
        for (Enum<?> s : enumVal.getClass().getEnumConstants()) {
            if (s.toString().equalsIgnoreCase(value)) {
                this.value = (T) s;
            }
        }

    }


}
