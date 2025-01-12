package com.example.springboot.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "driver_visa_application")
public class DriverVisaApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int driverId;

    @Column(nullable = false, length = 200)
    private String appliedFor;

    @Lob
    @Column(nullable = false)
    private String notes;

    @Column(nullable = false)
    private int approvalStatus;

    @Column(nullable = false)
    private int updatedBy;

    @Column(nullable = false)
    private LocalDate updatedDate;

    @Column(nullable = false, columnDefinition = "int default 1")
    private int status;

    public DriverVisaApplication() {}

    public DriverVisaApplication(int driverId, String appliedFor, String notes, int approvalStatus, int updatedBy, LocalDate updatedDate, int status) {
        this.driverId = driverId;
        this.appliedFor = appliedFor;
        this.notes = notes;
        this.approvalStatus = approvalStatus;
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

    public String getAppliedFor() {
        return appliedFor;
    }

    public void setAppliedFor(String appliedFor) {
        this.appliedFor = appliedFor;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(int approvalStatus) {
        this.approvalStatus = approvalStatus;
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
