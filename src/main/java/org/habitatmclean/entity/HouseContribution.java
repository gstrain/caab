package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="house_contribution")
@AttributeOverride(name="id", column = @Column(name="contribution_id"))
public class HouseContribution extends GenericEntity implements Serializable {

    @Column(name="involvement_desc")
    private String involvementDescription;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="actor_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private Actor actor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="house_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
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