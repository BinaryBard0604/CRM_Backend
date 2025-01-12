package com.example.springboot.controller;

import com.example.springboot.entity.Admin;
import com.example.springboot.service.LeavesService;
import com.example.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LeavesController {

    @Autowired
    LeavesService leavesService;

    @PostMapping("/leaves")
    public List<Map<String, String>> leaves(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");

        if ("normal".equals(type)) {
            return leavesService.getNormalLeaves();
        } else if ("updateleave".equals(type)) {
            String approvalStatus = payload.get("approval_status");
            String vacationId = payload.get("vacationid");
            return leavesService.updateLeaves(approvalStatus, vacationId);
        } else if ("update_staff_leave".equals(type)) {
            String approvalStatus = payload.get("approval_status");
            String vacationId = payload.get("val");
            String notes = payload.get("notes");
            return leavesService.updateStaffLeave(approvalStatus, vacationId, notes);
        } else {
            return null;
        }
    }
}