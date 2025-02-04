package com.example.springboot.Service;

import com.example.springboot.Entity.*;
import com.example.springboot.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class OpportunityService {

    private static final Logger logger = LoggerFactory.getLogger(OpportunityService.class);

    @Autowired
    private OpportunityRepository opportunityRepository;

    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAllWithStatus();
    }

    public List<Map<String, Object>> getAnalysis() {
        return opportunityRepository.getAnalysis();
    }

    public List<Map<String, Object>> getAllDataOpportunities() {
        return opportunityRepository.findAllDataWithStatus();
    }

    public Optional<Opportunity> getOpportunityById(Long id) {
        return opportunityRepository.findById(id);
    }

    public Opportunity createOpportunity(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    public Optional<Opportunity> updateOpportunity(Long id, Opportunity opportunity) {
        return opportunityRepository.findById(id).map(existingOpportunity -> {
            existingOpportunity.setName(opportunity.getName());
            existingOpportunity.setExpected_revenue(opportunity.getExpected_revenue());
            existingOpportunity.setProbability(opportunity.getProbability());
            existingOpportunity.setContact_id(opportunity.getContact_id());
            existingOpportunity.setSalesperson_id(opportunity.getSalesperson_id());
            existingOpportunity.setExpected_closing(opportunity.getExpected_closing());
            existingOpportunity.setTags(opportunity.getTags());
            existingOpportunity.setStage_id(opportunity.getStage_id());
            existingOpportunity.setCreated_date(existingOpportunity.getCreated_date());
            existingOpportunity.setRating(opportunity.getRating());
            existingOpportunity.setStatus(opportunity.getStatus());
            return opportunityRepository.save(existingOpportunity);
        });
    }

    public ResponseEntity<Map<String, String>> deleteOpportunity(Long id) {

        Map<String, String> result = new HashMap<>();

        try {
            List<Map<String, Object>> check = opportunityRepository.check(id);

            if (check.size() == 0 || check == null) {
                opportunityRepository.deleteByIdWithStatus(id);

                result.put("flag", "0");
                result.put("msg", "The opportunity is deleted successfully.");
            } else {
                result.put("flag", "1");
                result.put("msg", "Cannot delete");
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An error occurred while deleting the opportunity"));
        }
    }
}