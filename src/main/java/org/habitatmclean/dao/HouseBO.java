package org.habitatmclean.dao;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.House;
import org.habitatmclean.entity.Log;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class HouseBO<T extends GenericEntity> extends GenericDao<House> {
    public HouseBO() {
        super(House.class);
    }
    public SortedSet<T> findAllByForeignKey(Long fk) {
        List<T> objects = sessionFactory.getCurrentSession().createCriteria(Log.class).setCacheable(false).add(Restrictions.eq("logHouse.id", fk)).list();
        SortedSet actual = new TreeSet();
        actual.addAll(objects);
        return actual;
    }
}
