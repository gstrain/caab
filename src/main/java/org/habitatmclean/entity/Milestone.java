package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="milestone")
@AttributeOverride(name="id", column = @Column(name="milestone_id"))
public class Milestone extends GenericEntity implements Serializable {

    private String milestone;
    private String mileStone_desc;

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

}
