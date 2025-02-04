package com.example.springboot.Controller;

import com.example.springboot.Entity.*;
import com.example.springboot.Service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/opportunities")
public class OpportunityController {

    private static final Logger logger = LoggerFactory.getLogger(OpportunityController.class);

    @Autowired
    private OpportunityService opportunityService;

    @GetMapping
    public List<Opportunity> getAllOpportunities() {
        return opportunityService.getAllOpportunities();
    }

    @PostMapping("/anlaysis")
    public List<Map<String, Object>> getAnalysis(@RequestBody Map<String, String> payload) {
        Integer year = Integer.parseInt(payload.get("year"));
        logger.info(">>>>>>>" + year);

        return opportunityService.getAnalysis(year);
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllDataOpportunities() {
        return opportunityService.getAllDataOpportunities();
    }

    @GetMapping("/{id}")
    public Optional<Opportunity> getOpportunityById(@PathVariable Long id) {
        return opportunityService.getOpportunityById(id);
    }

    @PostMapping
    public Opportunity createOpportunity(@RequestBody Opportunity opportunity) {
        return opportunityService.createOpportunity(opportunity);
    }

    @PutMapping("/{id}")
    public Optional<Opportunity> updateOpportunity(@PathVariable Long id, @RequestBody Opportunity opportunity) {
        return opportunityService.updateOpportunity(id, opportunity);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteOpportunity(@RequestBody Map<String, Long> payload) {
        return opportunityService.deleteOpportunity(payload.get("id"));
    }
}