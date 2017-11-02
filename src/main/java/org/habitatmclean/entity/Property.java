package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="property")
@AttributeOverride(name="id", column = @Column(name="property_no"))
public class Property extends GenericEntity implements Serializable {

    private double appraised_value;
    private Date appraised_date;
    private double taxes;
    private String notes;

    @ManyToOne(optional = false)
    @JoinColumn(name="pstatus_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private PropertyStatus property_status;

    @ManyToOne(optional = false)
    @JoinColumn(name="zone_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Zone zone;

    @ManyToOne(optional = false)
    @JoinColumn(name="owner_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Actor owner;

    @OneToOne(optional = false)
    @JoinColumn(name="address_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Address address;

    public Property() { }

    public Property(Long id, double appraised_value, Date appraised_date, double taxes, String notes, PropertyStatus property_status, Zone zone, Actor owner, Address address) {
        this.id = id;
        this.appraised_value = appraised_value;
        this.appraised_date = appraised_date;
        this.taxes = taxes;
        this.notes = notes;
        this.property_status = property_status;
        this.zone = zone;
        this.owner = owner;
        this.address = address;
    }

    public Property(double appraised_value, Date appraised_date, double taxes, String notes, PropertyStatus property_status, Zone zone, Actor owner, Address address) {
        this.appraised_value = appraised_value;
        this.appraised_date = appraised_date;
        this.taxes = taxes;
        this.notes = notes;
        this.property_status = property_status;
        this.zone = zone;
        this.owner = owner;
        this.address = address;
    }

    @Column(name="appraised_value")
    public double getAppraised_value() {
        return appraised_value;
    }

    public void setAppraised_value(double appraised_value) {
        this.appraised_value = appraised_value;
    }

    @Column(name="appraised_date")
    @Temporal(TemporalType.TIMESTAMP) // includes date & time
    public Date getAppraised_date() {
        return appraised_date;
    }

    public void setAppraised_date(Date appraised_date) {
        this.appraised_date = appraised_date;
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

    public PropertyStatus getProperty_status() {
        return property_status;
    }

    public void setProperty_status(PropertyStatus property_status) {
        this.property_status = property_status;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Actor getOwner() {
        return owner;
    }

    public void setOwner(Actor actor) {
        this.owner = actor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
