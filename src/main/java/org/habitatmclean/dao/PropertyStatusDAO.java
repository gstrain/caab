package org.habitatmclean.dao;

import org.habitatmclean.entity.PropertyStatus;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PropertyStatusDAO implements ReadDAO<PropertyStatus, Long>, CreateUpdateDeleteDAO<PropertyStatus, Long> {
    private SessionFactory sessionFacotry = null;

    public PropertyStatusDAO() {}
    public PropertyStatusDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public PropertyStatus findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(PropertyStatus.class, id);
        return (PropertyStatus) obj;
    }

    @Override
    public PropertyStatus save(PropertyStatus entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(PropertyStatus entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<PropertyStatus> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from PropertyStatus");
        return result.list();
    }
}
