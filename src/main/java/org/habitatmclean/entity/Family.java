package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="family")
@AttributeOverride(name="id", column = @Column(name="family_id"))
public class Family extends GenericEntity implements Serializable {

    private double equity_hrs;
    private double income;

    @ManyToOne(optional = false)
    @JoinColumn(name="milestone_id")
    private Milestone milestone;

    @ManyToOne(optional = false)
    @JoinColumn(name="class_id")
    private Class classType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "family")
    private List<Person> people;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "family")
    private List<Log> logs;

    public Family() { }

    public Family(double equity_hrs, double income, Milestone milestone, Class classType) {
        this.equity_hrs = equity_hrs;
        this.income = income;
        this.milestone = milestone;
        this.classType = classType;
    }

    public Family(Long id, int equity_hrs, double income, Milestone milestone, Class classType) {
        this.id = id;
        this.equity_hrs = equity_hrs;
        this.income = income;
        this.milestone = milestone;
        this.classType = classType;
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

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
}