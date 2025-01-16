package com.example.springboot.Dto;

import com.example.springboot.Entity.Opportunity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class StageAllResponse {

    private Long id;

    private String name;

    private Integer status;

    @JsonProperty("opportunities")
    private List<OpportunityResponse> opportunities;

    public StageAllResponse() {}

    public StageAllResponse(Long id, String name, Integer status, List<OpportunityResponse> opportunities) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.opportunities = opportunities;
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

    public List<OpportunityResponse> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<OpportunityResponse> opportunities) {
        this.opportunities = opportunities;
    }
}
