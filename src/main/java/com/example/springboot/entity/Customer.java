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

    @Column(columnDefinition = "TEXT")
    private String attached_email;

    @Column(nullable = false)
    private String phone;

    private String mobile;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer customer_rank;

    @Column(nullable = false)
    private Integer supplier_rank;

    private String reference;

    @Column(nullable = false)
    private Integer status;

    public Customer() {
    }

    public Customer(String name, String email, String phone, String mobile, String type, Integer customer_rank, String attached_email, Integer supplier_rank, String reference, Integer status) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.type = type;
        this.customer_rank = customer_rank;
        this.supplier_rank = supplier_rank;
        this.reference = reference;
        this.attached_email = attached_email;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAttached_email() {
        return attached_email;
    }

    public void setAttached_email(String attached_email) {
        this.attached_email = attached_email;
    }
}
