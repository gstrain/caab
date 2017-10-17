package org.habitatmclean.entity;

import javax.persistence.*;

@Entity
@Table(name="house_contribution")
public class HouseContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contribution_id;

    private String involvementDescription;
    private Actor actor;
    private House house;

    public HouseContribution(String involvementDescription, Actor actor, House house) {
        this.involvementDescription = involvementDescription;
        this.actor = actor;
        this.house = house;
    }

    public HouseContribution(Long contribution_id, String involvementDescription, Actor actor, House house) {
        this.contribution_id = contribution_id;
        this.involvementDescription = involvementDescription;
        this.actor = actor;
        this.house = house;
    }

    @Column(name="contribution_id")
    public Long getContribution_id() {
        return contribution_id;
    }

    public void setContribution_id(Long contribution_id) {
        this.contribution_id = contribution_id;
    }

    @Column(name="involvement_desc")
    public String getInvolvementDescription() {
        return involvementDescription;
    }

    public void setInvolvementDescription(String involvementDescription) {
        this.involvementDescription = involvementDescription;
    }

    @ManyToOne(optional = false)
    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    @ManyToOne(optional = false)
    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }
}