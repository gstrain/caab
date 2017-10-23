package org.habitatmclean.dao;

import org.habitatmclean.entity.Organization;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrganizationDAO implements ReadDAO<Organization, Long>, CreateUpdateDeleteDAO<Organization, Long> {
    private SessionFactory sessionFactory = null;

    public OrganizationDAO() {}
    public OrganizationDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Organization findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Organization.class, id);
        return (Organization) obj;
    }

    @Override
    public Organization save(Organization entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Organization entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<Organization> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Organization");
        return result.list();
    }
}
