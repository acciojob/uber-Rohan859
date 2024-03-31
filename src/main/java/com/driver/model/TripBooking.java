package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "tripbooking")
public class TripBooking
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tripBookingId;
    private String fromLocation;
    private String toLocation;
    private Integer distanceInKm;

    @Enumerated(value = EnumType.STRING)
    private TripStatus tripStatus;

    private Integer bill;

    @JoinColumn
    @OneToOne
    Driver driver;

    @JoinColumn
    @ManyToOne
    private Customer customer;

    public TripBooking() {
    }

    public TripBooking(String fromLocation, String toLocation, Integer distanceInKm, TripStatus tripStatus, Driver driver, Customer customer) {
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
        this.distanceInKm = distanceInKm;
        this.tripStatus = tripStatus;
        this.driver = driver;
        this.customer = customer;
    }

    public Integer getTripBookingId() {
        return tripBookingId;
    }

    public void setTripBookingId(Integer tripBookingId) {
        this.tripBookingId = tripBookingId;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public Integer getDistanceInKm() {
        return distanceInKm;
    }

    public void setDistanceInKm(Integer distanceInKm) {
        this.distanceInKm = distanceInKm;
    }

    public TripStatus getStatus() {
        return tripStatus;
    }

    public void setStatus(TripStatus tripStatus) {
        this.tripStatus = tripStatus;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }


}