package com.example.springboot.controller;

import com.example.springboot.service.VisaApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class VisaApplicationController {

    @Autowired
    VisaApplicationService visaApplicationService;

    @PostMapping("/visaApplication")
    public List<Map<String, Object>> getStaffDashboard(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");

        if ("normal".equals(type)) {
            return visaApplicationService.getNormalVisaApplications();
        } else if ("update_visa".equals(type)) {
            String driver = payload.get("driver");
            String approvalStatus = payload.get("approval_status");

            return visaApplicationService.updateVisaApplication(driver, approvalStatus);
        } else {
            return null;
        }
    }


    @PostMapping("/getVisaDetails")
    public Map<String, String> getDriverVisaDetails(@RequestBody Map<String, String> payload) {
        String driverId = payload.get("driverId");

        return visaApplicationService.getDriverVisaDetail(driverId);
    }
}
