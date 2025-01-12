package com.example.springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_name")
public class VehicleName {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "brand_id", nullable = false)
    private int brandId;

    @Column(name = "vehicle_name", nullable = false, length = 200)
    private String vehicleName;

    @Column(name = "status", nullable = false)
    private int status = 1; // Default value

    // Default constructor
    public VehicleName() {
    }

    // Parameterized constructor
    public VehicleName(int brandId, String vehicleName, int status) {
        this.brandId = brandId;
        this.vehicleName = vehicleName;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
