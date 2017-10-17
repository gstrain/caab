package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="property")
public class Property implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long property_no;

    private double appriased_value;
    @Temporal(TemporalType.TIMESTAMP) // includes date & time
    private Date appriased_date;
    private double taxes;
    private String notes;
    private PropertyStatus property_status;
    private Zone zone;
    private Actor owner;

    public Property() { }

    public Property(Long property_no, double appriased_value, Date appriased_date, double taxes, String notes, PropertyStatus property_status, Zone zone, Actor owner) {
        this.property_no = property_no;
        this.appriased_value = appriased_value;
        this.appriased_date = appriased_date;
        this.taxes = taxes;
        this.notes = notes;
        this.property_status = property_status;
        this.zone = zone;
        this.owner = owner;
    }

    public Property(double appriased_value, Date appriased_date, double taxes, String notes, PropertyStatus property_status, Zone zone, Actor owner) {
        this.appriased_value = appriased_value;
        this.appriased_date = appriased_date;
        this.taxes = taxes;
        this.notes = notes;
        this.property_status = property_status;
        this.zone = zone;
        this.owner = owner;
    }

    @Column(name="property_no")
    public Long getProperty_no() {
        return property_no;
    }

    public void setProperty_no(Long property_no) {
        this.property_no = property_no;
    }

    @Column(name="appraised_value")
    public double getAppriased_value() {
        return appriased_value;
    }

    public void setAppriased_value(double appriased_value) {
        this.appriased_value = appriased_value;
    }

    @Column(name="date")
    public Date getAppriased_date() {
        return appriased_date;
    }

    public void setAppriased_date(Date appriased_date) {
        this.appriased_date = appriased_date;
    }

    @Column(name="taxes")
    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    @Column(name="notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @ManyToOne(optional = false)
    public PropertyStatus getProperty_status() {
        return property_status;
    }

    public void setProperty_status(PropertyStatus property_status) {
        this.property_status = property_status;
    }

    @ManyToOne(optional = false)
    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    @ManyToOne(optional = false)
    public Actor getOwner() {
        return owner;
    }

    public void setOwner(Actor actor) {
        this.owner = actor;
    }
}
