package com.example.springboot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "pod_files")
public class PodFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer podId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String podPath;

    @Column(nullable = false)
    private Integer status;

    public PodFiles() {
    }

    public PodFiles(Integer podId, String podPath, Integer status) {
        this.podId = podId;
        this.podPath = podPath;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Integer getPodId() {
        return podId;
    }

    public void setPodId(Integer podId) {
        this.podId = podId;
    }

    public String getPodPath() {
        return podPath;
    }

    public void setPodPath(String podPath) {
        this.podPath = podPath;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
