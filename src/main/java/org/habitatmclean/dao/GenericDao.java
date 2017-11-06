package org.habitatmclean.dao;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

public class GenericDao implements CreateUpdateDeleteDAO<GenericEntity, Long> {
    SessionFactory sessionFactory;

    public GenericDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    public GenericDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public GenericEntity save(GenericEntity entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(GenericEntity entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }
}
