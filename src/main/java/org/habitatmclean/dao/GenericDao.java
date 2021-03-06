package org.habitatmclean.dao;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class GenericDao<T extends GenericEntity> implements CreateUpdateDeleteDAO<T, Long>, ReadDAO<T, Long> {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private Class<T> clazz;

    public GenericDao() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }
    public GenericDao(Class<T> clazz) {
        this();
        this.clazz = clazz;
    }

    @Override
    public T save(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        sessionFactory.getCurrentSession().flush();
        return entity;
    }

    @Override
    public void delete(T entity) {
        if (entity != null)
            sessionFactory.getCurrentSession().delete(entity);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public T findByPrimaryKey(Long aLong) {
        T obj = null;
        if(aLong != null)
            obj = sessionFactory.getCurrentSession().get(clazz, aLong);
        return obj;
    }

    @Override
    public SortedSet<T> findAll() {
        List<T> objects = sessionFactory.getCurrentSession().createCriteria(clazz).setCacheable(false).list();
        SortedSet actual = new TreeSet();
        actual.addAll(objects);
        return actual;
    }
}
