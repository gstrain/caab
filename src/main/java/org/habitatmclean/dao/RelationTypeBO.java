package org.habitatmclean.dao;

import org.habitatmclean.entity.RelationType;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class RelationTypeBO extends GenericDao implements ReadDAO<RelationType, Long> {
    @Override
    public RelationType findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(RelationType.class, id);
        return (RelationType) obj;
    }

    @Override
    public List<RelationType> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from RelationType");
        return result.list();
    }
}
