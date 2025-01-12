package com.example.springboot.controller;

import com.example.springboot.service.OtherReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OtherReportController {

    @Autowired
    OtherReportService otherReportService;

    @PostMapping("/otherReport")
    public List<Map<String, String>> getFuelReport(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");
        String month = payload.get("month");
        String year = payload.get("year");

        if ("salik".equals(type)) {
            return otherReportService.getSalikData(year, month);
        } else if ("telephone".equals(type)) {
            return otherReportService.getTelephoneData(year, month);
        } else {
            return null;
        }
    }
}
