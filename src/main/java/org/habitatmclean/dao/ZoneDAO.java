package org.habitatmclean.dao;

import org.habitatmclean.entity.Zone;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ZoneDAO implements ReadDAO<Zone, Long>, CreateUpdateDeleteDAO<Zone, Long> {
    private SessionFactory sessionFacotry = null;

    public ZoneDAO() {}
    public ZoneDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public Zone findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(Zone.class, id);
        return (Zone) obj;
    }

    @Override
    public Zone save(Zone entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Zone entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<Zone> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from Zone");
        return result.list();
    }
}
