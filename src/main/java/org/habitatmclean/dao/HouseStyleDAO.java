package org.habitatmclean.dao;

import org.habitatmclean.entity.HouseStyle;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HouseStyleDAO implements ReadDAO<HouseStyle, Long>, CreateUpdateDeleteDAO<HouseStyle, Long> {
    private SessionFactory sessionFactory = null;

    public HouseStyleDAO() {}
    public HouseStyleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public HouseStyle findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(HouseStyle.class, id);
        return (HouseStyle) obj;
    }

    @Override
    public HouseStyle save(HouseStyle entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(HouseStyle entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<HouseStyle> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from HouseStyle");
        return result.list();
    }
}
