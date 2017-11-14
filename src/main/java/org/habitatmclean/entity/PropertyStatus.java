package org.habitatmclean.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;

@Entity
@Table(name="property_status")
@AttributeOverride(name="id", column = @Column(name="pstatus_id"))
public class PropertyStatus extends GenericEntity implements Serializable {

    private String pstatus;
    private String pstatus_desc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property_status")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Property> properties;

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

    public SortedSet<Property> getProperties() {
        return properties;
    }

    public void setProperties(SortedSet<Property> properties) {
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

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "pstatus_id":
            case "id":
                return "" + getId();
            case "pstatus":
                return "" + getPstatus();
            case "pstatus_desc":
                return "" + getPstatus_desc();
            default:
                return "invalid property specifier";
        }
    }
}
