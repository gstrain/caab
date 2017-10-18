package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="organization")
@DiscriminatorValue("O")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "organization_id", referencedColumnName = "actor_id")
public class Organization extends Actor implements Serializable {

    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name="contact_id")
    private Person person;

    public Organization() { }

    public Organization(String name, Person person) {
        this.name = name;
        this.person = person;
    }

    public Organization(Long actor_id, String name, Person person) {
        super(actor_id);
        this.name = name;
        this.person = person;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
