package org.habitatmclean.dao;

import org.habitatmclean.entity.HouseStyle;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class HouseStyleBO extends GenericDao implements ReadDAO<HouseStyle, Long> {
    @Override
    public HouseStyle findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(HouseStyle.class, id);
        return (HouseStyle) obj;
    }

    @Override
    public List<HouseStyle> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from HouseStyle");
        return result.list();
    }
}
