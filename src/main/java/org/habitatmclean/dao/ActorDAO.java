package org.habitatmclean.dao;

import org.habitatmclean.entity.Actor;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ActorDAO implements ReadDAO<Actor, Long>, CreateUpdateDeleteDAO<Actor, Long> {
    private SessionFactory sessionFacotry = null;

    public ActorDAO() {}
    public ActorDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public Actor findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(Actor.class, id);
        return (Actor) obj;
    }

    @Override
    public Actor save(Actor entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Actor entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<Actor> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from Actor");
        return result.list();
    }
}
