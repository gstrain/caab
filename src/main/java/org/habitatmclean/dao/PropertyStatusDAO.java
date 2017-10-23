package org.habitatmclean.dao;

import org.habitatmclean.entity.PropertyStatus;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PropertyStatusDAO implements ReadDAO<PropertyStatus, Long>, CreateUpdateDeleteDAO<PropertyStatus, Long> {
    private SessionFactory sessionFactory = null;

    public PropertyStatusDAO() {}
    public PropertyStatusDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public PropertyStatus findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(PropertyStatus.class, id);
        return (PropertyStatus) obj;
    }

    @Override
    public PropertyStatus save(PropertyStatus entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(PropertyStatus entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<PropertyStatus> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from PropertyStatus");
        return result.list();
    }
}
