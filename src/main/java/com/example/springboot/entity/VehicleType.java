package com.example.springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_type")
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vehicle_type", nullable = false, length = 200)
    private String vehicleType;

    @Column(name = "km_allowance", nullable = false, length = 200)
    private String kmAllowance;

    @Column(name = "month_allowance", nullable = false)
    private String monthAllowance; // Using String for text

    @Column(name = "status", nullable = false)
    private int status = 1; // Default value

    // Default constructor
    public VehicleType() {
    }

    // Parameterized constructor
    public VehicleType(String vehicleType, String kmAllowance, String monthAllowance, int status) {
        this.vehicleType = vehicleType;
        this.kmAllowance = kmAllowance;
        this.monthAllowance = monthAllowance;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getKmAllowance() {
        return kmAllowance;
    }

    public void setKmAllowance(String kmAllowance) {
        this.kmAllowance = kmAllowance;
    }

    public String getMonthAllowance() {
        return monthAllowance;
    }

    public void setMonthAllowance(String monthAllowance) {
        this.monthAllowance = monthAllowance;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
