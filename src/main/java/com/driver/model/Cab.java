package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "cabs")
public class Cab
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Boolean cabUnavlbl;
    private Integer perKmRate;

    @OneToOne(mappedBy = "cab",cascade = CascadeType.ALL)
    Driver driver;


    public Cab() {
    }

    public Cab(Boolean cabUnavlbl, Integer perKmRate) {
        this.cabUnavlbl = cabUnavlbl;
        this.perKmRate = perKmRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return cabUnavlbl;
    }

    public void setAvailable(Boolean cabUnavlbl) {
        this.cabUnavlbl = cabUnavlbl;
    }

    public Integer getPerKmRate() {
        return perKmRate;
    }

    public void setPerKmRate(Integer perKmRate) {
        this.perKmRate = perKmRate;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }
}