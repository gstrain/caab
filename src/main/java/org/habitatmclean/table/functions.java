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
}
