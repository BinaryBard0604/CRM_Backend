package com.example.springboot.controller;

import com.example.springboot.repository.AdminRepository;
import com.example.springboot.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    AdminRepository adminRepository;

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private DashboardService dashboardService;

    @PostMapping("/username")
    public String getUserName(@RequestBody Map<String, String> payload) {
        String user = payload.get("user");
        return adminRepository.getUserName(Integer.parseInt(user));
    }

    @PostMapping("/dashboard")
    public Map<String, Long> getCounts(@RequestBody Map<String, String> requestBody) {
        String user = requestBody.get("user");
        String branch = requestBody.get("branch");
        String user_role = requestBody.get("user_role");

        int userRole = Integer.parseInt(user_role);
        int userBranch = Integer.parseInt(branch);

        long driverCount = dashboardService.getDriverCount(userRole, userBranch);
        long vehicleCount = dashboardService.getVehicleCount(userRole, userBranch);
        long totalVehicleCount = dashboardService.getTotalVehicleCount();
        long driverLeavesCount = dashboardService.getDriverLeavesCount(userRole, userBranch);
        long freeVehicleCount = dashboardService.getfreeVehicleCount();
        long idleDriverCount = dashboardService.getIdleDriverCount(userRole, userBranch);

        return Map.of(
                "drivercount", driverCount,
                "vehiclecount", vehicleCount,
                "totalvehiclecount", totalVehicleCount,
                "driverleavescount", driverLeavesCount,
                "freevehiclecount", freeVehicleCount,
                "idledrivercount", idleDriverCount
        );
    }
}