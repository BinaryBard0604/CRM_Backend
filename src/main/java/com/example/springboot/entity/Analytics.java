package com.example.springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "analytics")
public class Analytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private Integer totalDriver;

    @Column(nullable = false)
    private Integer totalVehicle;

    @Column(nullable = false)
    private Integer branch;

    @Column(nullable = false, length = 200)
    private String monthYear;

    public Analytics() {}

    public Analytics(Integer totalDriver, Integer totalVehicle, Integer branch, String monthYear) {
        this.totalDriver = totalDriver;
        this.totalVehicle = totalVehicle;
        this.branch = branch;
        this.monthYear = monthYear;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getTotalDriver() {
        return totalDriver;
    }

    public void setTotalDriver(Integer totalDriver) {
        this.totalDriver = totalDriver;
    }

    public Integer getTotalVehicle() {
        return totalVehicle;
    }

    public void setTotalVehicle(Integer totalVehicle) {
        this.totalVehicle = totalVehicle;
    }

    public Integer getBranch() {
        return branch;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }
}
