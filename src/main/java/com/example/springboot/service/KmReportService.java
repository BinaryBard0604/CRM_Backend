package com.example.springboot.service;

import com.example.springboot.repository.KmDetailsRepository;
import com.example.springboot.repository.VehicleAssignmentRepository;
import com.example.springboot.repository.VehicleDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KmReportService {

    @Autowired
    VehicleDetailsRepository vehicleDetailsRepository;

    @Autowired
    KmDetailsRepository kmDetailsRepository;

    @Autowired
    VehicleAssignmentRepository vehicleAssignmentRepository;

    public List<Map<String, String>> getKmReport(String startDate, String endDate) {
        // Fetch all vehicle details at once
        List<Map<String, Object>> vehicleDetails = vehicleDetailsRepository.getVehicleDetailsByStatus();
        List<Map<String, String>> kmReports = new ArrayList<>();

        if (vehicleDetails != null && !vehicleDetails.isEmpty()) {
            // Create a list of vehicle registration numbers
            List<String> regNumbers = vehicleDetails.stream()
                    .map(v -> String.valueOf(v.get("regNumber")))
                    .collect(Collectors.toList());

            // Fetch all km details for the month and year for the relevant vehicles
            List<Map<String, Object>> kmDetails = kmDetailsRepository.getDataWithVehicles(LocalDate.parse(startDate), LocalDate.parse(endDate), regNumbers);
            // Fetch all vehicle assignments for the relevant vehicles in the date range
            List<Map<String, Object>> assignments = vehicleAssignmentRepository.getDataWithDates(LocalDate.parse(startDate), LocalDate.parse(endDate), regNumbers);

            // Create a map for fast lookup of km details
            Map<String, Map<String, Object>> kmDetailsMap = kmDetails.stream()
                    .collect(Collectors.toMap(
                            km -> String.valueOf(km.get("vehicle_reg")),
                            km -> km,
                            (existing, replacement) -> {
                                // Define how to handle duplicates; for example, keep the existing one
                                return existing; // or you can merge them in a way that makes sense for your use case
                            }
                    ));

            // Create a map for fast lookup of assignments
            Map<String, List<Map<String, Object>>> assignmentsMap = new HashMap<>();
            for (Map<String, Object> assignment : assignments) {
                String regNumber = String.valueOf(assignment.get("regNumber"));
                assignmentsMap.computeIfAbsent(regNumber, k -> new ArrayList<>()).add(assignment);
            }

            for (Map<String, Object> row : vehicleDetails) {
                Map<String, String> temp = new HashMap<>();
                String regNumber = String.valueOf(row.get("regNumber"));

                // Set vehicle registration number
                temp.put("reg_number", regNumber);

                // Set km details
                Map<String, Object> kmData = kmDetailsMap.get(regNumber);
                if (kmData != null) {
                    temp.put("km", String.valueOf(kmData.get("km")));
                    temp.put("km_updated_date", String.valueOf(kmData.get("updatedDate")));
                    temp.put("km_status", "recorded");
                } else {
                    temp.put("km_status", "No Records");
                }

                // Set driver details
                List<Map<String, Object>> driverAssignments = assignmentsMap.get(regNumber);
                if (driverAssignments != null && !driverAssignments.isEmpty()) {
                    List<String> driverData = new ArrayList<>();
                    for (Map<String, Object> assignment : driverAssignments) {
                        driverData.add(String.format("%s [from: %s, to: %s, Branch: %s] status: %s",
                                assignment.get("driverName"), assignment.get("fromDate"), assignment.get("toDate"),
                                assignment.get("branch"), assignment.get("status")));
                    }
                    temp.put("driver_name", String.join("</br>", driverData));
                    // Set branch if status is 1
                    driverAssignments.stream()
                            .filter(a -> Integer.parseInt(String.valueOf(a.get("status"))) == 1)
                            .findFirst()
                            .ifPresent(a -> temp.put("branch", String.valueOf(a.get("branch"))));
                } else {
                    temp.put("driver_name", "");
                }

                kmReports.add(temp);
            }
        }
        return kmReports;
    }
}
