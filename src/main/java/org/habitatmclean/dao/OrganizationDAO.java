package org.habitatmclean.dao;

import org.habitatmclean.entity.Organization;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class OrganizationDAO implements ReadDAO<Organization, Long>, CreateUpdateDeleteDAO<Organization, Long> {
    private SessionFactory sessionFacotry = null;

    public OrganizationDAO() {}
    public OrganizationDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public Organization findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(Organization.class, id);
        return (Organization) obj;
    }

    @Override
    public Organization save(Organization entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Organization entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<Organization> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from Organization");
        return result.list();
    }
}
