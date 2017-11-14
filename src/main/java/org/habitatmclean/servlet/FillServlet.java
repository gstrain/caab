package org.habitatmclean.servlet;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.hibernate.Functions;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedSet;

@WebServlet(name = "FillServlet", value="/fill-servlet")
public class FillServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();

        try{
            int id = Integer.parseInt(request.getParameter("id"));
            String table = request.getParameter("table");
            table = Functions.hiddenInputToClass(table);
            response.getWriter().println(fillResponse(id, table));
        }
        catch(Exception e){}

        sessionFactory.getCurrentSession().getTransaction().commit();

    }

    public String fillResponse(int id, String table){
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        return false;
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return aClass == SortedSet.class;
                    }
                }).create();
        GenericDao dao = HibernateAdapter.getBoByEntityName(table);
        GenericEntity responseObj = dao.findByPrimaryKey((long)id);
        return gson.toJson(responseObj);
    }

}
