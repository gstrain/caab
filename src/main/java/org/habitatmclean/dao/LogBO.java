package org.habitatmclean.dao;

import org.habitatmclean.entity.Log;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class LogBO extends GenericDao implements ReadDAO<Log, Long> {
    @Override
    public Log findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Log.class, id);
        return (Log) obj;
    }

    @Override
    public List<Log> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Log");
        return result.list();
    }
}
