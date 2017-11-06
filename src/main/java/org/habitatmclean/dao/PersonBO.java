package org.habitatmclean.dao;

import org.habitatmclean.entity.Person;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class PersonBO extends GenericDao implements ReadDAO<Person, Long> {
    @Override
    public Person findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Person.class, id);
        return (Person) obj;
    }

    @Override
    public List<Person> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Person");
        return result.list();
    }
}
