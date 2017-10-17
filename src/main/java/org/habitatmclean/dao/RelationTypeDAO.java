package org.habitatmclean.dao;

import org.habitatmclean.entity.RelationType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RelationTypeDAO implements ReadDAO<RelationType, Long>, CreateUpdateDeleteDAO<RelationType, Long> {
    private SessionFactory sessionFacotry = null;

    public RelationTypeDAO() {}
    public RelationTypeDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public RelationType findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(RelationType.class, id);
        return (RelationType) obj;
    }

    @Override
    public RelationType save(RelationType entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(RelationType entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<RelationType> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from RelationType");
        return result.list();
    }
}
