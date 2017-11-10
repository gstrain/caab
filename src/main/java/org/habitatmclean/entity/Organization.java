package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;

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
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Person contact;

    public Organization() { }

    public Organization(String name, Person contact, RelationType relationType, Address address) {
        this.name = name;
        this.contact = contact;
    }

    public Organization(Long actor_id, String name, Person contact, Address address, RelationType relationType) {
        super(relationType, address);
        this.name = name;
        this.contact = contact;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getContact() {
        return contact;
    }

    public void setContact(Person person) {
        this.contact = person;
    }
}
