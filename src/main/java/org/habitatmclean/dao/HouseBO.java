package org.habitatmclean.dao;

import org.habitatmclean.entity.House;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

public class HouseBO extends GenericDao<House> {
    public HouseBO() {
        super(House.class);
    }

    @Override
    public House save(House entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        entity.getAddress().setId(null);
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }
}
