package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;

@Entity
@Table(name="actor")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(
        name = "actor_type",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("A")
@AttributeOverride(name="id", column = @Column(name="actor_id"))
public class Actor extends GenericEntity implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="relation_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private RelationType relationType;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="address_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Address address;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "actor", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Log> logs;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "actor", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<HouseContribution> contributions;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "owner")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Property> properties;

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


    public SortedSet<HouseContribution> getContributions() {
        return contributions;
    }

    public void setContributions(SortedSet<HouseContribution> contributions) {
        this.contributions = contributions;
    }

    public SortedSet<Property> getProperties() {
        return properties;
    }

    public void setProperties(SortedSet<Property> properties) {
        this.properties = properties;
    }

    public SortedSet<Log> getLogs() {
        return logs;
    }

    public void setLogs(SortedSet<Log> logs) {
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

    @Override
    public String getValueByPropertyName(String property) {
        switch (property) {
            case "actor_id":
            case "id":
                return "" + getId();
            case "address_id":
                return "" + getAddress().getId();
            case "relation_id":
                return "" + getRelationType().getId();
            default:
                return "invalid property specifier";
        }
    }
}
