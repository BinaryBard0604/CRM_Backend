package com.example.springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "fuel_data_2022")
public class FuelData2022 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Vendor", nullable = false, length = 200)
    private String vendor;

    @Column(name = "VehiclePlateNumber", nullable = false, length = 200)
    private String vehiclePlateNumber;

    @Column(name = "TransactionDate", nullable = false, length = 200)
    private String transactionDate;

    @Column(name = "UnitPrice", nullable = false)
    private float unitPrice;

    @Column(name = "Volume", nullable = false)
    private float volume;

    @Column(name = "ActualAmount", nullable = false)
    private float actualAmount;

    @Column(name = "ProductName", nullable = false, length = 200)
    private String productName;

    @Column(name = "VatRate", nullable = false)
    private float vatRate;

    @Column(name = "VatAmount", nullable = false)
    private float vatAmount;

    @Column(name = "TotalAmount", nullable = false)
    private float totalAmount;

    @Column(name = "month", nullable = false, length = 200)
    private String month;

    public FuelData2022() {}

    public FuelData2022(String vendor, String vehiclePlateNumber, String transactionDate, float unitPrice, float volume,
                        float actualAmount, String productName, float varRate, float varAmount, float totalAmount, String month) {
        this.vendor = vendor;
        this.vehiclePlateNumber = vehiclePlateNumber;
        this.transactionDate = transactionDate;
        this.unitPrice = unitPrice;
        this.volume = volume;
        this.actualAmount = actualAmount;
        this.productName = productName;
        this.vatRate = varRate;
        this.vatAmount = varAmount;
        this.totalAmount = totalAmount;
        this.month = month;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVehiclePlateNumber() {
        return vehiclePlateNumber;
    }

    public void setVehiclePlateNumber(String vehiclePlateNumber) {
        this.vehiclePlateNumber = vehiclePlateNumber;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public float getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(float actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getVatRate() {
        return vatRate;
    }

    public void setVatRate(float vatRate) {
        this.vatRate = vatRate;
    }

    public float getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(float vatAmount) {
        this.vatAmount = vatAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
