package org.habitatmclean.dao;

import org.habitatmclean.entity.Actor;

public class ActorBO extends GenericDao {
    public ActorBO() {
        super(Actor.class);
    }
    //    @Override
//    public Actor findByPrimaryKey(Long id) {
//        Session session = sessionFactory.getCurrentSession();
//        Object obj = session.load(Actor.class, id);
//        return (Actor) obj;
//    }
//
//    @Override
//    public List<Actor> findAll() {
//        Session session = sessionFactory.getCurrentSession();
//        Query result = session.createQuery("from Actor");
//        return result.list();
//    }
}
