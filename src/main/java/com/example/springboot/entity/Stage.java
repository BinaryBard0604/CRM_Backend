package com.example.springboot.Entity;

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
    private Integer sequence;

    @Column(nullable = false)
    private Boolean fold;

    @Column(nullable = false)
    private Integer status;

    public Stage() {
    }

    public Stage(String name, Integer sequence, Boolean fold, Integer status) {
        this.name = name;
        this.sequence = sequence;
        this.fold = fold;
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

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Boolean getFold() {
        return fold;
    }

    public void setFold(Boolean fold) {
        this.fold = fold;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
