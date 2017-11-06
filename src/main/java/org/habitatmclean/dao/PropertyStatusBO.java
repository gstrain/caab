package org.habitatmclean.dao;

import org.habitatmclean.entity.PropertyStatus;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class PropertyStatusBO extends GenericDao implements ReadDAO<PropertyStatus, Long> {
    @Override
    public PropertyStatus findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(PropertyStatus.class, id);
        return (PropertyStatus) obj;
    }

    @Override
    public List<PropertyStatus> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from PropertyStatus");
        return result.list();
    }
}
