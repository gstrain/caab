package org.habitatmclean.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class Zone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long zone_id;

    private String zone_info;
    private String zone_description;

    public Zone() { }

    public Zone(Long zone_id, String zone_info, String zone_description) {
        this.zone_id = zone_id;
        this.zone_info = zone_info;
        this.zone_description = zone_description;
    }

    public Zone(String zone_info, String zone_description) {
        this.zone_info = zone_info;
        this.zone_description = zone_description;
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

    @Column(name="zone_description")
    public String getZone_description() {
        return zone_description;
    }

    public void setZone_description(String zone_description) {
        this.zone_description = zone_description;
    }
}
