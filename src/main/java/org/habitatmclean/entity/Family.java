package org.habitatmclean.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;

@Entity
@Table(name="family")
@AttributeOverride(name="id", column = @Column(name="family_id"))
public class Family extends GenericEntity implements Serializable {

    private double equity_hrs;
    private double income;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="milestone_id")
    @Fetch(FetchMode.JOIN)
    private Milestone milestone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="class_id")
    @Fetch(FetchMode.JOIN)
    private Class classType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "family")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Person> people;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "family", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Log> logs;

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

    public SortedSet<Person> getPeople() {
        return people;
    }

    public void setPeople(SortedSet<Person> people) {
        this.people = people;
    }

    public SortedSet<Log> getLogs() {
        return logs;
    }

    public void setLogs(SortedSet<Log> logs) {
        this.logs = logs;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "family_id":
            case "id":
                return "" + getId();
            case "class_id":
                return "" + getClassType().getId();
            case "milestone_id":
                return "" + getMilestone().getId();
            case "equity_hrs":
                return "" + getEquity_hrs();
            case "income":
                return "" + getIncome();
            case "this":
                return this.toString();
            default:
                return "invalid property specifier";
        }
    }

    @Override
    public String toString() {
        return "Family: #" +
                id + ", " +
                equity_hrs + " equity hrs, " +
                String.format("$%.2f", income) + " income, " +
                milestone;
    }
}