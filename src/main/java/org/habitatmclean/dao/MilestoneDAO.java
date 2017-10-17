package org.habitatmclean.dao;

import org.habitatmclean.entity.Milestone;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MilestoneDAO implements ReadDAO<Milestone, Long>, CreateUpdateDeleteDAO<Milestone, Long> {
    private SessionFactory sessionFacotry = null;

    public MilestoneDAO() {}
    public MilestoneDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public Milestone findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(Milestone.class, id);
        return (Milestone) obj;
    }

    @Override
    public Milestone save(Milestone entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Milestone entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<Milestone> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from Milestone");
        return result.list();
    }
}
