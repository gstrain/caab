package org.habitatmclean.dao;

import org.habitatmclean.entity.Family;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class FamilyBO extends GenericDao implements ReadDAO<Family, Long> {

    @Override
    public Family findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Family.class, id);
        return (Family) obj;
    }

    @Override
    public List<Family> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Family");
        return result.list();
    }
}
