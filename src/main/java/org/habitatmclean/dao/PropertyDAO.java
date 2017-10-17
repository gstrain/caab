package org.habitatmclean.dao;

import org.habitatmclean.entity.Property;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PropertyDAO implements ReadDAO<Property, Long>, CreateUpdateDeleteDAO<Property, Long> {
    private SessionFactory sessionFacotry = null;

    public PropertyDAO() {}
    public PropertyDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public Property findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(Property.class, id);
        return (Property) obj;
    }

    @Override
    public Property save(Property entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Property entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<Property> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from Property");
        return result.list();
    }
}
