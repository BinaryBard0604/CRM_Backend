package com.example.springboot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "pod_uploads")
public class PodUploads {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer branch;

    @Column(nullable = false)
    private Integer manager;

    @Column(nullable = false)
    private LocalDate updatedOn;

    @Column(nullable = false)
    private LocalTime uploadingDate;

    @Column(nullable = false)
    private Integer status;

    public PodUploads() {
    }

    public PodUploads(Integer branch, Integer manager, LocalDate updatedOn, LocalTime uploadingDate, Integer status) {
        this.branch = branch;
        this.manager = manager;
        this.updatedOn = updatedOn;
        this.uploadingDate = uploadingDate;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBranch() {
        return branch;
    }

    public void setBranch(Integer branch) {
        this.branch = branch;
    }

    public Integer getManager() {
        return manager;
    }

    public void setManager(Integer manager) {
        this.manager = manager;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public LocalTime getUploadingDate() {
        return uploadingDate;
    }

    public void setUploadingDate(LocalTime uploadingDate) {
        this.uploadingDate = uploadingDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
