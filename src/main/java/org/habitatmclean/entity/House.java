package org.habitatmclean.entity;

import org.habitatmclean.hibernate.Functions;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cstatus_id")
    @Fetch(FetchMode.JOIN)
    private ConstructionStatus houseConstructionStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="house_style")
    @Fetch(FetchMode.JOIN)
    private HouseStyle house_style;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "property_id")
    @Fetch(FetchMode.JOIN)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE})
    private Property houseProperty;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @Cascade({org.hibernate.annotations.CascadeType.MERGE, org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name="family_id")
    @Fetch(FetchMode.JOIN)
    private Family houseFamily;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="address_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Address houseAddress;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contributionHouse", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<HouseContribution> contributions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "logHouse", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @SortNatural
    @Fetch(FetchMode.SUBSELECT)
    private SortedSet<Log> logs;

    public House(Long id, Family houseFamily, double construction_cost, int size, int bedrooms, double bathrooms, ConstructionStatus houseConstructionStatus, HouseStyle house_style, Property houseProperty, Address houseAddress) {
        this.id = id;
        this.houseFamily = houseFamily;
        this.construction_cost = construction_cost;
        this.size = size;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.houseConstructionStatus = houseConstructionStatus;
        this.house_style = house_style;
        this.houseProperty = houseProperty;
        this.houseAddress = houseAddress;
    }

    public House(double construction_cost, Family houseFamily, int size, int bedrooms, double bathrooms, ConstructionStatus houseConstructionStatus, HouseStyle house_style, Property houseProperty, Address houseAddress) {
        this.construction_cost = construction_cost;
        this.houseFamily = houseFamily;
        this.size = size;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.houseConstructionStatus = houseConstructionStatus;
        this.house_style = house_style;
        this.houseProperty = houseProperty;
        this.houseAddress = houseAddress;
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

    public Family getHouseFamily() {
        return houseFamily;
    }

    public void setHouseFamily(Family family) {
        this.houseFamily = family;
    }

    public House() { }


    public Address getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(Address address) {
        this.houseAddress = address;
    }

    public ConstructionStatus getHouseConstructionStatus() {
        return houseConstructionStatus;
    }

    public void setHouseConstructionStatus(ConstructionStatus construction_status) {
        this.houseConstructionStatus = construction_status;
    }

    public HouseStyle getHouse_style() {
        return house_style;
    }

    public void setHouse_style(HouseStyle house_style) {
        this.house_style = house_style;
    }

    public Property getHouseProperty() {
        return houseProperty;
    }

    public void setHouseProperty(Property property) {
        this.houseProperty = property;
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

    @Override
    public String getValueByPropertyName(String property) {
        switch(property) {
            case "id":
                return "" + getId();
            case "houseAddress":
                return "" + getHouseAddress();
            case "houseProperty":
                return "" + getHouseProperty();
            case "houseFamily":
                return "" + getHouseFamily();
            case "construction_cost":
                return "" + getConstruction_cost();
            case "size":
                return "" + getSize();
            case "bedrooms":
                return "" + getBedrooms();
            case "bathrooms":
                return "" + getBathrooms();
            case "houseConstructionStatus":
                return "" + getHouseConstructionStatus();
            case "house_style":
                return "" + getHouse_style();
            case "this":
                return toString();
            default:
                return "invalid property specifier";
        }
    }

    @Override
    public String toString() {
        return "House #:" + id + Functions.NEWLINE_TAB +
                "Property #: " + getHouseProperty().getId() + Functions.NEWLINE_TAB +
                "Style: " + getHouse_style().getStyle() + Functions.NEWLINE_TAB +
                size + " sq ft., " +
                bedrooms + "  bedrooms, " +
                bathrooms + "  bathrooms" + Functions.NEWLINE_TAB +
                houseAddress.toString();
    }
}
