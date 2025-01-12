package com.example.springboot.controller;

import com.example.springboot.service.FuelReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FuelReportController {

    @Autowired
    FuelReportService fuelReportService;

    @PostMapping("/fuelReport")
    public List<Map<String, String>> getFuelReport(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");

        if ("report".equals(type)) {
            String startDate = payload.get("start");
            String endDate = payload.get("end");

            return fuelReportService.getFuelReportByReport(startDate, endDate);
        } else if ("excessreport".equals(type)) {
            String startDate = payload.get("start");
            String endDate = payload.get("end");

            return fuelReportService.getFuelReportByExcessReport(startDate, endDate);
        } else if ("driverfuel".equals(type)) {
            String startDate = payload.get("start");
            String endDate = payload.get("end");

            return fuelReportService.getFuelReportByDriverFuel(startDate, endDate);
        } else {
            return null;
        }
    }

    @PostMapping("/kmFuelDriver")
    public List<Map<String, String>> getKmFuelDriver(@RequestBody Map<String, String> payload) {
        String startDate = payload.get("start");
        String endDate = payload.get("end");

        return fuelReportService.getKmFuelDriver(startDate, endDate);
    }
}

