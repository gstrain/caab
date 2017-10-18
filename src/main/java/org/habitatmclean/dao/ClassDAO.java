package org.habitatmclean.dao;

import org.habitatmclean.entity.Class;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ClassDAO implements ReadDAO<Class, Long>, CreateUpdateDeleteDAO<Class, Long> {
    private SessionFactory sessionFacotry = null;

    public ClassDAO() {}
    public ClassDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public Class findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(Class.class, id);
        return (Class) obj;
    }

    @Override
    public Class save(Class entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Class entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<Class> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from Class");
        return result.list();
    }
}
