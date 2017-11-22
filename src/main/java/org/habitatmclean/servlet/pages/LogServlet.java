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

@WebServlet(name = "LogServlet", value="/log-servlet")
public class LogServlet extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ReadDAO dao = HibernateAdapter.getBoByEntityName("Log");
        sessionFactory.getCurrentSession().beginTransaction();
        SortedSet logs = dao.findAll();

        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page")); // default to 1st page, otherwise whatever page in request
        } catch (NumberFormatException e) { }

        Table table = null;
        try {
            table = TableFactory.getTable("log");
        } catch (TableTypeNotFoundException e) {
            e.printStackTrace();
        }
        table.addData(Functions.resultSet(logs, page));
        response.getWriter().println(table);
        response.getWriter().print("resultsSize:" + logs.size());
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
}
