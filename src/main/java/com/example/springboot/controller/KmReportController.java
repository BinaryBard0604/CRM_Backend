package com.example.springboot.controller;

import com.example.springboot.service.KmReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class KmReportController {

    @Autowired
    KmReportService kmReportService;

    @PostMapping("/kmReport")
    public List<Map<String, String>> getKmReport(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");
        String startDate = payload.get("start");
        String endDate = payload.get("end");

        return kmReportService.getKmReport(startDate, endDate);
    }
}

