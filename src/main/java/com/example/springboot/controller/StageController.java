package com.example.springboot.controller;

import com.example.springboot.Dto.StageAllResponse;
import com.example.springboot.entity.*;
import com.example.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping
    public List<Stage> getAllStages() {
        return stageService.getAllStages();
    }

    @GetMapping("/stageAll")
    public List<Map<String, Object>> getAllStageResponse() {
        return stageService.getAllStageResponse();
    }

    @GetMapping("/{id}")
    public Optional<Stage> getStageById(@PathVariable Long id) {
        return stageService.getStageById(id);
    }

    @PostMapping
    public Stage createStage(@RequestBody Stage stage) {
        return stageService.createStage(stage);
    }

    @PutMapping("/{id}")
    public Optional<Stage> updateStage(@PathVariable Long id, @RequestBody Stage stage) {
        return stageService.updateStage(id, stage);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteStage(@RequestBody Map<String, Long> payload) {
        return stageService.deleteStage(payload.get("id"));
    }
}