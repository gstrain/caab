package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="property_status")
public class PropertyStatus implements Serializable, RetrievableProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pstatus_id;

    private String pstatus;
    private String pstatus_desc;

    public PropertyStatus() { }

    public PropertyStatus(Long pstatus_id, String pstatus, String pstatus_desc) {
        this.pstatus_id = pstatus_id;
        this.pstatus = pstatus;
        this.pstatus_desc = pstatus_desc;
    }

    public PropertyStatus(String pstatus, String pstatus_desc) {
        this.pstatus = pstatus;
        this.pstatus_desc = pstatus_desc;
    }

    @Column(name="pstatus_id")
    public Long getPstatus_id() {
        return pstatus_id;
    }

    public void setPstatus_id(Long pstatus_id) {
        this.pstatus_id = pstatus_id;
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
                return "" + getPstatus_id();
            case "pstatus":
                return "" + getPstatus();
            case "pstatus_desc":
                return "" + getPstatus_desc();
            default:
                return "invalid property specifier";
        }
    }
}
