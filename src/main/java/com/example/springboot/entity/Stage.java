package com.example.springboot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stage")
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String stage_role;

    @Column(nullable = false)
    private Integer status;

    public Stage() {
    }

    public Stage(String name, Integer status, String stage_role) {
        this.name = name;
        this.stage_role = stage_role;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStage_role() {
        return stage_role;
    }

    public void setStage_role(String stage_role) {
        this.stage_role = stage_role;
    }
}
