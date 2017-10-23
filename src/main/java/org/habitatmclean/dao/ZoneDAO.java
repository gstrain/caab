package org.habitatmclean.dao;

import org.habitatmclean.entity.Zone;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ZoneDAO implements ReadDAO<Zone, Long> {
    private SessionFactory sessionFactory = null;

    public ZoneDAO() {}
    public ZoneDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Zone findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Zone.class, id);
        return (Zone) obj;
    }

    @Override
    public List<Zone> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Zone");
        return result.list();
    }
}
