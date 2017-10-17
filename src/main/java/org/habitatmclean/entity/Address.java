package org.habitatmclean.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="address")                        // serializable must be included if this object needs
public class Address implements Serializable{ // to be sent over a network and reconstructed elsewhere
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //TODO how are we tracking whether this address belongs to a family/house/property? If it's on the other side, why does actor have one?
    private Long address_id;

    private String street;
    private String number;
    private String apartment_no;
    private String city;
    private String state;
    private String zipcode;

    public Address() { }

    public Address(Long address_id, String street, String number, String apartment_no, String city, String state, String zipcode) {
        this.address_id = address_id;
        this.street = street;
        this.number = number;
        this.apartment_no = apartment_no;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
    }

    @Column(name="address_id")
    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    @Column(name="street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name="number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name="apartment_no")
    public String getApartment_no() {
        return apartment_no;
    }

    public void setApartment_no(String apartment_no) {
        this.apartment_no = apartment_no;
    }

    @Column(name="city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name="state")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Column(name="zipcode")
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

}
