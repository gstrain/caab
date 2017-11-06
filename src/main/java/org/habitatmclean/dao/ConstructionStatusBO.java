package org.habitatmclean.dao;

import org.habitatmclean.entity.ConstructionStatus;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ConstructionStatusBO extends GenericDao implements ReadDAO<ConstructionStatus, Long> {
    @Override
    public ConstructionStatus findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(ConstructionStatus.class, id);
        return (ConstructionStatus) obj;
    }

    @Override
    public List<ConstructionStatus> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from ConstructionStatus");
        return result.list();
    }
}
