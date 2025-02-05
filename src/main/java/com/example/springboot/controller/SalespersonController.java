package com.example.springboot.Controller;

import com.example.springboot.Entity.*;
import com.example.springboot.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Salesperson createSalesperson(@RequestBody Salesperson salesperson) {
        return salespersonService.createSalesperson(salesperson);
    }
}