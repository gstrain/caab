package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="property-status")
public class PropertyStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pstatus_id;

    private String pstatus;
    private String pstatus_description;

    public PropertyStatus() { }

    public PropertyStatus(Long pstatus_id, String pstatus, String pstatus_description) {
        this.pstatus_id = pstatus_id;
        this.pstatus = pstatus;
        this.pstatus_description = pstatus_description;
    }

    public PropertyStatus(String pstatus, String pstatus_description) {
        this.pstatus = pstatus;
        this.pstatus_description = pstatus_description;
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
    public String getPstatus_description() {
        return pstatus_description;
    }

    public void setPstatus_description(String pstatus_description) {
        this.pstatus_description = pstatus_description;
    }
}
