package org.habitatmclean.dao;

import org.habitatmclean.entity.Log;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class LogDAO implements ReadDAO<Log, Long>, CreateUpdateDeleteDAO<Log, Long> {
    private SessionFactory sessionFacotry = null;

    public LogDAO() {}
    public LogDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public Log findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(Log.class, id);
        return (Log) obj;
    }

    @Override
    public Log save(Log entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Log entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<Log> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from Log");
        return result.list();
    }
}
