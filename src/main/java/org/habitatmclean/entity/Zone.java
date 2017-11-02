package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="zone")
@AttributeOverride(name="id", column = @Column(name="zone_id"))
public class Zone extends GenericEntity implements Serializable {

    private String zone_info;
    private String zone_desc;

    public Zone() { }

    public Zone(Long id, String zone_info, String zone_desc) {
        this.id = id;
        this.zone_info = zone_info;
        this.zone_desc = zone_desc;
    }

    public Zone(String zone_info, String zone_desc) {
        this.zone_info = zone_info;
        this.zone_desc = zone_desc;
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

}
