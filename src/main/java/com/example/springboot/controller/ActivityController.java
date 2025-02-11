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
@RequestMapping("/api/activities")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/all")
    public List<Map<String, Object>> getAllDataActivities() {
        return activityService.getAllDataActivities();
    }

    @GetMapping("/allCalendar")
    public List<Map<String, Object>> getAllCalendarDataActivities() {
        return activityService.getAllCalendarDataActivities();
    }

    @GetMapping("/{id}")
    public Optional<Activity> getActivityById(@PathVariable Long id) {
        return activityService.getActivityById(id);
    }

    @PostMapping
    public Activity createActivity(@RequestBody Activity activity) {
        return activityService.createActivity(activity);
    }

    @PutMapping("/{id}")
    public Optional<Activity> updateActivity(@PathVariable Long id, @RequestBody Activity activity) {
        return activityService.updateActivity(id, activity);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteActivity(@RequestBody Map<String, Long> payload) {
        return activityService.deleteActivity(payload.get("id"));
    }
}