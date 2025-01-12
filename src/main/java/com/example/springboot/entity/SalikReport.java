package com.example.springboot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "salik_report")
public class SalikReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vehicle_reg", nullable = false, length = 200)
    private String vehicleReg;

    @Column(name = "location", nullable = false, length = 200)
    private String location;

    @Column(name = "transaction_time", nullable = false)
    private String transactionTime; // Using String for text

    @Column(name = "amount", nullable = false)
    private String amount;

    @Column(name = "month_year", nullable = false)
    private String monthYear;

    public SalikReport() {}

    public SalikReport(String vehicleReg, String location, String transactionTime, String amount, String monthYear) {
        this.vehicleReg = vehicleReg;
        this.location = location;
        this.transactionTime = transactionTime;
        this.amount = amount;
        this.monthYear = monthYear;
    }

    public String getVehicleReg() {
        return vehicleReg;
    }

    public void setVehicleReg(String vehicleReg) {
        this.vehicleReg = vehicleReg;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMonthYear() {
        return monthYear;
    }

    public void setMonthYear(String monthYear) {
        this.monthYear = monthYear;
    }
}
