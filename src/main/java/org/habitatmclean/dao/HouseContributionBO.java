package org.habitatmclean.dao;

import org.habitatmclean.entity.HouseContribution;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class HouseContributionBO extends GenericDao implements ReadDAO<HouseContribution, Long> {
    @Override
    public HouseContribution findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(HouseContribution.class, id);
        return (HouseContribution) obj;
    }

    @Override
    public List<HouseContribution> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from HouseContribution");
        return result.list();
    }
}
