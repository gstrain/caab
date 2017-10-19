package org.habitatmclean.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.habitatmclean.dao.*;
import org.habitatmclean.entity.*;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;

@WebServlet(name = "DBServlet", value="/dbservlet")
public class DBServlet extends HttpServlet {
    private static Gson gson = new GsonBuilder().setPrettyPrinting()
            .create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Random gen = new Random();
        PrintWriter out = response.getWriter();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ReadDAO dao = new PropertyDAO(sessionFactory);
        CreateUpdateDeleteDAO saveDao = (CreateUpdateDeleteDAO) dao;   // safe b/c dao holds an implementation of both interfaces

        /* read and update */
        sessionFactory.getCurrentSession().beginTransaction();
        List list = dao.findAll(); // find all properties in table
        Property single = (Property) dao.findByPrimaryKey(1L); // find specific by primary key, 1L is equivalent to typing new Long(1)
        single.setNotes("added notes via save function");
        saveDao.save(single);   // Update functionality
        sessionFactory.getCurrentSession().getTransaction().commit();

        /* print read values to response */
        String s = gson.toJson(single);
        out.println(s); // send list of properties to response
        out.println("\n\n");

        /* make random values and grab random foreign keys for the new property */
        sessionFactory.getCurrentSession().beginTransaction();
        Address address = new Address("street name", "" +gen.nextInt(9999) + 1, "" +gen.nextInt(9999) + 1, "nowhere", "Pennsylvania", "" +gen.nextInt(99999) + 1);
        Zone zone = new ZoneDAO(sessionFactory).findByPrimaryKey((long) (gen.nextInt(3) + 1));
        PropertyStatus propertyStatus = new PropertyStatusDAO(sessionFactory).findByPrimaryKey((long) (gen.nextInt(3) + 1));
        Actor owner = new ActorDAO(sessionFactory).findByPrimaryKey((long) (gen.nextInt(6) + 1));
        sessionFactory.getCurrentSession().getTransaction().commit();

        Property toInsert = new Property(gen.nextDouble()*99999, new Date(), gen.nextDouble()*99999, "", propertyStatus, zone, owner, address);

        /* save new */
        sessionFactory.getCurrentSession().beginTransaction();
        toInsert.setAddress(address); // generate a new address b/c properties must have unique address_id fields
        saveDao.save(toInsert);   // insert new entity functionality, CascadeType.SAVE_UPDATE annotations in the entities
                                // handle automatically inserting new foreign key rows

        // fix HibernateProxy type (see below)
        Zone fixedZone = initializeAndUnproxy(zone);
        Actor fixedOwner = initializeAndUnproxy(owner);
        PropertyStatus fixedStatus = initializeAndUnproxy(propertyStatus);
        toInsert.setZone(fixedZone);
        toInsert.setOwner(fixedOwner);
        toInsert.setProperty_status(fixedStatus);

        sessionFactory.getCurrentSession().getTransaction().commit();

        s = gson.toJson(toInsert); // the newly inserted property
        out.println(s);
    }

    /*
     * turns objects returned by a hibernate query into real objects. those returned by a
     * lazy fetch query are technically of type HibernateProxy. Useful if we want to serialize something
     * returned by a query, but it MUST be used DURING the session (see above example)
     */
    private static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }


//      toInsert.setProperty_no(null); // remove primary key so we can test a save instead of update
    // hibernate throws an exception if we do this in the middle of a transaction

}
