package org.habitatmclean.servlet;

import org.habitatmclean.dao.CreateUpdateDeleteDAO;
import org.habitatmclean.dao.ReadDAO;
import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.hibernate.HibernateAdapter;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pk = Integer.parseInt(request.getParameter("primary_k"));
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        ReadDAO reader = HibernateAdapter.getDaoByEntityName(request.getParameter("data_type"));

        if (reader != null) {
            sessionFactory.getCurrentSession().beginTransaction();
            GenericEntity toDelete = (GenericEntity) reader.findByPrimaryKey(new Long(pk));
            GenericEntity actual = HibernateUtil.initializeAndUnproxy(toDelete);
            sessionFactory.getCurrentSession().getTransaction().commit();
            CreateUpdateDeleteDAO deleter = new HibernateAdapter();
            deleter.delete(actual);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
