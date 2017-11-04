package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="property_status")
@AttributeOverride(name="id", column = @Column(name="pstatus_id"))
public class PropertyStatus extends GenericEntity implements Serializable {

    private String pstatus;
    private String pstatus_desc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property_status")
    private List<Property> properties;

    public PropertyStatus() { }

    public PropertyStatus(Long id, String pstatus, String pstatus_desc) {
        this.id = id;
        this.pstatus = pstatus;
        this.pstatus_desc = pstatus_desc;
    }

    public PropertyStatus(String pstatus, String pstatus_desc) {
        this.pstatus = pstatus;
        this.pstatus_desc = pstatus_desc;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Column(name="pstatus")
    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    @Column(name="pstatus_desc")
    public String getPstatus_desc() {
        return pstatus_desc;
    }

    public void setPstatus_desc(String pstatus_description) {
        this.pstatus_desc = pstatus_description;
    }
}
