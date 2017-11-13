package org.habitatmclean.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.SortedSet;

@Entity
@Table(name="house")
@AttributeOverride(name="id", column = @Column(name="house_id"))
public class House extends GenericEntity implements Serializable {

    private double construction_cost;
    private int size;
    private int bedrooms;
    private double bathrooms;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="cstatus_id")
    @Fetch(FetchMode.JOIN)
    private ConstructionStatus construction_status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="house_style")
    @Fetch(FetchMode.JOIN)
    private HouseStyle house_style;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "property_id")
    @Fetch(FetchMode.JOIN)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    private Property property;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name="family_id")
    @Fetch(FetchMode.JOIN)
    private Family family;

    @OneToOne(optional = false)
    @JoinColumn(name="address_id")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    private Address address;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "house")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<HouseContribution> contributions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "house")
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Log> logs;

    public House(Long id, Family family, double construction_cost, int size, int bedrooms, double bathrooms, ConstructionStatus construction_status, HouseStyle house_style, Property property, Address address) {
        this.id = id;
        this.family = family;
        this.construction_cost = construction_cost;
        this.size = size;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.construction_status = construction_status;
        this.house_style = house_style;
        this.property = property;
        this.address = address;
    }

    public House(double construction_cost, Family family, int size, int bedrooms, double bathrooms, ConstructionStatus construction_status, HouseStyle house_style, Property property, Address address) {
        this.construction_cost = construction_cost;
        this.family = family;
        this.size = size;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.construction_status = construction_status;
        this.house_style = house_style;
        this.property = property;
        this.address = address;
    }

    public SortedSet<Log> getLogs() {
        return logs;
    }

    public void setLogs(SortedSet<Log> logs) {
        this.logs = logs;
    }

    public SortedSet<HouseContribution> getContributions() {
        return contributions;
    }

    public void setContributions(SortedSet<HouseContribution> contributions) {
        this.contributions = contributions;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public House() { }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ConstructionStatus getConstruction_status() {
        return construction_status;
    }

    public void setConstruction_status(ConstructionStatus construction_status) {
        this.construction_status = construction_status;
    }

    public HouseStyle getHouse_style() {
        return house_style;
    }

    public void setHouse_style(HouseStyle house_style) {
        this.house_style = house_style;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Column(name="construction_cost")
    public double getConstruction_cost() {
        return construction_cost;
    }

    public void setConstruction_cost(double construction_cost) {
        this.construction_cost = construction_cost;
    }

    @Column(name="size")
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Column(name="bedrooms")
    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    @Column(name="bathrooms")
    public double getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(double bathrooms) {
        this.bathrooms = bathrooms;
    }

}
