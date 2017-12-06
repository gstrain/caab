package org.habitatmclean.hibernate;

import javax.servlet.http.HttpServletRequest;
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
            case "organization":
                return"organizations";
            case "family":
                return "families";
            case "log":
                return "logs";
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
            case "organization":
                return "Organization";
            case "family":
                return "Family";
            case "property":
                return "Property";
            case "zone":
                return "Zone";
            case "house":
                return "House";
            case "log":
                return "Log";
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

    public static SortedSet resultSet(SortedSet all, int page, int resultsPerPage) {
        if(resultsPerPage == -1) return all; // -1 is flag to indicate the entire result set
        SortedSet results = new TreeSet();
        Iterator itr = all.iterator();
        int beginIndex = (page-1)*resultsPerPage;
        for(int i = 0; i < beginIndex && itr.hasNext(); i++) itr.next(); // move the iterator to the appropriate position
        for(int i = 0; i < resultsPerPage && itr.hasNext(); i++) {  // grab the desired amount or the rest of the results, whichever comes first
            results.add(itr.next());
        }
        return results; // the results to display
    }

    /**
     * @param request
     * @param size the result set size
     * @return an int[] of size 2: page # is [0], count per page is [1]
     */
    public static int[] getPageAndCount(HttpServletRequest request, int size) {
        int[] result = {1, 50};
        try {
            if(request.getParameter("perPage") != null) {
                int perPage = Integer.parseInt(request.getParameter("perPage"));
                if(perPage == -1)
                    return new int[] {1, -1};
                result[1] = perPage;
            }
            if(request.getParameter("page") != null)
                result[0] = Integer.parseInt(request.getParameter("page"));
            if(result[0] < 1)
                result[0] = 1;  // don't allow invalid pages
            result[0] = Math.min(result[0], size/Math.abs(result[1]));
        } catch (NumberFormatException e) {
            result[0] = 1;
            result [1] = 50;// default to page 1 and a count of 50
        }
        return result;
    }


}
