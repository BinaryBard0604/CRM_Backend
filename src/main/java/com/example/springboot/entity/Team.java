package com.example.springboot.Entity;

import com.example.springboot.Entity.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long leader_id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer target;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String salespersons;

    @Column(nullable = false)
    private Integer status;

    public Team() {
    }

    public Team(String name, Long leader_id, String email, Integer target, String salespersons, Integer status) {
        this.name = name;
        this.leader_id = leader_id;
        this.email = email;
        this.target = target;
        this.salespersons = salespersons;
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

    public Long getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(Long leader_id) {
        this.leader_id = leader_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getSalespersons() {
        return salespersons;
    }

    public void setSalespersons(String salespersons) {
        this.salespersons = salespersons;
    }
}
