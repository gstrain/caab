package org.habitatmclean.servlet;

import org.habitatmclean.dao.GenericDao;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.habitatmclean.hibernate.HibernateUtil;
import org.habitatmclean.hibernate.functions;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", value="/delete")
public class DeleteServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pk = Integer.parseInt(request.getParameter("primary_k"));
        String type = functions.hiddenInputToClass(request.getParameter("page"));
        GenericDao reader = HibernateAdapter.getBoByEntityName(type);

        if (reader != null) {
            HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
            GenericEntity toDelete = reader.findByPrimaryKey(new Long(pk));
            GenericDao deleter = new HibernateAdapter();
            deleter.delete(toDelete);
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
