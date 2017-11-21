package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="organization")
@DiscriminatorValue("O")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "organization_id", referencedColumnName = "actor_id")
public class Organization extends Actor implements Serializable {

    private String name;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="contact_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private Person organizationContact;

    public Organization() { }

    public Organization(String name, Person organizationContact, RelationType relationType, Address address) {
        this.name = name;
        this.organizationContact = organizationContact;
    }

    public Organization(Long actor_id, String name, Person organizationContact, Address address, RelationType relationType) {
        super(relationType, address);
        this.name = name;
        this.organizationContact = organizationContact;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getOrganizationContact() {
        return organizationContact;
    }

    public void setOrganizationContact(Person person) {
        this.organizationContact = person;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "organization_id":
            case "id":
                return "" + getId();
            case "name":
                return "" + getName();
            case "organizationContact":
                return "" + getOrganizationContact();
            case "this":
                return toString();
            default:
                return "invalid property specifier";
        }
    }

    @Override
    public String toString() {
        return "Organization: " + name;
    }
}
