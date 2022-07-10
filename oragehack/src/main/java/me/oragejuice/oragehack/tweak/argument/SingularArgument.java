package me.oragejuice.oragehack.tweak.argument;

import java.util.List;

/**
 * @author Brady
 * @since 10/9/2018
 */
public final class SingularArgument implements Argument {

    private final String value;

    SingularArgument(String value) {
        this.value = value;
    }

    @Override
    public void addToList(List<String> arguments) {
        arguments.add(this.value);
    }

    @Override
    public boolean conflicts(Argument argument) {
        if (!(argument instanceof SingularArgument))
            return false;

        return ((SingularArgument) argument).value.equals(this.value);
    }
}
