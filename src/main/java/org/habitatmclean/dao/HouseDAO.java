package org.habitatmclean.dao;

import org.habitatmclean.entity.House;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class HouseDAO implements ReadDAO<House, Long>, CreateUpdateDeleteDAO<House, Long> {
    private SessionFactory sessionFacotry = null;

    public HouseDAO() {}
    public HouseDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public House findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(House.class, id);
        return (House) obj;
    }

    @Override
    public House save(House entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(House entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<House> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from House");
        return result.list();
    }
}
