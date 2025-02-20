package com.example.springboot.service;

import com.example.springboot.entity.*;
import com.example.springboot.repository.*;
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
public class StageService {

    private static final Logger logger = LoggerFactory.getLogger(StageService.class);

    @Autowired
    private StageRepository stageRepository;

    public List<Stage> getAllStages() {
        return stageRepository.findAllWithStatus();
    }

    public Optional<Stage> getStageById(Long id) {
        return stageRepository.findById(id);
    }

    public Stage createStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public Optional<Stage> updateStage(Long id, Stage stage) {
        return stageRepository.findById(id).map(existingStage -> {
            existingStage.setName(stage.getName());
            existingStage.setStatus(stage.getStatus());
            return stageRepository.save(existingStage);
        });
    }

    public ResponseEntity<Map<String, String>> deleteStage(Long id) {

        Map<String, String> result = new HashMap<>();

        try {
            stageRepository.deleteByIdWithStatus(id);

            result.put("msg", "The stage is deleted successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An error occurred while deleting the stage"));
        }
    }

    public List<Map<String, Object>> getAllStageResponse() {
        return stageRepository.getAllStageResponse();
    }
}