package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="zone")
public class Zone implements Serializable, RetrievableProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zone_id;

    private String zone_info;
    private String zone_desc;

    public Zone() { }

    public Zone(Long zone_id, String zone_info, String zone_desc) {
        this.zone_id = zone_id;
        this.zone_info = zone_info;
        this.zone_desc = zone_desc;
    }

    public Zone(String zone_info, String zone_desc) {
        this.zone_info = zone_info;
        this.zone_desc = zone_desc;
    }

    @Column(name="zone_id")
    public Long getZone_id() {
        return zone_id;
    }

    public void setZone_id(Long zone_id) {
        this.zone_id = zone_id;
    }

    @Column(name="zone_info")
    public String getZone_info() {
        return zone_info;
    }

    public void setZone_info(String zone_info) {
        this.zone_info = zone_info;
    }

    @Column(name="zone_desc")
    public String getZone_desc() {
        return zone_desc;
    }

    public void setZone_desc(String zone_description) {
        this.zone_desc = zone_description;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "id":
                return "" + getZone_id();
            case "zone_info":
                return "" + getZone_info();
            case "zone_desc":
                return "" + getZone_desc();
            default:
                return "invalid property specifier";
        }
    }
}
