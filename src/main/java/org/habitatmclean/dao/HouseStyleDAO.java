package org.habitatmclean.dao;

import org.habitatmclean.entity.HouseStyle;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HouseStyleDAO implements ReadDAO<HouseStyle, Long>, CreateUpdateDeleteDAO<HouseStyle, Long> {
    private SessionFactory sessionFacotry = null;

    public HouseStyleDAO() {}
    public HouseStyleDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public HouseStyle findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(HouseStyle.class, id);
        return (HouseStyle) obj;
    }

    @Override
    public HouseStyle save(HouseStyle entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(HouseStyle entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<HouseStyle> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from HouseStyle");
        return result.list();
    }
}
