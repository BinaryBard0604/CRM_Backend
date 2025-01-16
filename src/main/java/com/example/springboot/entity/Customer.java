package com.example.springboot.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer customer_rank;

    @Column(nullable = false)
    private Integer supplier_rank;

    @Column(nullable = false)
    private Integer salesperson;

    @Column(nullable = false)
    private Integer status;

    public Customer() {
    }

    public Customer(String name, String email, String phone, String type, Integer customer_rank, Integer supplier_rank, Integer salesperson, Integer status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.type = type;
        this.customer_rank = customer_rank;
        this.supplier_rank = supplier_rank;
        this.salesperson = salesperson;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCustomer_rank() {
        return customer_rank;
    }

    public void setCustomer_rank(Integer customer_rank) {
        this.customer_rank = customer_rank;
    }

    public Integer getSupplier_rank() {
        return supplier_rank;
    }

    public void setSupplier_rank(Integer supplier_rank) {
        this.supplier_rank = supplier_rank;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(Integer salesperson) {
        this.salesperson = salesperson;
    }
}
