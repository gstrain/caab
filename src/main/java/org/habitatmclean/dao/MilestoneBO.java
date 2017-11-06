package org.habitatmclean.dao;

import org.habitatmclean.entity.Milestone;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class MilestoneBO extends GenericDao implements ReadDAO<Milestone, Long> {
    @Override
    public Milestone findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Milestone.class, id);
        return (Milestone) obj;
    }

    @Override
    public List<Milestone> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Milestone");
        return result.list();
    }
}
