package me.oragejuice.commandDispatcher;

import java.util.ArrayList;
import java.util.Arrays;

public class CommandDispatcher {

    ArrayList<ArgumentNode> commands = new ArrayList<>();

    public void register(ArgumentNode node){
        commands.add(node);
    }

    public void dispatch(String command){
        String[] arguments = command.split(" ");
        for (ArgumentNode argumentNode : commands) {
            argumentNode.dispatch(arguments);
        }
    }

    public String[] getSuggestion(String command){
        String[] arguments = command.split(" ");
        //System.out.println("arguments length: " + arguments.length);
        String[] matches = new String[]{};
        for (ArgumentNode argumentNode : commands) {
            matches = concatWithArrayCopy(matches, argumentNode.getSuggestions(arguments));
        }
        return matches;
    }


    private static <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }

}
