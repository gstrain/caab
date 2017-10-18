package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="family")
public class Family implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long family_id;

    private double equity_hrs;
    private double income;
    private Milestone milestone;
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

    @ManyToOne(optional = false)
    public Milestone getMilestone() {
        return milestone;
    }

    public void setMilestone(Milestone milestone) {
        this.milestone = milestone;
    }

    @ManyToOne(optional = false)
    public Class getClassType() {
        return classType;
    }

    public void setClassType(Class classType) {
        this.classType = classType;
    }
}