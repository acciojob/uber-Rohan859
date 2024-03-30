package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "drivers")
public class Driver
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer driverId;

    private String mobile;
    private String password;


    @OneToOne
    private Cab cab;

    public Driver(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public Driver() {
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }
}