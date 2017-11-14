package org.habitatmclean.entity;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.SortedSet;

@Entity
@Table(name="person")
@DiscriminatorValue("P")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name = "person_id", referencedColumnName = "actor_id")
public class Person extends Actor implements Serializable {

    private String first;
    private String middle;
    private String last;
    private String email;
    private String home_phone;
    private String cell_phone;
    private String work_phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="family_id")
    @Cascade({CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private Family family;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contact")
    @Fetch(FetchMode.SUBSELECT)
    @SortNatural
    private SortedSet<Organization> organizations;

    public Person() {
        super();
    }

    public Person(String first, String middle, String last, String email, String home_phone, String cell_phone, String work_phone, Family family, RelationType relationType, Address address) {
        super(relationType, address);
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.email = email;
        this.home_phone = home_phone;
        this.cell_phone = cell_phone;
        this.work_phone = work_phone;
        this.family = family;
    }

    public Person(Long actor_id, String first, String middle, String last, String email, String home_phone, String cell_phone, String work_phone, Family family, RelationType relationType, Address address) {
        super(relationType, address);
        this.first = first;
        this.middle = middle;
        this.last = last;
        this.email = email;
        this.home_phone = home_phone;
        this.cell_phone = cell_phone;
        this.work_phone = work_phone;
        this.family = family;
    }

    public SortedSet<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(SortedSet<Organization> organizations) {
        this.organizations = organizations;
    }

    @Column(name="first")
    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    @Column(name="middle")
    public String getMiddle() {
        return middle;
    }

    public void setMiddle(String middle) {
        this.middle = middle;
    }

    @Column(name="last")
    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="home_phone")
    public String getHome_phone() {
        return home_phone;
    }

    public void setHome_phone(String home_phone) {
        this.home_phone = home_phone;
    }

    @Column(name="cell_phone")
    public String getCell_phone() {
        return cell_phone;
    }

    public void setCell_phone(String cell_phone) {
        this.cell_phone = cell_phone;
    }

    @Column(name="work_phone")
    public String getWork_phone() {
        return work_phone;
    }

    public void setWork_phone(String work_phone) {
        this.work_phone = work_phone;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "id":
                return "" + getId();
            case "family_id":
                return "" + getFamily();
            case "first":
                return "" + getFirst();
            case "middle":
                return "" + getMiddle();
            case "last":
                return "" + getLast();
            case "email":
                return "" + getEmail();
            case "home_phone":
                return "" + getHome_phone();
            case "cell_phone":
                return "" + getCell_phone();
            case "work_phone":
                return "" + getWork_phone();
            default:
                return "invalid property specifier";
        }
    }

    @Override
    public String toString() {
        return "Person: " +
                first + " " +
                middle + " " +
                last;
    }
}
