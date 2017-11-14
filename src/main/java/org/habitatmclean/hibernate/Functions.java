package org.habitatmclean.hibernate;

public class Functions {
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
    public static String hiddenInputToHTMLPage(String hiddenInput) {
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
                return null;
        }
    }

    /**
     * returns the class name that a hidden input refers to
     * @param hiddenInput
     * @return
     */
    public static String hiddenInputToClass(String hiddenInput) {
        switch (hiddenInput) {
            case "person":
                return "Person";
            case "vendor":
                return "Organization";
            case "property":
                return "Property";
            case "zone":
                return "Zone";
            case "house":
                return "House";
            default:
                return null;
        }
    }
}
