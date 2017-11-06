package org.habitatmclean.dao;

import org.habitatmclean.entity.Class;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ClassBO extends GenericDao implements ReadDAO<Class, Long> {
    @Override
    public Class findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Class.class, id);
        return (Class) obj;
    }

    @Override
    public List<Class> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Class");
        return result.list();
    }
}
