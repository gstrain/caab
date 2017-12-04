package org.habitatmclean.servlet.pages;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.House;
import org.habitatmclean.entity.Property;
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

@WebServlet(name = "LogServlet", value="/log-servlet")
public class LogServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long fk = Long.parseLong(request.getParameter("fk"));
        String pname = request.getParameter("pname");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        SortedSet logs = null;
        sessionFactory.getCurrentSession().beginTransaction();
        if (pname.equals("property")) {
            GenericDao dao = HibernateAdapter.getBoByEntityName("Property");
            Property property = (Property) dao.findByPrimaryKey(new Long(request.getParameter("fk")));
            logs = property.getLogs();
        } else if (pname.equals("house")) {
            GenericDao dao = HibernateAdapter.getBoByEntityName("House");
            House house = (House) dao.findByPrimaryKey(new Long(request.getParameter("fk")));
            logs = house.getLogs();
        } else if (pname.equals("family")) {
//            logs = FamilyBO fo = new FamilyBO();
        } else {

        }

        Table table = null;
        try {
            table = TableFactory.getTable("log");
        } catch (TableTypeNotFoundException e) {
            e.printStackTrace();
        }

        int[] options = Functions.getPageAndCount(request, logs.size());
        table.addData(Functions.resultSet(logs, options[0], options[1]));
        response.getWriter().println(table);
        response.getWriter().print("resultsSize:" + logs.size());
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
}
