package org.habitatmclean.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.Property;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.SortedSet;

@WebServlet(name = "SearchBoxServlet", value="/search-servlet")
public class SearchBoxServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.getCurrentSession().beginTransaction();
        GenericDao readerDAO = HibernateAdapter.getBoByEntityName("Property");
        SortedSet<Property> properties = readerDAO.findAll();
        sessionFactory.getCurrentSession().getTransaction().commit();

        // hash map stores in form (property id, property address)
        HashMap<String, String> results = new HashMap<>();

        Iterator itr = properties.iterator();
        while(itr.hasNext()) {
            Property curr = (Property) itr.next();
            String value = curr.getId() + ": " + curr.getPropertyAddress().getStreet() + ", " + curr.getPropertyAddress().getCity() + ", " +
                    curr.getPropertyAddress().getState() + " " + curr.getPropertyAddress().getZipcode();
            results.put("" + curr.getId(), value);
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        response.getWriter().println(gson.toJson(results));
    }

}
