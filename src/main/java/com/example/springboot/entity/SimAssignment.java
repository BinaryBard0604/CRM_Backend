package com.example.springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sim_assignment")
public class SimAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int driverId;

    @Column(nullable = false, length = 200)
    private String mobile;

    @Column(nullable = false, length = 200)
    private String updatedDate;

    @Column(nullable = false)
    private int updatedBy;

    @Column(nullable = false, columnDefinition = "int default 1")
    private int status;

    public SimAssignment() {}

    public SimAssignment(int driverId, String mobile, String updatedDate, int updatedBy, int status) {
        this.driverId = driverId;
        this.mobile = mobile;
        this.updatedDate = updatedDate;
        this.updatedBy = updatedBy;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
