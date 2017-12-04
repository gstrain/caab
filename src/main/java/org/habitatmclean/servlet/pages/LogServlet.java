package org.habitatmclean.servlet.pages;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.dao.HouseBO;
import org.habitatmclean.dao.PropertyBO;
import org.habitatmclean.dao.ReadDAO;
import org.habitatmclean.entity.GenericEntity;
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
import java.util.TreeSet;

@WebServlet(name = "LogServlet", value="/log-servlet")
public class LogServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long fk = Long.parseLong(request.getParameter("fk"));
        String pname = request.getParameter("pname");
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        GenericDao dao = HibernateAdapter.getBoByEntityName("Log");
        SortedSet logs = null;

        sessionFactory.getCurrentSession().beginTransaction();
        if (pname.equals("property")) {
            PropertyBO prop = new PropertyBO();
            System.out.println("\n\n\n" + fk + "\n\n\n" + pname + "\n\n\n");
            logs = prop.findAllByForeignKey(fk);
        } else if (pname.equals("house")) {
            HouseBO ho = new HouseBO();
            logs = ho.findAllByForeignKey(fk);
        } else if (pname.equals("family")) {
//            logs = FamilyBO fo = new FamilyBO();
        } else {

        }
//        SortedSet logs = dao.findAllByForeignKey(fk, pname);
//        SortedSet logs = dao.findAll();

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
