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
public class TeamService {

    private static final Logger logger = LoggerFactory.getLogger(TeamService.class);

    @Autowired
    private TeamRepository teamRepository;

    public List<Team> getAllTeams() {
        return teamRepository.findAllWithStatus();
    }

    public Optional<Team> getTeamById(Long id) {
        return teamRepository.findById(id);
    }

    public List<Map<String, Object>> getAllData() {
        return teamRepository.getAllData();
    }

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Optional<Team> updateTeam(Long id, Team team) {
        return teamRepository.findById(id).map(existingTeam -> {
            existingTeam.setName(team.getName());
            existingTeam.setLeader_id(team.getLeader_id());
            existingTeam.setEmail(team.getEmail());
            existingTeam.setTarget(team.getTarget());
            existingTeam.setStatus(team.getStatus());
            return teamRepository.save(existingTeam);
        });
    }

    public ResponseEntity<Map<String, String>> deleteTeam(Long id) {

        Map<String, String> result = new HashMap<>();

        try {
            List<Map<String, Object>> check = teamRepository.check(id);

            if (check.size() == 0 || check == null) {
                teamRepository.deleteByIdWithStatus(id);

                result.put("flag", "0");
                result.put("msg", "The team is deleted successfully.");
            } else {
                result.put("flag", "1");
                result.put("msg", "Cannot delete");
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An error occurred while deleting the activity"));
        }
    }
}