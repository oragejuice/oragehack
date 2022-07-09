package me.oragejuice.oragehack.argument;

import java.util.List;

public final class ClassifiedArgument implements Argument {

    private final String classifier;
    private final String value;

    ClassifiedArgument(String classifier, String value) {
        this.classifier = classifier;
        this.value = value;
    }

    @Override
    public final void addToList(List<String> arguments) {
        arguments.add(classifier);
        arguments.add(value);
    }

    @Override
    public final boolean conflicts(Argument argument) {
        if (!(argument instanceof ClassifiedArgument))
            return false;

        return ((ClassifiedArgument) argument).classifier.equals(this.classifier);
    }
}
