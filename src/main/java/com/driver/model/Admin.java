package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "admin")
public class Admin
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer adminId;

    private String password;

    private String username;

    public Admin() {
    }

    public Admin(Integer adminId, String password) {
        this.adminId = adminId;
        this.password = password;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}