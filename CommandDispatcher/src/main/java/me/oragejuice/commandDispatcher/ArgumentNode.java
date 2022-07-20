package me.oragejuice.commandDispatcher;

import java.util.Arrays;
import java.util.Locale;

public class ArgumentNode {

    final String regex;
    final String[] suggestions; //the syntax suggestions that this node can give
    final int depth; //the arg index this node should handle
    ArgumentNode[] children = new ArgumentNode[]{};
    ArgumentOperator executor = null;

    public ArgumentNode(String regex, String[] suggestions, int depth, ArgumentNode... children) {
        this.regex = regex;
        this.suggestions = suggestions;
        this.children = children;
        this.depth = depth;
    }

    public ArgumentNode(String regex, String[] suggestions, int depth, ArgumentOperator a) {
        this.regex = regex;
        this.suggestions = suggestions;
        this.children = children;
        this.depth = depth;
        setExecutor(a);
    }

    public String[] getSuggestions() {
        return suggestions;
    }

    public ArgumentNode[] getChildren() {
        return children;
    }

    public void setExecutor(ArgumentOperator a){
        executor = a;
    }

    public ArgumentNode setExecutorBuilder(ArgumentOperator a){
        this.setExecutor(a);
        return this;
    }

    public boolean matches(String argument){
        return argument.matches(regex);
    }


    public String[] getSuggestions(String[] args){
        String[] matches = new String[]{};
        //if matches then this argument is valid
        //System.out.println("args length: " + args.length);
        if(matches(args[depth])){
            for (ArgumentNode child : children) {
               // System.out.println("child suggestions called " + child.regex + " from: " + this.regex);
                if((child.depth < args.length)) {
                    //System.out.println("depth smaller than args length");
                    matches = concatWithArrayCopy(matches, child.getSuggestions(args));
                } else {
                    //System.out.println("depth greater than args length");
                    matches = concatWithArrayCopy(matches, child.getSuggestions());
                }
            }

        } //Invalid argument at this depth, return filtered list of given suggestions
        else {
            for (String suggestion : suggestions) {
                if (DamerauLevenshtein.calculate(suggestion, args[depth]) < 2 || suggestion.toLowerCase().contains(args[depth].toLowerCase())) {
                    matches = concatWithArrayCopy(matches, new String[]{suggestion});
                }
            }
        }
        return matches;
    }

    public void dispatch(String[] args){
        if(matches(args[depth])){
            if (executor != null){
                executor.exec(args);
            }
            for (ArgumentNode child : children) {
                child.dispatch(args);
            }
        }
    }

    private static <T> T[] concatWithArrayCopy(T[] array1, T[] array2) {
        T[] result = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }


}
