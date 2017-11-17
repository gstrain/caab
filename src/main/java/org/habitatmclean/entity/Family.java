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
    private Milestone familyMilestone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="class_id")
    @Fetch(FetchMode.JOIN)
    private Class familyClassType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "family")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Person> people;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "logFamily", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Log> logs;

    public Family() { }

    public Family(double equity_hrs, double income, Milestone familyMilestone, Class familyClassType) {
        this.equity_hrs = equity_hrs;
        this.income = income;
        this.familyMilestone = familyMilestone;
        this.familyClassType = familyClassType;
    }

    public Family(Long id, int equity_hrs, double income, Milestone familyMilestone, Class familyClassType) {
        this.id = id;
        this.equity_hrs = equity_hrs;
        this.income = income;
        this.familyMilestone = familyMilestone;
        this.familyClassType = familyClassType;
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

    public Milestone getFamilyMilestone() {
        return familyMilestone;
    }

    public void setFamilyMilestone(Milestone milestone) {
        this.familyMilestone = milestone;
    }

    public Class getFamilyClassType() {
        return familyClassType;
    }

    public void setFamilyClassType(Class classType) {
        this.familyClassType = classType;
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
            case "familyClassType":
                return "" + getFamilyClassType();
            case "familyMilestone":
                return "" + getFamilyMilestone();
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
                familyMilestone;
    }
}