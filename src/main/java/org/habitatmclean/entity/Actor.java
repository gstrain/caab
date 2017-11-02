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
@AttributeOverride(name="id", column = @Column(name="actor_id"))
public class Actor extends GenericEntity implements Serializable {

    @ManyToOne
    @JoinColumn(name="relation_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private RelationType relationType;

    @OneToOne(optional = false)
    @JoinColumn(name="address_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Address address;

    public Actor() { }

    public Actor(Long id) {
        this.id = id;
    }
    public Actor(RelationType relationType, Address address) {
        this.relationType = relationType;
        this.address = address;
    }

    public Actor(Long id, RelationType relationType, Address address) {
        this.id = id;
        this.relationType = relationType;
        this.address = address;
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

}
