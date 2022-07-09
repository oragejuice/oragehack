package me.oragejuice.oragehack.argument;

import java.util.List;

public interface Argument {

    /**
     * Adds the "components" of this argument to the specified list.
     *
     * @see ClassifiedArgument#addToList(List)
     * @see SingularArgument#addToList(List)
     *
     * @param arguments The argument list
     */
    void addToList(List<String> arguments);

    /**
     * Returns whether or not this argument conflicts with the specified one. In the context
     * of singular arguments, this means that the singular argument itself matches, in the context
     * of classified arguments, this means that the classifier matches.
     *
     * @param argument Another argument
     * @return Whether or not this argument conflicts
     */
    boolean conflicts(Argument argument);
}
