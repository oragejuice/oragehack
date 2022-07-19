package me.oragejuice.commandDispatcher;

import java.util.Arrays;

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
        if(!matches(args[depth])) {
            System.out.println("doesnt match");
            //Doesnt match with thee regex so will ask itself for suggestions
            if(children == null || children.length == 0){
                System.out.println("no children");
                for (String suggestion : suggestions) {
                    if (args.length >= depth + 2) {
                        //if trailing search
                        if (DamerauLevenshtein.calculate(suggestion, args[depth + 1]) < 4) {
                            matches = concatWithArrayCopy(matches, new String[]{suggestion});
                        }
                    } else {
                        //if is end of message need to show options
                        if (DamerauLevenshtein.calculate(suggestion, args[depth]) < 4) {
                            matches = concatWithArrayCopy(matches, new String[]{suggestion});
                        }
                    }
                }
                return matches;
                //return this.suggestions;
            }
            for (ArgumentNode child : children) {
                for (String suggestion : child.getSuggestions()) {
                    //if the next argumen has even started being typed (and so needs filtering)
                    if (args.length >= depth + 2) {
                        if (DamerauLevenshtein.calculate(suggestion, args[depth + 1]) < 4)
                            matches = concatWithArrayCopy(matches, new String[]{suggestion});
                    } else {
                        matches = concatWithArrayCopy(matches, new String[]{suggestion});
                    }
                }
            }
        } else {
            System.out.println("does match");
            //if it does match
            if (this.depth == args.length-1){
                for (ArgumentNode child : children) {
                    for (String suggestion : child.getSuggestions()) {
                        //if the next argumen has even started being typed (and so needs filtering)
                        if (args.length >= depth + 2) {
                            if (DamerauLevenshtein.calculate(suggestion, args[depth + 1]) < 4)
                                matches = concatWithArrayCopy(matches, new String[]{suggestion});
                        } else {
                            matches = concatWithArrayCopy(matches, new String[]{suggestion});
                        }
                    }
                }
                return matches;
            }

            if(this.depth <= args.length) {
                for (ArgumentNode child : children) {
                    matches = concatWithArrayCopy(matches, child.getSuggestions(args));
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
