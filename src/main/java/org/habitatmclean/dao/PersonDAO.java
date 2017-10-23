package org.habitatmclean.dao;

import org.habitatmclean.entity.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class PersonDAO implements ReadDAO<Person, Long>, CreateUpdateDeleteDAO<Person, Long> {
    private SessionFactory sessionFactory = null;

    public PersonDAO() {}
    public PersonDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Person findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Person.class, id);
        return (Person) obj;
    }

    @Override
    public Person save(Person entity) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(entity);
        return entity;
    }

    @Override
    public void delete(Person entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public List<Person> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Person");
        return result.list();
    }
}
