package org.habitatmclean.entity;

import org.habitatmclean.hibernate.Functions;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="log")
@AttributeOverride(name="id", column = @Column(name="log_id"))
public class Log extends GenericEntity implements Serializable {

    private String reason;
    @Temporal(TemporalType.TIMESTAMP) // saves date and time
    private Date date;
    private String notes;
    private String status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="family_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private Family logFamily;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="contact_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private Actor logContact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="house_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private House logHouse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="property_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private Property logProperty;

    public Log() { }

    public Log(Long id, String reason, Date date, String notes, String status, Family logFamily, Actor logContact, House logHouse, Property logProperty) {
        this.id = id;
        this.reason = reason;
        this.date = date;
        this.notes = notes;
        this.status = status;
        this.logFamily = logFamily;
        this.logContact = logContact;
        this.logHouse = logHouse;
        this.logProperty = logProperty;
    }

    public Log(String reason, Date date, String notes, String status, Family logFamily, Actor logContact, House logHouse, Property logProperty) {
        this.reason = reason;
        this.date = date;
        this.notes = notes;
        this.status = status;
        this.logFamily = logFamily;
        this.logContact = logContact;
        this.logHouse = logHouse;
        this.logProperty = logProperty;
    }

    @Column(name="reason")
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Column(name="date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name="notes")
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Column(name="status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Family getLogFamily() {
        return logFamily;
    }

    public void setLogFamily(Family family) {
        this.logFamily = family;
    }

    public Actor getLogContact() {
        return logContact;
    }

    public void setLogContact(Actor actor) {
        this.logContact = actor;
    }

    public House getLogHouse() {
        return logHouse;
    }

    public void setLogHouse(House house) {
        this.logHouse = house;
    }

    public Property getLogProperty() {
        return logProperty;
    }

    public void setLogProperty(Property property) {
        this.logProperty = property;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "log_id":
            case "id":
                return "" + getId();
            case "logFamily":
                return "" + getLogFamily();
            case "logContact":
                return "" + getLogContact();
            case "logHouse":
                return "" + getLogHouse();
            case "logProperty":
                return "" + getLogProperty();
            case "reason":
                return "" + getReason();
            case "date":
                return "" + getDate();
            case "notes":
                return "" + getNotes();
            case "status":
                return getStatus();
            case "this":
                return toString();
            default:
                return "invalid property specifier";
        }
    }

    @Override
    public String toString() {
        return "Log: " +
                date.toString() + Functions.NEWLINE_TAB +
                "notes: " + notes;
    }
}