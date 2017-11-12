package org.habitatmclean.dao;

import org.habitatmclean.entity.Person;

public class PersonBO extends GenericDao<Person> {
    public PersonBO() {
        super(Person.class);
    }
}
