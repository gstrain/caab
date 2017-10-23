package org.habitatmclean.dao;

import org.habitatmclean.entity.RelationType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RelationTypeDAO implements ReadDAO<RelationType, Long>, CreateUpdateDeleteDAO<RelationType, Long> {
    private SessionFactory sessionFactory = null;

    public RelationTypeDAO() {}
    public RelationTypeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public RelationType findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(RelationType.class, id);
        return (RelationType) obj;
    }

    @Override
    public RelationType save(RelationType entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(RelationType entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<RelationType> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from RelationType");
        return result.list();
    }
}
