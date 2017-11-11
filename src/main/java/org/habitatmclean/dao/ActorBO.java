package org.habitatmclean.dao;

import org.habitatmclean.entity.Actor;
import org.habitatmclean.hibernate.HibernateUtil;
import org.hibernate.SessionFactory;

public class ActorBO extends GenericDao<Actor> {
    public ActorBO() {
        super(Actor.class);
    }

    @Override
    public Actor save(Actor entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        entity.getAddress().setId(null);
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
        return entity;
    }
}
