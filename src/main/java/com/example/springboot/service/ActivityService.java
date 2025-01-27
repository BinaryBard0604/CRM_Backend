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
public class ActivityService {

    private static final Logger logger = LoggerFactory.getLogger(OpportunityService.class);

    @Autowired
    private ActivityRepository activityRepository;

    public List<Activity> getAllActivities() {
        return activityRepository.findAllWithStatus();
    }

    public List<Map<String, Object>> getAllDataActivities() {
        return activityRepository.findAllDataWithStatus();
    }

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id);
    }

    public Activity createActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    public Optional<Activity> updateActivity(Long id, Activity activity) {
        return activityRepository.findById(id).map(existingActivity -> {
            existingActivity.setActivity_status(activity.getActivity_status());
            existingActivity.setAssign_id(activity.getAssign_id());
            existingActivity.setDeadline(activity.getDeadline());
            existingActivity.setNotes(activity.getNotes());
            existingActivity.setOpportunity_id(activity.getOpportunity_id());
            existingActivity.setStatus(activity.getStatus());
            existingActivity.setSummary(activity.getSummary());
            existingActivity.setType(activity.getType());
            return activityRepository.save(existingActivity);
        });
    }

    public ResponseEntity<Map<String, String>> deleteActivity(Long id) {

        Map<String, String> result = new HashMap<>();

        try {
            activityRepository.deleteByIdWithStatus(id);

            result.put("msg", "The activity is deleted successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An error occurred while deleting the activity"));
        }
    }
}