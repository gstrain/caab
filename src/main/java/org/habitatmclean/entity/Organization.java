package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="organization")
@DiscriminatorValue("O")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "organization_id", referencedColumnName = "actor_id")
public class Organization extends Actor implements Serializable, RetrievableProperties {

    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name="contact_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
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

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "id":
                return "" + getActor_id();
            case "name":
                return "" + getName();
            case "contact_id":
                return "" + getPerson().getActor_id();
            default:
                return "invalid property specifier";
        }
    }
}
