package com.example.springboot.service;

import com.example.springboot.repository.DriverDetailsRepository;
import com.example.springboot.repository.SalikReportRepository;
import com.example.springboot.repository.TelephoneDataRepository;
import com.example.springboot.repository.VehicleAssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Driver;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OtherReportService {

    @Autowired
    SalikReportRepository salikReportRepository;

    @Autowired
    VehicleAssignmentRepository vehicleAssignmentRepository;

    @Autowired
    TelephoneDataRepository telephoneDataRepository;

    @Autowired
    DriverDetailsRepository driverDetailsRepository;

    public List<Map<String, String>> getSalikData(String year, String month) {
        String month_year = month + "-" + year;
        LocalDate startDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Map<String, Object>> salikdata = salikReportRepository.getDataWithMonthYear(month_year);
        List<Map<String, String>> result = new ArrayList<>();

        if (salikdata != null && !salikdata.isEmpty()) {
            List<String> regNumbers = salikdata.stream()
                    .map(v -> String.valueOf(v.get("vehicleReg")))
                    .collect(Collectors.toList());

            List<Map<String, Object>> assignments = vehicleAssignmentRepository.getDataWithDates(startDate, endDate, regNumbers);

            // Create a map for fast lookup of assignments
            Map<String, List<Map<String, Object>>> assignmentsMap = new HashMap<>();
            for (Map<String, Object> assignment : assignments) {
                String regNumber = String.valueOf(assignment.get("regNumber"));
                assignmentsMap.computeIfAbsent(regNumber, k -> new ArrayList<>()).add(assignment);
            }

            for (Map<String, Object> row : salikdata) {
                Map<String, String> temp = new HashMap<>();
                String regNumber = String.valueOf(row.get("vehicleReg"));
                temp.put("vehicle_reg", String.valueOf(row.get("vehicleReg")));
                temp.put("amount", String.valueOf(row.get("amount")));
                temp.put("times", String.valueOf(row.get("times")));

                List<Map<String, Object>> driverAssignments = assignmentsMap.get(regNumber);
                if (driverAssignments != null && !driverAssignments.isEmpty()) {
                    List<String> driverData = new ArrayList<>();
                    List<String> driverIds = new ArrayList<>();
                    for (Map<String, Object> assignment : driverAssignments) {
                        driverData.add(String.format("%s [from: %s, to: %s, Branch: %s] status: %s",
                                assignment.get("driverName"), assignment.get("fromDate"), assignment.get("toDate"),
                                assignment.get("branch"), assignment.get("status")));
                        driverIds.add(String.valueOf(assignment.get("driverId")));
                    }
                    temp.put("driver_name", String.join(", ", driverData));
                    temp.put("driver_id", driverIds.get(0));
                } else {
                    temp.put("driver_name", "");
                    temp.put("driver_id", "");
                }

                result.add(temp);
            }
        }
        return result;
    }

    public List<Map<String, String>> getTelephoneData(String year, String month) {

        String month_year = month + "-" + year;
        LocalDate startDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        List<Map<String, Object>> telephoneData = telephoneDataRepository.getTelephoneDataWithMonthYear(month_year);
        List<Map<String, String>> result = new ArrayList<>();

        if (telephoneData != null && !telephoneData.isEmpty()) {
            for (Map<String, Object> row : telephoneData) {
                Map<String, String> temp = new HashMap<>();

                temp.put("phone", String.valueOf(row.get("phone")));
                temp.put("amount", String.valueOf(row.get("amount")));

                Map<String, Object> driverDetails = driverDetailsRepository.getDataWithPhone(String.valueOf(row.get("phone")));
                if (driverDetails != null && !driverDetails.isEmpty()) {
                    temp.put("driver_name", String.valueOf(driverDetails.get("driver_name")));
                }

                result.add(temp);
            }
        }

        return result;
    }
}
