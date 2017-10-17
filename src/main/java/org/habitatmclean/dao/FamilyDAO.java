package org.habitatmclean.dao;

import org.habitatmclean.entity.Family;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class FamilyDAO implements ReadDAO<Family, Long>, CreateUpdateDeleteDAO<Family, Long> {
    private SessionFactory sessionFacotry = null;

    public FamilyDAO() {}
    public FamilyDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public Family findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(Family.class, id);
        return (Family) obj;
    }

    @Override
    public Family save(Family entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Family entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<Family> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from Family");
        return result.list();
    }
}
