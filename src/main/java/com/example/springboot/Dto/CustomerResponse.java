package com.example.springboot.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import org.springframework.web.multipart.MultipartFile;

public class CustomerResponse {
    private Long id;

    private String name;

    private String email;

    private MultipartFile file;

    private String phone;

    private String mobile;

    private String type;

    private Integer customer_rank;

    private Integer supplier_rank;

    private String reference;

    private Integer status;

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
