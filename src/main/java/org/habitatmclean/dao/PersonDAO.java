package org.habitatmclean.dao;

import org.habitatmclean.entity.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PersonDAO implements ReadDAO<Person, Long>, CreateUpdateDeleteDAO<Person, Long> {
    private SessionFactory sessionFacotry = null;

    public PersonDAO() {}
    public PersonDAO(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    public void setSessionFacotry(SessionFactory sessionFacotry) {
        this.sessionFacotry = sessionFacotry;
    }

    @Override
    public Person findByPrimaryKey(Long id) {
        Session session = sessionFacotry.getCurrentSession();
        Object obj = session.load(Person.class, id);
        return (Person) obj;
    }

    @Override
    public Person save(Person entity) {
        Session session = sessionFacotry.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Person entity) {
        sessionFacotry.getCurrentSession().delete(entity);
    }

    @Override
    public List<Person> findAll() {
        Session session = sessionFacotry.getCurrentSession();
        Query result = session.createQuery("from Person");
        return result.list();
    }
}
