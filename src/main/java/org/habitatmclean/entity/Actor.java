package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

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
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actor")
    private List<Log> logs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "actor")
    private List<HouseContribution> contributions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    private List<Property> properties;

    public Actor() { }

    public Actor(RelationType relationType, Address address) {
        this.relationType = relationType;
        this.address = address;
    }
    public Actor(Long id, RelationType relationType, Address address) {
        this.id = id;
        this.relationType = relationType;
        this.address = address;
    }


    public List<HouseContribution> getContributions() {
        return contributions;
    }

    public void setContributions(List<HouseContribution> contributions) {
        this.contributions = contributions;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
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
