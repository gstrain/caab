package org.habitatmclean.dao;

import org.habitatmclean.entity.Actor;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ActorBO extends GenericDao implements ReadDAO<Actor, Long> {

    @Override
    public Actor findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Actor.class, id);
        return (Actor) obj;
    }

    @Override
    public List<Actor> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Actor");
        return result.list();
    }
}
