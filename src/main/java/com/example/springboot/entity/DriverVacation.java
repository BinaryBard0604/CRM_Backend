package com.example.springboot.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "driver_vacation")
public class DriverVacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int driverId;

    @Column(nullable = false, length = 200)
    private String leaveType;

    private LocalDate leaveFrom;

    private LocalDate leaveTo;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String leaveForm;

    @Column(nullable = false, columnDefinition = "int default 0")
    private int appovalStatus;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String settlementForm;

    @Column(nullable = false, columnDefinition = "int default 1")
    private int status;

    @Column(nullable = false, length = 200, columnDefinition = "varchar(200) default '0'")
    private String rejoinDate;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String rejoinNotes;

    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String notes;

    @Column(nullable = false)
    private int updatedBy;

    @Column(nullable = false)
    private LocalDate updatedDate;

    public DriverVacation() {}

    public DriverVacation(int driverId, String leaveType, LocalDate leaveFrom, LocalDate leaveTo, String leaveForm, int appovalStatus, String settlementForm, int status, String rejoinDate, String rejoinNotes, String notes, int updatedBy, LocalDate updatedDate) {
        this.driverId = driverId;
        this.leaveType = leaveType;
        this.leaveFrom = leaveFrom;
        this.leaveTo = leaveTo;
        this.leaveForm = leaveForm;
        this.appovalStatus = appovalStatus;
        this.settlementForm = settlementForm;
        this.status = status;
        this.rejoinDate = rejoinDate;
        this.rejoinNotes = rejoinNotes;
        this.notes = notes;
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

    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(LocalDate leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    public LocalDate getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(LocalDate leaveTo) {
        this.leaveTo = leaveTo;
    }

    public String getLeaveForm() {
        return leaveForm;
    }

    public void setLeaveForm(String leaveForm) {
        this.leaveForm = leaveForm;
    }

    public int getAppovalStatus() {
        return appovalStatus;
    }

    public void setAppovalStatus(int appovalStatus) {
        this.appovalStatus = appovalStatus;
    }

    public String getSettlementForm() {
        return settlementForm;
    }

    public void setSettlementForm(String settlementForm) {
        this.settlementForm = settlementForm;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRejoinDate() {
        return rejoinDate;
    }

    public void setRejoinDate(String rejoinDate) {
        this.rejoinDate = rejoinDate;
    }

    public String getRejoinNotes() {
        return rejoinNotes;
    }

    public void setRejoinNotes(String rejoinNotes) {
        this.rejoinNotes = rejoinNotes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

