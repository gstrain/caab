package org.habitatmclean.dao;

import org.habitatmclean.entity.Log;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class LogDAO implements ReadDAO<Log, Long>, CreateUpdateDeleteDAO<Log, Long> {
    private SessionFactory sessionFactory = null;

    public LogDAO() {}
    public LogDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Log findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Log.class, id);
        return (Log) obj;
    }

    @Override
    public Log save(Log entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Log entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<Log> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Log");
        return result.list();
    }
}
