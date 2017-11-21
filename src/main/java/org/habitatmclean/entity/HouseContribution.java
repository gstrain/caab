package org.habitatmclean.entity;

import org.habitatmclean.hibernate.Functions;
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
    private Actor contributor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="house_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private House contributionHouse;

    public HouseContribution() { }

    public HouseContribution(String involvementDescription, Actor contributor, House contributionHouse) {
        this.involvementDescription = involvementDescription;
        this.contributor = contributor;
        this.contributionHouse = contributionHouse;
    }

    public HouseContribution(Long id, String involvementDescription, Actor contributor, House contributionHouse) {
        this.id = id;
        this.involvementDescription = involvementDescription;
        this.contributor = contributor;
        this.contributionHouse = contributionHouse;
    }


    public String getInvolvementDescription() {
        return involvementDescription;
    }

    public void setInvolvementDescription(String involvementDescription) {
        this.involvementDescription = involvementDescription;
    }

    public Actor getContributor() {
        return contributor;
    }

    public void setContributor(Actor actor) {
        this.contributor = actor;
    }

    public House getContributionHouse() {
        return contributionHouse;
    }

    public void setContributionHouse(House house) {
        this.contributionHouse = house;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch (property) {
            case "contribution_id":
            case "id":
                return "" + getId();
            case "contributor":
                return "" + getContributor();
            case "contributionHouse":
                return "" + getContributionHouse();
            case "involvement_desc":
                return "" + getInvolvementDescription();
            case "this":
                return toString();
            default:
                return "invalid property specifier";
        }
    }

    @Override
    public String toString() {
        return "Contribution: " + Functions.NEWLINE_TAB +
                involvementDescription + Functions.NEWLINE_TAB +
                "House #: " + contributionHouse.getId();
    }
}