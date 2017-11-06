package org.habitatmclean.dao;

import org.habitatmclean.entity.Address;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class AddressBO extends GenericDao implements ReadDAO<Address, Long> {

    @Override
    public Address findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.load(Address.class, id);
    }

    @Override
    public List<Address> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Address");
        return result.list();
    }
}
