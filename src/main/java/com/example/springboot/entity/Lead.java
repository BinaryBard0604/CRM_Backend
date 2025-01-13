package com.example.springboot.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "`lead`") // Escaped the table name
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Customer partner;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Float probability;

    @Column(nullable = false)
    private Double expectedRevenue;

    @Column(nullable = false)
    private Integer status;

    public Lead() {
    }

    public Lead(String name, Customer partner, Stage stage, User user, Float probability, Double expectedRevenue, Integer status) {
        this.name = name;
        this.partner = partner;
        this.stage = stage;
        this.user = user;
        this.probability = probability;
        this.expectedRevenue = expectedRevenue;
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

    public Customer getPartner() {
        return partner;
    }

    public void setPartner(Customer partner) {
        this.partner = partner;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Float getProbability() {
        return probability;
    }

    public void setProbability(Float probability) {
        this.probability = probability;
    }

    public Double getExpectedRevenue() {
        return expectedRevenue;
    }

    public void setExpectedRevenue(Double expectedRevenue) {
        this.expectedRevenue = expectedRevenue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
