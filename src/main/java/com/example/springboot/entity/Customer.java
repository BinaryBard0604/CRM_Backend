package com.example.springboot.entity;

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

    private String file_name;

    @Lob // Use LOB to store large binary data
    private byte[] file_data;

    @Column(nullable = false)
    private String phone;

    private String mobile;

    @Column(nullable = false)
    private String type;

    private String reference;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false)
    private Long created_salespersonid;

    public Customer() {
    }

    public Customer(String name, String email, String phone, String mobile, String type, String file_name, byte[] file_data, String reference, Integer status, Long created_salespersonid) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.mobile = mobile;
        this.type = type;
        this.reference = reference;
        this.file_data = file_data;
        this.file_name = file_name;
        this.status = status;
        this.created_salespersonid = created_salespersonid;
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

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public byte[] getFile_data() {
        return file_data;
    }

    public void setFile_data(byte[] file_data) {
        this.file_data = file_data;
    }

    public Long getCreated_salespersonid() {
        return created_salespersonid;
    }

    public void setCreated_salespersonid(Long created_salespersonid) {
        this.created_salespersonid = created_salespersonid;
    }
}
