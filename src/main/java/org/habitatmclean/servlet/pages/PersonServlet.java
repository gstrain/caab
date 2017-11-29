package org.habitatmclean.servlet.pages;

import org.habitatmclean.dao.ReadDAO;
import org.habitatmclean.hibernate.Functions;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.habitatmclean.table.Table;
import org.habitatmclean.table.TableFactory;
import org.habitatmclean.table.TableTypeNotFoundException;
import org.hibernate.SessionFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedSet;

@WebServlet(name = "PersonServlet", value="/person-servlet")
public class PersonServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

//        sessionFactory.getCurrentSession().beginTransaction();
//        Family fam = (Family) HibernateAdapter.getBoByEntityName("Family").findByPrimaryKey(2L);
//        RelationType relationType = (RelationType) HibernateAdapter.getBoByEntityName("RelationType").findByPrimaryKey(1L);
//        sessionFactory.getCurrentSession().getTransaction().commit();
//
//        Random gen = new Random();
//        GenericDao saver = new HibernateAdapter();
//

//        for(int i = 0; i < 3000; i++) {
//            sessionFactory.getCurrentSession().beginTransaction();
//            Person p = new Person("abe" + gen.nextInt(9999), "west", "ram", "231@example.com", "1234567890", "5432106987", "0987654321", fam, relationType,
//                    new Address("1st", "" + gen.nextInt(100), "peo", "IL", "" + gen.nextInt(999999)));
//            saver.save(p);
//            sessionFactory.getCurrentSession().getTransaction().commit();
//        }


        ReadDAO dao = HibernateAdapter.getBoByEntityName("Person");

        sessionFactory.getCurrentSession().beginTransaction();
        SortedSet persons = dao.findAll();


        Table table = null;
        try {
            table = TableFactory.getTable("person");
        } catch (TableTypeNotFoundException e) {
            e.printStackTrace();
        }

        int[] options = Functions.getPageAndCount(request, persons.size());
        table.addData(Functions.resultSet(persons, options[0], options[1]));
        response.getWriter().println(table);
        response.getWriter().print("resultsSize:" + persons.size());
        System.out.println(persons.size());
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
}
