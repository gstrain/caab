package org.habitatmclean.dao;

import org.habitatmclean.entity.Address;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AddressDAO implements ReadDAO<Address, Long>, CreateUpdateDeleteDAO<Address, Long> {
    private SessionFactory sessionFacotry = null;

    public AddressDAO() {}
    public AddressDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public Address findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(Address.class, id);
        return (Address) obj;
    }

    @Override
    public Address save(Address entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Address entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<Address> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from Address");
        return result.list();
    }
}
