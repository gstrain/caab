package org.habitatmclean.servlet.pages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

@WebServlet(name = "VendorServlet", value="/vendor-servlet")
public class VendorServlet extends HttpServlet {
    private static Gson gson = new GsonBuilder().setPrettyPrinting()
            .create();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ReadDAO dao = HibernateAdapter.getBoByEntityName("Organization");
        sessionFactory.getCurrentSession().beginTransaction();
        SortedSet vendors = dao.findAll();

        Table table = null;
        try {
            table = TableFactory.getTable("vendor");
        } catch (TableTypeNotFoundException e) {
            e.printStackTrace();
        }

        int[] options = Functions.getPageAndCount(request, vendors.size());
        table.addData(Functions.resultSet(vendors, options[0], options[1]));
        response.getWriter().println(table);
        response.getWriter().print("resultsSize:" + vendors.size());
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

}
