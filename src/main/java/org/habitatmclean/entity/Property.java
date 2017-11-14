package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.SortedSet;

@Entity
@Table(name="property")
@AttributeOverride(name="id", column = @Column(name="property_no"))
public class Property extends GenericEntity implements Serializable {

    private double appraised_value;
    private Date appraised_date;
    private double taxes;
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="pstatus_id")
    @Fetch(FetchMode.JOIN)
    private PropertyStatus property_status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="zone_id")
    @Fetch(FetchMode.JOIN)
    private Zone zone;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="owner_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private Actor owner;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="address_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Log> logs;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "property")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<House> houses;

    public Property() {}

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

    public SortedSet<Log> getLogs() {
        return logs;
    }

    public void setLogs(SortedSet<Log> logs) {
        this.logs = logs;
    }

    public SortedSet<House> getHouses() {
        return houses;
    }

    public void setHouses(SortedSet<House> houses) {
        this.houses = houses;
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

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "property_no":
                return "" + getId();
            case "address_id":
                return "" + getAddress().getId();
            case "zone_id":
                return "" + getZone().getId();
            case "owner_id":
                return "" + getOwner().getId();
            case "property_status":
                return "" + getProperty_status().getId();
            case "appraised_value":
                return "" + getAppraised_value();
            case "appraised_date":
                return "" + getAppraised_date();
            case "taxes":
                return "" + getTaxes();
            case "notes":
                return "" + getNotes();
            default:
                return "invalid property specifier";
        }
    }

    @Override
    public String toString() {
        return "Property: " +
                "Property #" + id + "\n\t" +
                "taxes: " + taxes +
                "zone: " + zone.getZone_desc() + " \n\t" +
                address.toString();
    }
}
