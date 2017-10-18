package org.habitatmclean.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.habitatmclean.adapter.PropertyAdapter;
import org.habitatmclean.dao.PropertyDAO;
import org.habitatmclean.entity.Property;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DBServlet", value="/dbservlet")
public class DBServlet extends HttpServlet {
    private static Gson gson = new GsonBuilder()
            .create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        PropertyDAO dao = new PropertyDAO(sessionFactory);
        sessionFactory.getCurrentSession().beginTransaction();
        List<Property> list = dao.findAll();
        sessionFactory.getCurrentSession().getTransaction().commit();
        gson = new GsonBuilder().registerTypeAdapter(Property.class, new PropertyAdapter()).create();
        String s = gson.toJson(list);
        System.out.println("called servlet");
        System.out.println("stringified list of users into json: ");
        System.out.println(s);
        out.println(s);
    }
}
