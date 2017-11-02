package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="family")
public class Family implements Serializable, RetrievableProperties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long family_id;

    private double equity_hrs;
    private double income;

    @ManyToOne(optional = false)
    @JoinColumn(name="milestone_id")
    private Milestone milestone;

    @ManyToOne(optional = false)
    @JoinColumn(name="class_id")
    private Class classType;

    public Family() { }

    public Family(double equity_hrs, double income, Milestone milestone, Class classType) {
        this.equity_hrs = equity_hrs;
        this.income = income;
        this.milestone = milestone;
        this.classType = classType;
    }

    public Family(Long family_id, int equity_hrs, double income, Milestone milestone, Class classType) {
        this.family_id = family_id;
        this.equity_hrs = equity_hrs;
        this.income = income;
        this.milestone = milestone;
        this.classType = classType;
    }

    @Column(name="family_id")
    public Long getFamily_id() {
        return family_id;
    }

    public void setFamily_id(Long family_id) {
        this.family_id = family_id;
    }

    @Column(name="equity_hrs")
    public double getEquity_hrs() {
        return equity_hrs;
    }

    public void setEquity_hrs(double equity_hrs) {
        this.equity_hrs = equity_hrs;
    }

    @Column(name="income")
    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    public Class getClassType() {
        return classType;
    }

    public void setClassType(Class classType) {
        this.classType = classType;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "id":
                return "" + getFamily_id();
            case "class_id":
                return "" + getClassType().getClass_id();
            case "milestone_id":
                return "" + getMilestone().getMilestone_id();
            case "equity_hrs":
                return "" + getEquity_hrs();
            case "income":
                return "" + getIncome();
            default:
                return "invalid property specifier";
        }
    }
}
