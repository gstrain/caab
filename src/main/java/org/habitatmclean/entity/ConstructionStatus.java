package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="construction_status")
@AttributeOverride(name="id", column = @Column(name="cstatus_id"))
public class ConstructionStatus extends GenericEntity implements Serializable {

    private String cstatus;
    private String cstatus_description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "construction_status")
    private List<House> houses;

    public ConstructionStatus() { }

    public ConstructionStatus(Long id, String cstatus, String cstatus_description) {
        this.id = id;
        this.cstatus = cstatus;
        this.cstatus_description = cstatus_description;
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
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

}
