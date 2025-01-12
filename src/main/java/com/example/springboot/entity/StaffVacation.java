package com.example.springboot.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "staff_vacation")
public class StaffVacation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Integer staff_id;

    @Column(nullable = false, length = 200)
    private String leave_type;

    @Column(nullable = false)
    private LocalDate leave_from;

    @Column(nullable = false)
    private LocalDate leave_to;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String leave_form;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String notes;

    @Column(nullable = false)
    private Integer appoval_status;

    @Column(columnDefinition = "TEXT")
    private String settlement_form;

    @Column(nullable = false)
    private Integer status;

    @Column(nullable = false, length = 200)
    private String rejoin_date;

    @Column(nullable = false)
    private Integer updated_by;

    private LocalDate updated_date;

    public String getLeave_type() {
        return leave_type;
    }

    public void setLeave_type(String leave_type) {
        this.leave_type = leave_type;
    }

    public Integer getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(Integer staff_id) {
        this.staff_id = staff_id;
    }

    public LocalDate getLeave_from() {
        return leave_from;
    }

    public void setLeave_from(LocalDate leave_from) {
        this.leave_from = leave_from;
    }

    public LocalDate getLeave_to() {
        return leave_to;
    }

    public void setLeave_to(LocalDate leave_to) {
        this.leave_to = leave_to;
    }

    public String getLeave_form() {
        return leave_form;
    }

    public void setLeave_form(String leave_form) {
        this.leave_form = leave_form;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Integer getAppoval_status() {
        return appoval_status;
    }

    public void setAppoval_status(Integer appoval_status) {
        this.appoval_status = appoval_status;
    }

    public String getSettlement_form() {
        return settlement_form;
    }

    public void setSettlement_form(String settlement_form) {
        this.settlement_form = settlement_form;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRejoin_date() {
        return rejoin_date;
    }

    public void setRejoin_date(String rejoin_date) {
        this.rejoin_date = rejoin_date;
    }

    public Integer getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(Integer updated_by) {
        this.updated_by = updated_by;
    }

    public LocalDate getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDate updated_date) {
        this.updated_date = updated_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
