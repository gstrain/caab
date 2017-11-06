package org.habitatmclean.dao;

import org.habitatmclean.entity.Organization;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class OrganizationBO extends GenericDao implements ReadDAO<Organization, Long> {
    @Override
    public Organization findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Organization.class, id);
        return (Organization) obj;
    }

    @Override
    public List<Organization> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Organization");
        return result.list();
    }
}
