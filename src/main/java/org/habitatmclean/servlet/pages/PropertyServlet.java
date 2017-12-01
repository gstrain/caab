package org.habitatmclean.servlet.pages;

import org.habitatmclean.dao.ReadDAO;
import org.habitatmclean.hibernate.Functions;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.habitatmclean.table.Table;
import org.habitatmclean.table.TableFactory;
import org.habitatmclean.table.TableTypeNotFoundException;
import org.hibernate.SessionFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.SortedSet;

@WebServlet(name = "PropertyServlet", value="/property-servlet")
public class PropertyServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ReadDAO dao = HibernateAdapter.getBoByEntityName("Property");
        sessionFactory.getCurrentSession().beginTransaction();
        SortedSet properties = dao.findAll();

        Table table = null;
        try {
            table = TableFactory.getTable("property");
        } catch (TableTypeNotFoundException e) {
            e.printStackTrace();
        }

        int[] options = Functions.getPageAndCount(request, properties.size());
        table.addData(Functions.resultSet(properties, options[0], options[1]));
        response.getWriter().println(table);
        response.getWriter().print("resultsSize:" + properties.size());
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

}
