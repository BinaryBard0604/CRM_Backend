package com.example.springboot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "telephone_data")
public class TelephoneData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "phone", nullable = false, length = 200)
    private String phone;

    @Column(name = "amount", nullable = false, length = 200)
    private String amount;

    @Column(name = "month", nullable = false)
    private String month; // Using String for text

    public TelephoneData() {}

    public TelephoneData(String phone, String amount, String month) {
        this.phone = phone;
        this.amount = amount;
        this.month = month;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
