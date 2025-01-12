package com.example.springboot.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "driver_final_settlement")
public class DriverFinalSettlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int driverId;

    @Column(nullable = false)
    private LocalDate resignDate;

    @Column(nullable = false)
    private LocalDate cancelDate;

    @Column(nullable = false, length = 200)
    private String typeOfLeaving;

    @Lob
    @Column(nullable = false)
    private String settleForm;

    @Lob
    @Column(nullable = false)
    private String cancelForm;

    @Lob
    @Column(nullable = false)
    private String resignForm;

    @Lob
    @Column(nullable = false)
    private String notes;

    @Lob
    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private int status;

    @Column(nullable = false)
    private int updatedBy;

    @Column(nullable = false)
    private LocalDate updatedDate;

    public DriverFinalSettlement() {}

    public DriverFinalSettlement(int driverId, LocalDate resignDate, LocalDate cancelDate, String typeOfLeaving, String settleForm, String cancelForm, String resignForm, String notes, String reason, int status, int updatedBy, LocalDate updatedDate) {
        this.driverId = driverId;
        this.resignDate = resignDate;
        this.cancelDate = cancelDate;
        this.typeOfLeaving = typeOfLeaving;
        this.settleForm = settleForm;
        this.cancelForm = cancelForm;
        this.resignForm = resignForm;
        this.notes = notes;
        this.reason = reason;
        this.status = status;
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

    public LocalDate getResignDate() {
        return resignDate;
    }

    public void setResignDate(LocalDate resignDate) {
        this.resignDate = resignDate;
    }

    public LocalDate getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDate cancelDate) {
        this.cancelDate = cancelDate;
    }

    public String getTypeOfLeaving() {
        return typeOfLeaving;
    }

    public void setTypeOfLeaving(String typeOfLeaving) {
        this.typeOfLeaving = typeOfLeaving;
    }

    public String getSettleForm() {
        return settleForm;
    }

    public void setSettleForm(String settleForm) {
        this.settleForm = settleForm;
    }

    public String getCancelForm() {
        return cancelForm;
    }

    public void setCancelForm(String cancelForm) {
        this.cancelForm = cancelForm;
    }

    public String getResignForm() {
        return resignForm;
    }

    public void setResignForm(String resignForm) {
        this.resignForm = resignForm;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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
