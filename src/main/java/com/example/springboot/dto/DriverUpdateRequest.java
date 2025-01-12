package com.example.springboot.dto;

import java.util.Map;

public class DriverUpdateRequest {
    private String user;
    private Map<String, String> driverData;
    private String driverId;

    // Getters and Setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Map<String, String> getDriverData() {
        return driverData;
    }

    public void setDriverData(Map<String, String> driverData) {
        this.driverData = driverData;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
}
