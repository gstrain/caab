package org.habitatmclean.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;

@Entity
@Table(name="milestone")
@AttributeOverride(name="id", column = @Column(name="milestone_id"))
public class Milestone extends GenericEntity implements Serializable {

    private String milestone;
    private String mileStone_desc;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "milestone")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Family> families;

    public Milestone() { }

    public Milestone(Long id, String milestone, String mileStone_desc) {
        this.id = id;
        this.milestone = milestone;
        this.mileStone_desc = mileStone_desc;
    }

    public Milestone(String milestone, String mileStone_desc) {
        this.milestone = milestone;
        this.mileStone_desc = mileStone_desc;
    }

    public SortedSet<Family> getFamilies() {
        return families;
    }

    public void setFamilies(SortedSet<Family> families) {
        this.families = families;
    }

    @Column(name="milestone")
    public String getMilestone() {
        return milestone;
    }

    public void setMilestone(String milestone) {
        this.milestone = milestone;
    }

    @Column(name="milestone_desc")
    public String getMileStone_desc() {
        return mileStone_desc;
    }

    public void setMileStone_desc(String mileStone_desc) {
        this.mileStone_desc = mileStone_desc;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "milestone_id":
            case "id":
                return "" + getId();
            case "milestone":
                return "" + getMilestone();
            case "milestone_desc":
                return "" + getMileStone_desc();
            default:
                return "invalid property specifier";
        }
    }
}
