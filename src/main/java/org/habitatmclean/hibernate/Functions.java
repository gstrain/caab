package org.habitatmclean.hibernate;

import java.lang.reflect.Field;
import java.util.*;

public class Functions {
    public static final String TAB = "&nbsp;&nbsp;&nbsp;&nbsp;";
    public static final String NEWLINE_TAB = "<br/>" + TAB;

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

    public static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }

    public static SortedSet resultSet(SortedSet all, int page) {
        final int RESULTS_PER_PAGE = 50;
        SortedSet results = new TreeSet();
        Iterator itr = all.iterator();
        int beginIndex = (page-1)*RESULTS_PER_PAGE;
        for(int i = 0; i < beginIndex && itr.hasNext(); i++) itr.next(); // move the iterator to the appropriate position
        for(int i = 0; i < RESULTS_PER_PAGE && itr.hasNext(); i++) {  // grab the desired amount or the rest of the results, whichever comes first
            results.add(itr.next());
        }
        return results; // the results to display
    }
}
