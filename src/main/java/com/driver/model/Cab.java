package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "cabs")
public class Cab
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cabId;
    private Boolean isAvailable;
    private Integer ratePerKm;


    public Cab(Boolean isAvailable, Integer ratePerKm) {
        this.isAvailable = isAvailable;
        this.ratePerKm = ratePerKm;
    }

    public Cab() {
    }

    public Integer getCabId() {
        return cabId;
    }

    public void setCabId(Integer cabId) {
        this.cabId = cabId;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public Integer getRatePerKm() {
        return ratePerKm;
    }

    public void setRatePerKm(Integer ratePerKm) {
        this.ratePerKm = ratePerKm;
    }
}