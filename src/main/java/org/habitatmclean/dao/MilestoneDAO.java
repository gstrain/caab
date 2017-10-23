package org.habitatmclean.dao;

import org.habitatmclean.entity.Milestone;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MilestoneDAO implements ReadDAO<Milestone, Long>, CreateUpdateDeleteDAO<Milestone, Long> {
    private SessionFactory sessionFactory = null;

    public MilestoneDAO() {}
    public MilestoneDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Milestone findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Milestone.class, id);
        return (Milestone) obj;
    }

    @Override
    public Milestone save(Milestone entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Milestone entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<Milestone> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Milestone");
        return result.list();
    }
}
