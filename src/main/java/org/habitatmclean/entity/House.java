package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="house")
public class House implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long house_id;

    private double construction_cost;
    private int size;
    private int bedrooms;
    private double bathrooms;
    private ConstructionStatus construction_status;
    private HouseStyle house_style;
    private Property property;
    private Address address;
    private Family family;

    public House(Long house_id, Family family, double construction_cost, int size, int bedrooms, double bathrooms, ConstructionStatus construction_status, HouseStyle house_style, Property property, Address address) {
        this.house_id = house_id;
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

    @ManyToOne(optional = true)
    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public House() { }

    @ManyToOne(optional = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne(optional = false)
    public ConstructionStatus getConstruction_status() {
        return construction_status;
    }

    public void setConstruction_status(ConstructionStatus construction_status) {
        this.construction_status = construction_status;
    }

    @ManyToOne(optional = false)
    public HouseStyle getHouse_style() {
        return house_style;
    }

    public void setHouse_style(HouseStyle house_style) {
        this.house_style = house_style;
    }

    @ManyToOne(optional = false)
    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    @Column(name="house_id")
    public Long getHouse_id() {
        return house_id;
    }

    public void setHouse_id(Long house_id) {
        this.house_id = house_id;
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
