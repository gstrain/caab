package org.habitatmclean.table;

import org.habitatmclean.dao.ReadDAO;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedSet;

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
