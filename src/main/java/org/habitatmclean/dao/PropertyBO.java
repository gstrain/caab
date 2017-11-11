package org.habitatmclean.dao;

import org.habitatmclean.entity.Property;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

public class PropertyBO extends GenericDao<Property> {
    public PropertyBO() {
        super(Property.class);
    }

    @Override
    public Property save(Property entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        entity.getAddress().setId(null);
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }
}
