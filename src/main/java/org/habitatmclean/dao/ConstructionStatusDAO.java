package org.habitatmclean.dao;

import org.habitatmclean.entity.ConstructionStatus;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ConstructionStatusDAO implements ReadDAO<ConstructionStatus, Long>, CreateUpdateDeleteDAO<ConstructionStatus, Long> {
    private SessionFactory sessionFacotry = null;

    public ConstructionStatusDAO() {}
    public ConstructionStatusDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public ConstructionStatus findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(ConstructionStatus.class, id);
        return (ConstructionStatus) obj;
    }

    @Override
    public ConstructionStatus save(ConstructionStatus entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(ConstructionStatus entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<ConstructionStatus> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from ConstructionStatus");
        return result.list();
    }
}
