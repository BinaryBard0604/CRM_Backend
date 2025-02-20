package com.example.springboot.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDate;

@Entity
@Table(name = "opportunity")
public class Opportunity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer expected_revenue;

    @Column(nullable = false)
    private Integer probability;

    @Column(nullable = true)
    private String contact;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String phone;

    @Column(name = "salesperson_id", nullable = true)
    private Long salesperson_id;

    private LocalDate expected_closing;

    private LocalDate created_date;

    @Column(nullable = false)
    private Long team_id;

    @JoinColumn(name = "stage_id")
    private Long stage_id;

    private String tags;

    private Integer rating;

    private Integer status;

    public Opportunity() {
    }

    public Opportunity(String name, Integer expected_revenue, Integer probability, String contact, String email, String phone, Long salesperson_id, LocalDate expected_closing
    , LocalDate created_date, String tags, Long stage_id, Integer rating, Long team_id, Integer status) {
        this.name = name;
        this.expected_revenue = expected_revenue;
        this.probability = probability;
        this.contact = contact;
        this.email = email;
        this.phone = phone;
        this.salesperson_id = salesperson_id;
        this.expected_closing = expected_closing;
        this.created_date = created_date;
        this.tags = tags;
        this.stage_id = stage_id;
        this.rating = rating;
        this.team_id = team_id;
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

    public Integer getExpected_revenue() {
        return expected_revenue;
    }

    public void setExpected_revenue(Integer expected_revenue) {
        this.expected_revenue = expected_revenue;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public LocalDate getExpected_closing() {
        return expected_closing;
    }

    public void setExpected_closing(LocalDate expected_closing) {
        this.expected_closing = expected_closing;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSalesperson_id() {
        return salesperson_id;
    }

    public void setSalesperson_id(Long salesperson_id) {
        this.salesperson_id = salesperson_id;
    }

    public Long getStage_id() {
        return stage_id;
    }

    public void setStage_id(Long stage_id) {
        this.stage_id = stage_id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LocalDate getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDate created_date) {
        this.created_date = created_date;
    }

    public Long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Long team_id) {
        this.team_id = team_id;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
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
}
