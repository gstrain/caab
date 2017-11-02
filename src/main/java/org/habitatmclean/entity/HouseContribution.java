package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="house_contribution")
@AttributeOverride(name="id", column = @Column(name="contribution_id"))
public class HouseContribution extends GenericEntity implements Serializable {

    private String involvementDescription;

    @ManyToOne(optional = false)
    @JoinColumn(name="actor_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Actor actor;

    @ManyToOne(optional = false)
    @JoinColumn(name="house_id")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private House house;

    public HouseContribution() { }

    public HouseContribution(String involvementDescription, Actor actor, House house) {
        this.involvementDescription = involvementDescription;
        this.actor = actor;
        this.house = house;
    }

    public HouseContribution(Long id, String involvementDescription, Actor actor, House house) {
        this.id = id;
        this.involvementDescription = involvementDescription;
        this.actor = actor;
        this.house = house;
    }

    @Column(name="involvement_desc")
    public String getInvolvementDescription() {
        return involvementDescription;
    }

    public void setInvolvementDescription(String involvementDescription) {
        this.involvementDescription = involvementDescription;
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

}