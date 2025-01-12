package com.example.springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_branch")
public class CustomerBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String branch;

    @Column(nullable = false)
    private int status = 1; // Default value is 1

    public CustomerBranch() {}

    public CustomerBranch(String branch, int status) {
        this.branch = branch;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
