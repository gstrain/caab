package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="log")
public class Log implements Serializable, RetrievableProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long log_id;

    private String reason;
    @Temporal(TemporalType.TIMESTAMP) // saves date and time
    private Date date;
    private String notes;
    private String status;

    @ManyToOne
    @JoinColumn(name="family_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Family family;

    @ManyToOne(optional = false)
    @JoinColumn(name="contact_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Actor actor;

    @ManyToOne
    @JoinColumn(name="house_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private House house;

    @ManyToOne
    @JoinColumn(name="property_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Property property;

    public Log() { }

    public Log(Long log_id, String reason, Date date, String notes, String status, Family family, Actor actor, House house, Property property) {
        this.log_id = log_id;
        this.reason = reason;
        this.date = date;
        this.notes = notes;
        this.status = status;
        this.family = family;
        this.actor = actor;
        this.house = house;
        this.property = property;
    }

    public Log(String reason, Date date, String notes, String status, Family family, Actor actor, House house, Property property) {
        this.reason = reason;
        this.date = date;
        this.notes = notes;
        this.status = status;
        this.family = family;
        this.actor = actor;
        this.house = house;
        this.property = property;
    }

    @Column(name="log_id")
    public Long getLog_id() {
        return log_id;
    }

    public void setLog_id(Long log_id) {
        this.log_id = log_id;
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

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "id":
                return "" + getLog_id();
            case "family_id":
                return "" + getFamily().getFamily_id();
            case "contact_id":
                return "" + getActor().getActor_id();
            case "house_id":
                return "" + getHouse().getHouse_id();
            case "property_id":
                return "" + getProperty().getProperty_no();
            case "reason":
                return "" + getReason();
            case "date":
                return "" + getDate();
            case "notes":
                return "" + getNotes();
            case "status":
                return getStatus();
            default:
                return "invalid property specifier";
        }
    }
}

