package com.example.springboot.controller;

import com.example.springboot.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @PostMapping("/analytics")
    public Map<String, String> getAnalytics(@RequestBody Map<String, String> requestBody) {
        String type = requestBody.get("type");
        String year = requestBody.get("year");
        String branch = requestBody.get("branch");

        if ("driver_vehicle".equals(type)) {
            return analyticsService.getDriverVehicle(year, branch);
        } else if ("leave_application".equals(type)) {
            return analyticsService.getLeaveApplication(year, branch);
        } else if ("couryier_stat".equals(type)) {
            return analyticsService.getCouryierStat(year, branch);
        } else if ("fuel_km".equals(type)) {
            return analyticsService.getFuelKm(year, branch);
        } else {
            return null;
        }
    }
}