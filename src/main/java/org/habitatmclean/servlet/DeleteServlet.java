package org.habitatmclean.servlet;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.hibernate.Functions;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.SortedSet;

@WebServlet(name = "DeleteServlet", value="/delete")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // first grab the entity that is being deleted
        int pk = Integer.parseInt(request.getParameter("primary_k"));
        String type = Functions.hiddenInputToClass(request.getParameter("page"));
        GenericDao reader = HibernateAdapter.getBoByEntityName(type);
        HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        GenericEntity toDelete = reader.findByPrimaryKey(new Long(pk));
        switch (request.getParameter("method")) {
            case "delete":
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
                if (reader != null) {
                    HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
                    GenericDao deleter = new HibernateAdapter();
                    deleter.delete(toDelete);
                    HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
                }
                break;

            case "validate":    // returns a string of rows that will be affected by the deletion
                boolean safe = true;
                StringBuilder responseText = new StringBuilder("");
                for (Field field : toDelete.getClass().getDeclaredFields()) {
                    try {
                        field.setAccessible(true);
                        // found a sorted set, check it for foreign keys
                        if(field.getType() == SortedSet.class) {
                            HibernateUtil.initializeAndUnproxy(field.get(toDelete));
                            Iterator<GenericEntity> itr = ((SortedSet) field.get(toDelete)).iterator();    // cast is safe b/c of if statement
                            if(itr.hasNext()) safe = false;

                            // iterate through set and add it's fields to response
                            while (itr.hasNext()) {
                                GenericEntity obj = itr.next();
                                responseText.append(obj.toString() + "\n");
                            }
                        }
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) { }
                }
                HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();

                response.getWriter().print(safe + "\n" + responseText.toString());
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
