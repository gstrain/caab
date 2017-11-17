package org.habitatmclean.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;

@Entity
@Table(name="construction_status")
@AttributeOverride(name="id", column = @Column(name="cstatus_id"))
public class ConstructionStatus extends GenericEntity implements Serializable {

    private String cstatus;
    private String cstatus_description;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "houseConstructionStatus")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<House> houses;

    public ConstructionStatus() { }

    public ConstructionStatus(Long id, String cstatus, String cstatus_description) {
        this.id = id;
        this.cstatus = cstatus;
        this.cstatus_description = cstatus_description;
    }

    public SortedSet<House> getHouses() {
        return houses;
    }

    public void setHouses(SortedSet<House> houses) {
        this.houses = houses;
    }

    @Column(name="cstatus")
    public String getCstatus() {
        return cstatus;
    }

    public void setCstatus(String cstatus) {
        this.cstatus = cstatus;
    }

    @Column(name="cstatus_description")
    public String getCstatus_description() {
        return cstatus_description;
    }

    public void setCstatus_description(String cstatus_description) {
        this.cstatus_description = cstatus_description;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "cstatus_id":
            case "id":
                return "" + getId();
            case "cstatus":
                return "" + getCstatus();
            case "cstatus_description":
                return "" + getCstatus_description();
            case "this":
                return this.toString();
            default:
                return "invalid property specifier";
        }
    }

    @Override
    public String toString() {
        return cstatus + ": " + cstatus_description;
    }
}
