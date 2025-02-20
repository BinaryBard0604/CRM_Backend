package com.example.springboot.Dto;

import com.example.springboot.entity.Customer;
import com.example.springboot.entity.Opportunity;
import com.example.springboot.entity.Stage;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

public class OpportunityResponse {

    private Long id;

    private String name;

    private Integer expected_revenue;

    private Integer probability;

    @JsonProperty("contact")
    private Customer contact;

    @JsonProperty("salesperson")
    private Customer salesperson;

    @JsonProperty("stage")
    private Stage stage;

    private LocalDate expected_closing;

    private String tags;

    private Integer status;

    public OpportunityResponse() {
    }

    public OpportunityResponse(String name, Integer expected_revenue, Integer probability, Customer contact, Customer salesperson, LocalDate expected_closing
            , String tags, Stage stage, Integer status) {
        this.name = name;
        this.expected_revenue = expected_revenue;
        this.probability = probability;
        this.contact = contact;
        this.salesperson = salesperson;
        this.expected_closing = expected_closing;
        this.tags = tags;
        this.stage = stage;
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

    public Customer getContact() {
        return contact;
    }

    public void setContact(Customer contact) {
        this.contact = contact;
    }

    public Customer getSalesperson() {
        return salesperson;
    }

    public void setSalesperson(Customer salesperson) {
        this.salesperson = salesperson;
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

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
