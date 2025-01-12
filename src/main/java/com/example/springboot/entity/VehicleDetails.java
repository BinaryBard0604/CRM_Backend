package com.example.springboot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "vehicle_details")
public class VehicleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vehicle_type", nullable = false)
    private int vehicleType;

    @Column(name = "make", nullable = false, length = 200)
    private String make;

    @Column(name = "plate_number", nullable = false, length = 200)
    private String plateNumber;

    @Column(name = "reg_number", nullable = false, length = 200)
    private String regNumber;

    @Column(name = "reg_emirate", nullable = false, length = 200)
    private String regEmirate;

    @Column(name = "vehicle_name", nullable = false, length = 200)
    private String vehicleName;

    @Column(name = "model", nullable = false, length = 200)
    private String model;

    @Column(name = "initial_mileage", nullable = false, length = 200)
    private String initialMileage;

    @Column(name = "mileage", nullable = false, length = 200)
    private String mileage;

    @Column(name = "purchase_type", nullable = false, length = 200)
    private String purchaseType;

    @Column(name = "purchase_company", nullable = false, length = 200)
    private String purchaseCompany;

    @Column(name = "purchase_from")
    private LocalDate purchaseFrom;

    @Column(name = "purchase_to")
    private LocalDate purchaseTo;

    @Column(name = "notes", nullable = false, columnDefinition = "TEXT")
    private String notes;

    @Column(name = "vehicle_gear", nullable = false, length = 200)
    private String vehicleGear;

    @Column(name = "plan", length = 200)
    private String plan;

    @Column(name = "status", nullable = false)
    private int status = 1; // Default value

    @Column(name = "updated_by", nullable = false)
    private int updatedBy;

    @Column(name = "updated_date", nullable = false, length = 200)
    private String updatedDate;

    @Column(name = "restored_by")
    private Integer restoredBy;

    @Column(name = "restored_date")
    private Date restoredDate;

    // Default constructor
    public VehicleDetails() {
    }

    // Parameterized constructor
    public VehicleDetails(int vehicleType, String make, String plateNumber, String regNumber,
                          String regEmirate, String vehicleName, String model, String initialMileage,
                          String mileage, String purchaseType, String purchaseCompany, LocalDate purchaseFrom,
                          LocalDate purchaseTo, String notes, String vehicleGear, String plan, int updatedBy,
                          String updatedDate, Integer restoredBy, Date restoredDate) {
        this.vehicleType = vehicleType;
        this.make = make;
        this.plateNumber = plateNumber;
        this.regNumber = regNumber;
        this.regEmirate = regEmirate;
        this.vehicleName = vehicleName;
        this.model = model;
        this.initialMileage = initialMileage;
        this.mileage = mileage;
        this.purchaseType = purchaseType;
        this.purchaseCompany = purchaseCompany;
        this.purchaseFrom = purchaseFrom;
        this.purchaseTo = purchaseTo;
        this.notes = notes;
        this.vehicleGear = vehicleGear;
        this.plan = plan;
        this.status = status;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.restoredBy = restoredBy;
        this.restoredDate = restoredDate;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(int vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegEmirate() {
        return regEmirate;
    }

    public void setRegEmirate(String regEmirate) {
        this.regEmirate = regEmirate;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getInitialMileage() {
        return initialMileage;
    }

    public void setInitialMileage(String initialMileage) {
        this.initialMileage = initialMileage;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    public String getPurchaseCompany() {
        return purchaseCompany;
    }

    public void setPurchaseCompany(String purchaseCompany) {
        this.purchaseCompany = purchaseCompany;
    }

    public LocalDate getPurchaseFrom() {
        return purchaseFrom;
    }

    public void setPurchaseFrom(LocalDate purchaseFrom) {
        this.purchaseFrom = purchaseFrom;
    }

    public LocalDate getPurchaseTo() {
        return purchaseTo;
    }

    public void setPurchaseTo(LocalDate purchaseTo) {
        this.purchaseTo = purchaseTo;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getVehicleGear() {
        return vehicleGear;
    }

    public void setVehicleGear(String vehicleGear) {
        this.vehicleGear = vehicleGear;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
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

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Integer getRestoredBy() {
        return restoredBy;
    }

    public void setRestoredBy(Integer restoredBy) {
        this.restoredBy = restoredBy;
    }

    public Date getRestoredDate() {
        return restoredDate;
    }

    public void setRestoredDate(Date restoredDate) {
        this.restoredDate = restoredDate;
    }
}
