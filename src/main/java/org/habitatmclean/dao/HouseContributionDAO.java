package org.habitatmclean.dao;

import org.habitatmclean.entity.HouseContribution;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HouseContributionDAO implements ReadDAO<HouseContribution, Long>, CreateUpdateDeleteDAO<HouseContribution, Long> {
    private SessionFactory sessionFacotry = null;

    public HouseContributionDAO() {}
    public HouseContributionDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public HouseContribution findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(HouseContribution.class, id);
        return (HouseContribution) obj;
    }

    @Override
    public HouseContribution save(HouseContribution entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(HouseContribution entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<HouseContribution> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from HouseContribution");
        return result.list();
    }
}
