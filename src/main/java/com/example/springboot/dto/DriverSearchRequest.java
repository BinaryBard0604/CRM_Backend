package com.example.springboot.dto;

import java.util.Map;

public class DriverSearchRequest {
    private String branch;
    private String user_role;
    private String status;
    private String type;
    private Map<String, String> searchdata;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getSearchdata() {
        return searchdata;
    }

    public void setSearchdata(Map<String, String> searchdata) {
        this.searchdata = searchdata;
    }

    // Getters and Setters
}
