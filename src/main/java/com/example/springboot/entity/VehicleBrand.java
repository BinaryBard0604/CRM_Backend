package com.example.springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_brand")
public class VehicleBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "veh_type", nullable = false)
    private int vehType;

    @Column(name = "brand", nullable = false, length = 200)
    private String brand;

    @Column(name = "status", nullable = false)
    private int status = 1; // Default value

    // Default constructor
    public VehicleBrand() {
    }

    // Parameterized constructor
    public VehicleBrand(int vehType, String brand, int status) {
        this.vehType = vehType;
        this.brand = brand;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehType() {
        return vehType;
    }

    public void setVehType(int vehType) {
        this.vehType = vehType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
