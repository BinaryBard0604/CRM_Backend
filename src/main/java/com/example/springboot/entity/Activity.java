package com.example.springboot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long opportunity_id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private LocalDate deadline;

    private String summary;

    @Column(nullable = false)
    private Long assign_id;

    private String notes;

    @Column(nullable = false)
    private Integer activity_status;

    @Column(nullable = false)
    private Integer status;

    public Activity() {
    }

    public Activity(Long opportunity_id, String type, LocalDate deadline, String summary, Long assign_id, String notes, Integer activity_status,
                    Integer status) {
        this.opportunity_id = opportunity_id;
        this.type = type;
        this.deadline = deadline;
        this.summary = summary;
        this.assign_id = assign_id;
        this.notes = notes;
        this.activity_status = activity_status;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOpportunity_id() {
        return opportunity_id;
    }

    public void setOpportunity_id(Long opportunity_id) {
        this.opportunity_id = opportunity_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getAssign_id() {
        return assign_id;
    }

    public void setAssign_id(Long assign_id) {
        this.assign_id = assign_id;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getActivity_status() {
        return activity_status;
    }

    public void setActivity_status(Integer activity_status) {
        this.activity_status = activity_status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
