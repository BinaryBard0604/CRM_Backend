package com.example.springboot.dto;

import com.example.springboot.entity.DriverDetails;

public class DriverDetailsDTO {
    private DriverDetails driverDetails;
    private String branch;

    public DriverDetailsDTO(DriverDetails driverDetails, String branch) {
        this.driverDetails = driverDetails;
        this.branch = branch;
    }


    public DriverDetails getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(DriverDetails driverDetails) {
        this.driverDetails = driverDetails;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
