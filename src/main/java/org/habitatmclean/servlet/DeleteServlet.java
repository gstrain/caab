package org.habitatmclean.servlet;

import org.habitatmclean.dao.CreateUpdateDeleteDAO;
import org.habitatmclean.dao.PersonDAO;
import org.habitatmclean.dao.ReadDAO;
import org.habitatmclean.entity.Person;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value="/delete")
public class DeleteServlet extends HttpServlet {
    private static TTLCache cache = new TTL();
    private final Long TIME_TO_LIVE =10000L;    // objects are valid in the cache for 10 seconds

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pk = Integer.parseInt(request.getParameter("primary_k"));
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        switch(request.getParameter("data_type")) {
                case "person":
                    sessionFactory.getCurrentSession().beginTransaction();
                    ReadDAO reader = new PersonDAO(sessionFactory);
                    CreateUpdateDeleteDAO deleter = (CreateUpdateDeleteDAO) reader;
                    Person toDelete = (Person) reader.findByPrimaryKey(new Long(pk));
                    Person cached = HibernateUtil.initializeAndUnproxy(toDelete);
                    cache.put(cached.getActor_id() + "", cached, TIME_TO_LIVE);
                    deleter.delete(toDelete);
                    cached.setActor_id(null);
                    sessionFactory.getCurrentSession().getTransaction().commit();
                    break;
                case "undo":
                    sessionFactory.getCurrentSession().beginTransaction();
                    CreateUpdateDeleteDAO save = new PersonDAO(sessionFactory);
                    save.save(cache.get(pk + ""));
                    sessionFactory.getCurrentSession().getTransaction().commit();
                    break;
            }
    }
}
