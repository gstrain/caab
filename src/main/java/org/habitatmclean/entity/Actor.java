package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;

import javax.management.relation.Relation;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="actor")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "actor_type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("A")
public class Actor implements Serializable, RetrievableProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actor_id;

    @ManyToOne
    @JoinColumn(name="relation_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private RelationType relationType;

    @OneToOne(optional = false)
    @JoinColumn(name="address_id")
    private Address address;

    public Actor() { }

    public Actor(Long actor_id) {
        this.actor_id = actor_id;
    }

    public Actor(RelationType relationType, Address address) {
        this.relationType = relationType;
        this.address = address;
    }

    public Actor(Long actor_id, RelationType relationType, Address address) {
        this.actor_id = actor_id;
        this.relationType = relationType;
        this.address = address;
    }

    @Column(name="actor_id")
    public Long getActor_id() {
        return actor_id;
    }

    public void setActor_id(Long actor_id) {
        this.actor_id = actor_id;
    }

    public RelationType getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationType relationType) {
        this.relationType = relationType;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "actor_id":
                return "" + getActor_id();
            case "address_id":
                return "" + getAddress().getAddress_id();
            case "relation_id":
                return "" + getRelationType().getRelation_id();
            case "actor_type":
                return "" + getActor_id();
            default:
                return "invalid property specifier";
        }
    }
}
