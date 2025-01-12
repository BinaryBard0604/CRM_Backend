package com.example.springboot.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "driver_branch_assignment")
public class DriverBranchAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int driverId;

    @Column(nullable = false)
    private int branchId;

    @Column(nullable = false)
    private int updatedBy;

    @Column(nullable = false)
    private LocalDate updatedDate;

    @Column(nullable = false)
    private int status = 1; // Default value is 1

    public DriverBranchAssignment() {}

    public DriverBranchAssignment(int driverId, int branchId, int updatedBy, LocalDate updatedDate, int status) {
        this.driverId = driverId;
        this.branchId = branchId;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
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

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
