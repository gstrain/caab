package org.habitatmclean.dao;

import org.habitatmclean.entity.Property;
import org.hibernate.Session;

import java.util.List;

public class PropertyBO extends GenericDao implements ReadDAO<Property, Long> {
    @Override
    public Property findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Property.class, id);
        return (Property) obj;
    }

    @Override
    public List<Property> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Property ").list();
    }
}
