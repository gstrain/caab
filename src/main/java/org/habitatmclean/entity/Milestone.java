package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="milestone")
public class Milestone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long milestone_id;

    private String milestone;
    private String mileStone_desc;

    public Milestone() { }

    public Milestone(Long milestone_id, String milestone, String mileStone_desc) {
        this.milestone_id = milestone_id;
        this.milestone = milestone;
        this.mileStone_desc = mileStone_desc;
    }

    public Milestone(String milestone, String mileStone_desc) {
        this.milestone = milestone;
        this.mileStone_desc = mileStone_desc;
    }

    @Column(name="milestone_id")
    public Long getMilestone_id() {
        return milestone_id;
    }

    public void setMilestone_id(Long milestone_id) {
        this.milestone_id = milestone_id;
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
