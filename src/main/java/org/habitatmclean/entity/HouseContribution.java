package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="house_contribution")
public class HouseContribution implements Serializable, RetrievableProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contribution_id;

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

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "contribution_id":
                return "" + getContribution_id();
            case "actor_id":
                return "" + getActor().getActor_id();
            case "house_id":
                return "" + getHouse().getHouse_id();
            case "involvement_desc":
                return "" + getInvolvementDescription();
            default:
                return "invalid property specifier";
        }
    }
}