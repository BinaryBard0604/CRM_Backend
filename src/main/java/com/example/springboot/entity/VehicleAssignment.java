package com.example.springboot.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "vehicle_assignment")
public class VehicleAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int vehicleId;

    @Column(nullable = false)
    private int driverId;

    @Column(nullable = false)
    private int branchId;

    private LocalDate fromDate;

    @Column(nullable = false)
    private LocalTime fromTime;

    private LocalDate toDate;

    @Column(nullable = false)
    private LocalTime toTime;

    @Column(nullable = false)
    private int assignedBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false, columnDefinition = "int default 1")
    private int status;

    @Column(nullable = false)
    private int updatedBy;

    private LocalDate updatedDate;

    private LocalDate closedDate;

    @Column(nullable = false, length = 200)
    private String closedBy;

    // Default Constructor
    public VehicleAssignment() {
    }

    // Parameterized Constructor
    public VehicleAssignment(int id, int vehicleId, int driverId, int branchId, LocalDate fromDate, LocalTime fromTime,
                             LocalDate toDate, LocalTime toTime, int assignedBy, LocalDateTime createdAt, String notes,
                             int status, int updatedBy, LocalDate updatedDate, LocalDate closedDate, String closedBy) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.branchId = branchId;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;
        this.assignedBy = assignedBy;
        this.createdAt = createdAt;
        this.notes = notes;
        this.status = status;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.closedDate = closedDate;
        this.closedBy = closedBy;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
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

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalTime getFromTime() {
        return fromTime;
    }

    public void setFromTime(LocalTime fromTime) {
        this.fromTime = fromTime;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalTime getToTime() {
        return toTime;
    }

    public void setToTime(LocalTime toTime) {
        this.toTime = toTime;
    }

    public int getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(int assignedBy) {
        this.assignedBy = assignedBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public LocalDate getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(LocalDate closedDate) {
        this.closedDate = closedDate;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }
}
