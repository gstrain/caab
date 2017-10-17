package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="log")
public class Log implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long log_id;

    private String reason;
    @Temporal(TemporalType.TIMESTAMP) // saves date and time
    private Date date;
    private String notes;
    private String status;
    private String type;
    private Family family;
    private Actor actor;
    private House house;
    private Property property;

    public Log() { }

    public Log(Long log_id, String reason, Date date, String notes, String status, String type, Family family, Actor actor, House house, Property property) {
        this.log_id = log_id;
        this.reason = reason;
        this.date = date;
        this.notes = notes;
        this.status = status;
        this.type = type;
        this.family = family;
        this.actor = actor;
        this.house = house;
        this.property = property;
    }

    public Log(String reason, Date date, String notes, String status, String type, Family family, Actor actor, House house, Property property) {
        this.reason = reason;
        this.date = date;
        this.notes = notes;
        this.status = status;
        this.type = type;
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

    @Column(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne(optional = true)
    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    @ManyToOne(optional = false)
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @ManyToOne(optional=true)
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @ManyToOne(optional = true)
    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}

