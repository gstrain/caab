package org.habitatmclean.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.habitatmclean.dao.*;
import org.habitatmclean.hibernate.HibernateUtil;
import org.habitatmclean.table.Table;
import org.habitatmclean.table.TableFactory;
import org.habitatmclean.table.TableTypeNotFoundException;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.proxy.HibernateProxy;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "DBServlet", value="/dbservlet")
public class DBServlet extends HttpServlet {
    private static Gson gson = new GsonBuilder().setPrettyPrinting()
            .create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Random gen = new Random();
//        PrintWriter out = response.getWriter();
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        ReadDAO dao = new PropertyDAO(sessionFactory);
//        CreateUpdateDeleteDAO saveDao = (CreateUpdateDeleteDAO) dao;   // safe b/c dao holds an implementation of both interfaces
//
//        /* read and update */
//        sessionFactory.getCurrentSession().beginTransaction();
//        List list = dao.findAll(); // find all properties in table
//        Property single = (Property) dao.findByPrimaryKey(1L); // find specific by primary key, 1L is equivalent to typing new Long(1)
//        single.setNotes("added notes via save function");
//        saveDao.save(single);   // Update functionality
//        sessionFactory.getCurrentSession().getTransaction().commit();
//
//        /* print read values to response */
//        String s = gson.toJson(single);
//        out.println(s); // send list of properties to response
//        out.println("\n\n");
//
//        /* make random values and grab random foreign keys for the new property */
//        sessionFactory.getCurrentSession().beginTransaction();
////        Address address = new AddressDAO(sessionFactory).findByPrimaryKey(1L); // insert with an existing address
//        Address address = new Address("street name", "" +gen.nextInt(9999) + 1, ""
//              +gen.nextInt(9999) + 1, "nowhere", "Pennsylvania", "" +gen.nextInt(99999) + 1); // insert with a new address
//        address = initializeAndUnproxy(address);
//        Zone zone = new ZoneDAO(sessionFactory).findByPrimaryKey((long) (gen.nextInt(3) + 1));
//        PropertyStatus propertyStatus = new PropertyStatusDAO(sessionFactory).findByPrimaryKey((long) (gen.nextInt(3) + 1));
//        Actor owner = new ActorDAO(sessionFactory).findByPrimaryKey((long) (gen.nextInt(6) + 1));
//        sessionFactory.getCurrentSession().getTransaction().commit();
//
//        Property toInsert = new Property(gen.nextDouble()*99999, new Date(), gen.nextDouble()*99999, "", propertyStatus, zone, owner, address);
//
//        /* save new */
//        sessionFactory.getCurrentSession().beginTransaction();
//        toInsert.setAddress(address); // generate a new address b/c properties must have unique address_id fields
//        saveDao.save(toInsert);   // insert new entity functionality, CascadeType.SAVE_UPDATE annotations in the entities
//                                  // handle automatically inserting new foreign key rows
//
//        // fix HibernateProxy type (see below)
//        Zone fixedZone = initializeAndUnproxy(zone);
//        Actor fixedOwner = initializeAndUnproxy(owner);
//        PropertyStatus fixedStatus = initializeAndUnproxy(propertyStatus);
//        toInsert.setZone(fixedZone);
//        toInsert.setOwner(fixedOwner);
//        toInsert.setProperty_status(fixedStatus);
//
//        sessionFactory.getCurrentSession().getTransaction().commit();

//        s = gson.toJson(toInsert); // the newly inserted property
//        out.println(s);

          /* bye */
//        sessionFactory.getCurrentSession().beginTransaction();
//        Property toDelete = (Property) dao.findByPrimaryKey(12L);
//        saveDao.delete(toDelete);
//        sessionFactory.getCurrentSession().getTransaction().commit();
        HibernateUtil.openSession();
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ReadDAO dao = new PersonDAO(sessionFactory);
        sessionFactory.getCurrentSession().beginTransaction();
        List persons = dao.findAll();
        Table table = null;
        try {
            table = TableFactory.getTable("person");
        } catch (TableTypeNotFoundException e) {
            e.printStackTrace();
        }
        table.addData(persons);
        sessionFactory.getCurrentSession().getTransaction().commit();
//        String bootstrap = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\" integrity=\"sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb\" crossorigin=\"anonymous\">" +
//                "<script src=\"https://code.jquery.com/jquery-3.2.1.slim.min.js\" integrity=\"sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN\" crossorigin=\"anonymous\"></script>\n" +
//                "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js\" integrity=\"sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh\" crossorigin=\"anonymous\"></script>\n" +
//                "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\" integrity=\"sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ\" crossorigin=\"anonymous\"></script>";
//        String jscript = "<script src=\"/scripts/table.js\"></script>";

//        response.getWriter().println("<!DOCTYPE html><html><head>" + bootstrap +"</head><body>");
        response.getWriter().println(table);
//        response.getWriter().println(jscript + "</body></html>");
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
