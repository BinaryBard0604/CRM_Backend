package com.example.springboot.controller;

import com.example.springboot.entity.DriverDetails;
import com.example.springboot.entity.DriverVacation;
import com.example.springboot.entity.VehicleDetails;
import com.example.springboot.service.DashboardNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardNotificationController {

    @Autowired
    private DashboardNotificationService dashboardNotificationService;

    @PostMapping("/dashboardNotification")
    public Map<String, List<Map<String, Object>>> getInfo(@RequestBody Map<String, String> requestBody) {
        String user = requestBody.get("user");
        String branch = requestBody.get("branch");
        String user_role = requestBody.get("user_role");

        int userRole = Integer.parseInt(user_role);
        int userBranch = Integer.parseInt(branch);

        List<Object[]> licenseExpiry = dashboardNotificationService.getLicenseExpiringSoon(userBranch);
        List<Object[]> licenseExpired = dashboardNotificationService.getLicenseExpired(userBranch);
        List<Object[]> visaExpiry = dashboardNotificationService.getVisaExpiry(userBranch);
        List<Object[]> passportExpiry = dashboardNotificationService.getPassportExpiry(userBranch);
        List<Object[]> leaseExpiry = dashboardNotificationService.getLeaseExpiringSoon();
        List<Object[]> leaveOverdue = dashboardNotificationService.getLeaveOverdue();
        List<Object[]> newJoinees = dashboardNotificationService.getNewJoiness();

        List<Map<String, Object>> formattedlicenseExpiry = new ArrayList<>();
        for (Object[] row : licenseExpiry) {
            Map<String, Object> map = new HashMap<>();
            map.put("first_name", row[0]);
            map.put("last_name", row[1]);
            map.put("license_expiry_date", row[2]);
            formattedlicenseExpiry.add(map);
        }

        List<Map<String, Object>> formattedlicenseExpired = new ArrayList<>();
        for (Object[] row : licenseExpired) {
            Map<String, Object> map = new HashMap<>();
            map.put("first_name", row[0]);
            map.put("last_name", row[1]);
            map.put("license_expiry_date", row[2]);
            formattedlicenseExpired.add(map);
        }

        List<Map<String, Object>> formattedvisaExpiry = new ArrayList<>();
        for (Object[] row : visaExpiry) {
            Map<String, Object> map = new HashMap<>();
            map.put("first_name", row[0]);
            map.put("last_name", row[1]);
            map.put("work_permit_expiry", row[2]);
            formattedvisaExpiry.add(map);
        }

        List<Map<String, Object>> formattedpassportExpiry = new ArrayList<>();
        for (Object[] row : passportExpiry) {
            Map<String, Object> map = new HashMap<>();
            map.put("first_name", row[0]);
            map.put("last_name", row[1]);
            map.put("pdrp_expiry_date", row[2]);
            formattedpassportExpiry.add(map);
        }

        List<Map<String, Object>> formattedleaseExpiry = new ArrayList<>();
        for (Object[] row : leaseExpiry) {
            Map<String, Object> map = new HashMap<>();
            map.put("vehicle_name", row[0]);
            map.put("reg_number", row[1]);
            map.put("purchase_to", row[2]);
            formattedleaseExpiry.add(map);
        }

        List<Map<String, Object>> formattedleaveOverdue = new ArrayList<>();
        for (Object[] row : leaveOverdue) {
            Map<String, Object> map = new HashMap<>();
            map.put("driver_id", row[0]);
            map.put("leave_from", row[1]);
            map.put("leave_to", row[2]);
            map.put("first_name", row[3]);
            map.put("last_name", row[4]);
            map.put("rejoin_date", row[5]);
            formattedleaveOverdue.add(map);
        }

        List<Map<String, Object>> formattednewJoinees = new ArrayList<>();
        for (Object[] row : newJoinees) {
            Map<String, Object> map = new HashMap<>();
            map.put("first_name", row[0]);
            map.put("last_name", row[1]);
            map.put("citizenship", row[2]);
            map.put("date_employed", row[3]);
            formattednewJoinees.add(map);
        }

        return Map.of(
                "licenseexpiry", formattedlicenseExpiry,
                "licenseexpired", formattedlicenseExpired,
                "visaexpiry", formattedvisaExpiry,
                "passportexpiry", formattedpassportExpiry,
                "leaseexpiry", formattedleaseExpiry,
                "leaveoverdue", formattedleaveOverdue,
                "newjoinees", formattednewJoinees
        );
    }
}
