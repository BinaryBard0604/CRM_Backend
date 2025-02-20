package com.example.springboot.controller;

import com.example.springboot.entity.*;
import com.example.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/salespersons")
public class SalespersonController {

    @Autowired
    private SalespersonService salespersonService;

    @GetMapping
    public List<Salesperson> getAllSalespersons() {
        return salespersonService.getAllSalespersons();
    }

    @GetMapping("/{id}")
    public Optional<Salesperson> getSalespersonById(@PathVariable Long id) {
        return salespersonService.getSalespersonById(id);
    }

    @PostMapping
    public Salesperson createSalesperson(@RequestBody Salesperson salesperson) {
        return salespersonService.createSalesperson(salesperson);
    }

    @PostMapping("/updateLogin")
    public Optional<Salesperson> updateLoginDate(@RequestBody Map<String, String> payload) {
        String loginTime = payload.get("loginTime");
        String email = payload.get("email");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy, HH:mm:ss");

        // Parse the string to LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(loginTime, formatter);
        return salespersonService.updateLoginDate(localDateTime, email);
    }

    @PutMapping("/{id}")
    public Optional<Salesperson> updateSalesperson(@PathVariable Long id, @RequestBody Salesperson salesperson) {
        return salespersonService.updateSalesperson(id, salesperson);
    }
}