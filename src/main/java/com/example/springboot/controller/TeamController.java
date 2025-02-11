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
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping("/api/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public Optional<Team> getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllData() {
        return teamService.getAllData();
    }

    @PostMapping
    public Team createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @PostMapping("/searchTeam")
    public List<Map<String, Object>> searchTeamWithSalesperson(@RequestBody Map<String, String> payload) {
        String salespersonId = payload.get("salespersonId");

        return teamService.searchTeamWithSalesperson(salespersonId);
    }

    @PutMapping("/{id}")
    public Optional<Team> updateTeam(@PathVariable Long id, @RequestBody Team team) {
        return teamService.updateTeam(id, team);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteTeam(@RequestBody Map<String, Long> payload) {
        return teamService.deleteTeam(payload.get("id"));
    }
}