package com.example.springboot.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "km_details")
public class KmDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String vehicleReg;

    @Column(nullable = false, length = 200)
    private String km;

    @Column(nullable = false)
    private int updatedBy;

    @Column(nullable = false)
    private LocalDate updatedDate;

    public KmDetails() {}

    public KmDetails(String vehicleReg, String km, int updatedBy, LocalDate updatedDate) {
        this.vehicleReg = vehicleReg;
        this.km = km;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public void setVehicleReg(String vehicleReg) {
        this.vehicleReg = vehicleReg;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
}

