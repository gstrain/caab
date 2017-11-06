package org.habitatmclean.dao;

import org.habitatmclean.entity.House;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class HouseBO extends GenericDao implements ReadDAO<House, Long> {
    @Override
    public House findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(House.class, id);
        return (House) obj;
    }

    @Override
    public List<House> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from House");
        return result.list();
    }
}
