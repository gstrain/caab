package org.habitatmclean.table;

public class functions {
    public static boolean checkfor(String ... strings){
        boolean filled = true;
        for(String s : strings){
            if( s == null || s.isEmpty())
                filled = false;
        }
        return filled;
    }

    /**
     * converts the hidden input representation to the name of the html page it resides on
     * @param hiddenInput
     * @return
     */
    public static String hiddenInputToHTML(String hiddenInput) {
        switch (hiddenInput) {
            case "person":
                return"people";
            case "vendor":
                return"vendors";
            case "property":
                return"properties";
            case "zone":
                return"zone";
            case "house":
                return "houses";
            default:
                return hiddenInput;
        }
    }
}
