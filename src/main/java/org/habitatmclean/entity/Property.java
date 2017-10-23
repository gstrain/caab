package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="property")
public class Property implements Serializable, RetrievableProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long property_no;

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

    @OneToOne
    @JoinColumn(name="address_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Address address;

    public Property() { }

    public Property(Long property_no, double appraised_value, Date appraised_date, double taxes, String notes, PropertyStatus property_status, Zone zone, Actor owner, Address address) {
        this.property_no = property_no;
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
    @Column(name="property_no")
    public Long getProperty_no() {
        return property_no;
    }

    public void setProperty_no(Long property_no) {
        this.property_no = property_no;
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
                return "" + getProperty_no();
            case "address_id":
                return "" + getAddress().getAddress_id();
            case "zone_id":
                return "" + getZone().getZone_id();
            case "owner_id":
                return "" + getOwner().getActor_id();
            case "property_status":
                return "" + getProperty_status().getPstatus_id();
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
}
