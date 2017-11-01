package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="construction_status")
public class ConstructionStatus implements Serializable, RetrievableProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cstatus_id;

    private String cstatus;
    private String cstatus_description;

    public ConstructionStatus() { }

    public ConstructionStatus(Long cstatus_id, String cstatus, String cstatus_description) {
        this.cstatus_id = cstatus_id;
        this.cstatus = cstatus;
        this.cstatus_description = cstatus_description;
    }

    @Column(name="cstatus_id")
    public Long getCstatus_id() {
        return cstatus_id;
    }

    public void setCstatus_id(Long cstatus_id) {
        this.cstatus_id = cstatus_id;
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
            case "id":
                return "" + getCstatus_id();
            case "cstatus":
                return "" + getCstatus();
            case "cstatus_description":
                return "" + getCstatus_description();
            default:
                return "invalid property specifier";
        }
    }
}
