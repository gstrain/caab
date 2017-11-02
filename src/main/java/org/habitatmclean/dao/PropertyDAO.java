package org.habitatmclean.dao;

import org.habitatmclean.entity.Address;
import org.habitatmclean.entity.Property;
import org.habitatmclean.hibernate.HibernateAdapter;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PropertyDAO implements ReadDAO<Property, Long>, CreateUpdateDeleteDAO<Property, Long> {
    private SessionFactory sessionFactory = null;

    public PropertyDAO() {}
    public PropertyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Property findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Property.class, id);
        return (Property) obj;
    }

    @Override
    public Property save(Property entity) {
        ReadDAO dao = HibernateAdapter.getDaoByEntityName("Address");
        CreateUpdateDeleteDAO saveDao = new HibernateAdapter();

        Address propertyAddress = entity.getAddress();
        Address foundAddress = null;

        // updating an address
        if(entity.getAddress().getId() != null) {
            try {
                foundAddress = (Address) dao.findByPrimaryKey(propertyAddress.getId());
                System.out.println("address found with id: " + foundAddress.getId());
                if(!foundAddress.equals(propertyAddress)) { // check if we made an update to a property's address
                    // we did, so force add a new address
                    sessionFactory.getCurrentSession().evict(foundAddress); // this needs to happen before we can cascade save a property
//                    propertyAddress.setAddress_id(null);
                    System.out.println("forcing new address insert");
                } else {
                    System.out.println("leaving address as-is");
                    // leave address alone b/c it remained the same
                }
            } catch (ObjectNotFoundException e) {
                System.out.println("address for property wasn't located, saving a new one");
            }
        }
        saveDao.save(propertyAddress);
        System.out.println("saved address with address_id: " + propertyAddress.getId());

        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity); // regular cascade save b/c new address
        return entity;
    }

    @Override
    public void delete(Property entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<Property> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Property ").list();
    }
}
