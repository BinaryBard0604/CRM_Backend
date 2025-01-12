package com.example.springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wp_country")
public class WpCountry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "country_name", nullable = false, length = 50)
    private String countryName;

    @Column(name = "country_code", nullable = false, length = 50)
    private String countryCode;

    @Column(name = "country_zone", nullable = false, length = 50)
    private String countryZone;

    @Column(name = "country_time", nullable = false, length = 50)
    private String countryTime;

    // Default constructor
    public WpCountry() {
    }

    // Parameterized constructor
    public WpCountry(String countryName, String countryCode, String countryZone, String countryTime) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.countryZone = countryZone;
        this.countryTime = countryTime;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryZone() {
        return countryZone;
    }

    public void setCountryZone(String countryZone) {
        this.countryZone = countryZone;
    }

    public String getCountryTime() {
        return countryTime;
    }

    public void setCountryTime(String countryTime) {
        this.countryTime = countryTime;
    }
}
