package com.example.springboot.Entity;

import com.example.springboot.Entity.Role;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "salesperson")
public class Salesperson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String phone;

    private String mobile;

    private LocalDateTime latest_login;

    private String company;

    @Column(nullable = false)
    private Integer status;

    public Salesperson() {
    }

    public Salesperson(String name, String email, String phone, String mobile, LocalDateTime latest_login, String company, Integer status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.latest_login = latest_login;
        this.company = company;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDateTime getLatest_login() {
        return latest_login;
    }

    public void setLatest_login(LocalDateTime latest_login) {
        this.latest_login = latest_login;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
