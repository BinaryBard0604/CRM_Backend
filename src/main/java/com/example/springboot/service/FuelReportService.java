package com.example.springboot.service;

import com.example.springboot.entity.KmDetails;
import com.example.springboot.repository.*;
import org.aspectj.apache.bcel.generic.ObjectType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FuelReportService {

    @Autowired
    FuelData2021Repository fuelData2021Repository;

    @Autowired
    FuelData2022Repository fuelData2022Repository;

    @Autowired
    FuelData2023Repository fuelData2023Repository;

    @Autowired
    FuelData2024Repository fuelData2024Repository;

    @Autowired
    FuelData2025Repository fuelData2025Repository;

    @Autowired
    FuelData2026Repository fuelData2026Repository;

    @Autowired
    VehicleAssignmentRepository vehicleAssignmentRepository;

    @Autowired
    VehicleDetailsRepository vehicleDetailsRepository;

    @Autowired
    VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    DriverDetailsRepository driverDetailsRepository;

    @Autowired
    KmDetailsRepository kmDetailsRepository;

    private static final Logger logger = LoggerFactory.getLogger(FuelReportService.class);

    public List<Map<String, String>> getFuelReportByReport(String startDate, String endDate) {
        String startYear = startDate.split("-")[0];
        String endYear = endDate.split("-")[0];
        List<Map<String, String>> fuelReports = new ArrayList<>();
        logger.info(">>>>>>>>" + startDate + endDate + startYear);

        List<Object[]> results = new ArrayList<>();
        if ("2021".equals(startYear)) {
            results = fuelData2021Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if ("2022".equals(startYear)) {
            results = fuelData2022Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if ("2023".equals(startYear)) {
            results = fuelData2023Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if ("2024".equals(startYear)) {
            results = fuelData2024Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if ("2025".equals(startYear)) {
            results = fuelData2025Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if ("2026".equals(startYear)) {
            results = fuelData2026Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        }

        if (!results.isEmpty()) {
            List<String> vehiclePlateNumbers = results.stream()
                    .map(row -> String.valueOf(row[2])) // Assuming row[2] is the plate number
                    .collect(Collectors.toList());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            List<Map<String, Object>> assignments = vehicleAssignmentRepository.findAllAssignments(
                    LocalDate.parse(endDate, formatter),
                    vehiclePlateNumbers
            );

            Map<String, Map<String, Object>> assignmentMap = assignments.stream()
                    .collect(Collectors.toMap(
                            assignment -> (String) assignment.get("vehiclePlateNumber"), // Key: Plate number
                            assignment -> assignment, // Value: Assignment details
                            (existing, replacement) -> existing // Handle duplicates by keeping the first occurrence
                    ));

            for (Object[] row : results) {
                Map<String, String> fuelReportMap = new HashMap<>();
                String vehiclePlateNumber = String.valueOf(row[2]); // Assuming row[2] is the plate number

                // Add total usage (vehicle plate number in this case)
                fuelReportMap.put("total_usage", String.valueOf(row[9]));
                fuelReportMap.put("ActualAmount", String.valueOf(row[6]));
                fuelReportMap.put("ProductName", String.valueOf(row[7]));
                fuelReportMap.put("TotalAmount", String.valueOf(row[10]));
                fuelReportMap.put("TransactionDate", String.valueOf(row[3]));
                fuelReportMap.put("UnitPrice", String.valueOf(row[4]));
                fuelReportMap.put("VatAmount", String.valueOf(row[9]));
                fuelReportMap.put("VatRate", String.valueOf(row[8]));
                fuelReportMap.put("VehiclePlateNumber", String.valueOf(row[2]));
                fuelReportMap.put("Vendor", String.valueOf(row[1]));
                fuelReportMap.put("Volume", String.valueOf(row[5]));
                fuelReportMap.put("id", String.valueOf(row[0]));

                // Retrieve assignment details from the map
                Map<String, Object> assignment = assignmentMap.get(vehiclePlateNumber);

                // Populate fuel report with assignment details if available
                if (assignment != null && !assignment.isEmpty()) {
                    fuelReportMap.put("driver_name", String.valueOf(assignment.get("driverName")));
                    fuelReportMap.put("branch", String.valueOf(assignment.get("branch")));
                    fuelReportMap.put("driver_id", String.valueOf(assignment.get("driverId")));
                } else {
                    fuelReportMap.put("driver_name", "");
                    fuelReportMap.put("branch", "");
                    fuelReportMap.put("driver_id", "");
                }

                // Add the fuel report to the list
                fuelReports.add(fuelReportMap);
            }
        }

        return fuelReports;
    }

    public List<Map<String, String>> getFuelReportByExcessReport(String startDate, String endDate) {
        String startYear = startDate.split("-")[0];
        String endYear = endDate.split("-")[0];
        List<Map<String, String>> fuelReports = new ArrayList<>();

        List<Object[]> results = new ArrayList<>();
        if ("2021".equals(startYear)) {
            results = fuelData2021Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if ("2022".equals(startYear)) {
            results = fuelData2022Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if ("2023".equals(startYear)) {
            results = fuelData2023Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if ("2024".equals(startYear)) {
            results = fuelData2024Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if ("2025".equals(startYear)) {
            results = fuelData2025Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        } else if ("2026".equals(startYear)) {
            results = fuelData2026Repository.getDataByTable(LocalDate.parse(startDate), LocalDate.parse(endDate));
        }

        if (!results.isEmpty()) {
            List<String> vehiclePlateNumbers = results.stream()
                    .map(row -> String.valueOf(row[2])) // Assuming row[2] is the plate number
                    .collect(Collectors.toList());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            List<Map<String, Object>> vehicleDetailsList = vehicleDetailsRepository.findAllVehiclesWithPlateNumbers(vehiclePlateNumbers);

            Map<String, Map<String, Object>> vehicleDetailsMap = new HashMap<>();
            for (Map<String, Object> vehicle : vehicleDetailsList) {
                String plateNumber = String.valueOf(vehicle.get("reg_number")); // Adjust based on your actual key
                vehicleDetailsMap.put(plateNumber, vehicle);
            }

            List<Map<String, Object>> totalAmounts = new ArrayList<>();
            if ("2021".equals(startYear)) {
                totalAmounts = fuelData2021Repository.getTotalAmounts(vehiclePlateNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if ("2022".equals(startYear)) {
                totalAmounts = fuelData2022Repository.getTotalAmounts(vehiclePlateNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if ("2023".equals(startYear)) {
                totalAmounts = fuelData2023Repository.getTotalAmounts(vehiclePlateNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if ("2024".equals(startYear)) {
                totalAmounts = fuelData2024Repository.getTotalAmounts(vehiclePlateNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if ("2025".equals(startYear)) {
                totalAmounts = fuelData2025Repository.getTotalAmounts(vehiclePlateNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if ("2026".equals(startYear)) {
                totalAmounts = fuelData2026Repository.getTotalAmounts(vehiclePlateNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            }

            Map<String, Float> totalAmountMap = new HashMap<>();
            for (int i = 0; i < totalAmounts.size(); i++) {
                String plateNumber = String.valueOf(totalAmounts.get(i).get("vehiclePlateNumber"));
                Float totalAmount = Float.valueOf(String.valueOf(totalAmounts.get(i).get("total_usage")));

                // Put the plate number and total amount into the map
                totalAmountMap.put(plateNumber, totalAmount);
            }

            List<Map<String, Object>> assignments = vehicleAssignmentRepository.findAllAssignments(
                    LocalDate.parse(endDate, formatter),
                    vehiclePlateNumbers
            );

            Map<String, Map<String, Object>> assignmentMap = assignments.stream()
                    .collect(Collectors.toMap(
                            assignment -> (String) assignment.get("vehiclePlateNumber"), // Key: Plate number
                            assignment -> assignment, // Value: Assignment details
                            (existing, replacement) -> existing // Handle duplicates by keeping the first occurrence
                    ));

            for (int i = 0; i < results.size(); i++) {
                Object[] row = results.get(i);
                Map<String, String> fuelReportMap = new HashMap<>();
                String vehiclePlateNumber = String.valueOf(row[2]); // Assuming row[2] is the plate number

                Float sum = totalAmountMap.get(vehiclePlateNumber);  // Get the pre-fetched total amount

                // Access vehicle details from the map
                Map<String, Object> vehicles = vehicleDetailsMap.get(vehiclePlateNumber);

                if (vehicles != null && !vehicles.isEmpty()) {
                    String vehicleType = String.valueOf(vehicles.get("vehicleType"));

                    String check = vehicleTypeRepository.getMonthAllowance(Integer.parseInt(vehicleType));
                    if (check != null) {
                        if (sum > Float.valueOf(check)) {
                            fuelReportMap.put("allowance", check);
                            fuelReportMap.put("vehtype", String.valueOf(vehicles.get("vehtype")));

                            Map<String, Object> assignment = assignmentMap.get(vehiclePlateNumber);
                            fuelReportMap.put("total_usage", String.valueOf(sum));
                            fuelReportMap.put("ActualAmount", String.valueOf(row[6]));
                            fuelReportMap.put("ProductName", String.valueOf(row[7]));
                            fuelReportMap.put("TotalAmount", String.valueOf(sum));
                            fuelReportMap.put("TransactionDate", String.valueOf(row[3]));
                            fuelReportMap.put("UnitPrice", String.valueOf(row[4]));
                            fuelReportMap.put("VatAmount", String.valueOf(row[9]));
                            fuelReportMap.put("VatRate", String.valueOf(row[8]));
                            fuelReportMap.put("VehiclePlateNumber", String.valueOf(row[2]));
                            fuelReportMap.put("Vendor", String.valueOf(row[1]));
                            fuelReportMap.put("Volume", String.valueOf(row[5]));
                            fuelReportMap.put("id", String.valueOf(row[0]));

                            // Populate fuel report with assignment details if available
                            if (assignment != null && !assignment.isEmpty()) {
                                fuelReportMap.put("driver_name", (String.valueOf(assignment.get("driverName")) +
                                        "[from: " + String.valueOf(assignment.get("fromDate")) + ", to: " +
                                        String.valueOf(assignment.get("toDate")) + " Branch: " +
                                        String.valueOf(assignment.get("branch")) + "] status: " +
                                        String.valueOf(assignment.get("status"))));
                                fuelReportMap.put("branch", String.valueOf(assignment.get("branch")));
                                fuelReportMap.put("driver_id", String.valueOf(assignment.get("driverId")));
                            } else {
                                fuelReportMap.put("driver_name", "");
                                fuelReportMap.put("branch", "");
                                fuelReportMap.put("driver_id", "");
                            }

                            // Add the fuel report to the list
                            fuelReports.add(fuelReportMap);
                        }
                    }
                }
            }
        }

        return fuelReports;
    }

    public List<Map<String, String>> getFuelReportByDriverFuel(String startDate, String endDate) {
        String startYear = startDate.split("-")[0];
        String endYear = endDate.split("-")[0];
        List<Map<String, String>> fuelReports = new ArrayList<>();
        List<Map<String, Object>> driverDetails = driverDetailsRepository.getDriverDetailsWithBranchStatus();

        if (driverDetails != null && !driverDetails.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            List<Integer> driverIds = new ArrayList<>();
            for (Map<String, Object> driver: driverDetails) {
                driverIds.add(Integer.parseInt(String.valueOf(driver.get("id"))));
            }

            List<Map<String, Object>> vehicleDetailsList = vehicleDetailsRepository.getVehiclesLimit4ForAllDrivers(LocalDate.parse(startDate), LocalDate.parse(endDate), driverIds);
            Map<String, List<Map<String, Object>>> driverVehicleMap = new HashMap<>();
            for (Map<String, Object> vehicle : vehicleDetailsList) {
                String driverId = String.valueOf(vehicle.get("driverId"));
                driverVehicleMap.computeIfAbsent(driverId, k -> new ArrayList<>()).add(vehicle);
            }

            // Fetch vehicle types and km details in bulk
            List<String> vehicleNumbers = new ArrayList<>();
            for (List<Map<String, Object>> vehicles : driverVehicleMap.values()) {
                for (Map<String, Object> vehicle : vehicles) {
                    vehicleNumbers.add(String.valueOf(vehicle.get("regNumber")));
                }
            }

            List<Map<String, Object>> vehicleTypeList = vehicleDetailsRepository.getVehicleTypes(vehicleNumbers);
            Map<String, String> vehicleTypeMap = new HashMap<>();
            for (Map<String, Object> vehicleType1 : vehicleTypeList) {
                vehicleTypeMap.put(String.valueOf(vehicleType1.get("regNumber")), String.valueOf(vehicleType1.get("monthAllowance")));
            }

            List<Map<String, Object>> vehicleKmList = kmDetailsRepository.getKmsForVehicles(LocalDate.parse(startDate), LocalDate.parse(endDate), vehicleNumbers);
            Map<String, String> vehicleKmMap = new HashMap<>();
            for (Map<String, Object> vehicleType2 : vehicleKmList) {
                vehicleKmMap.put(String.valueOf(vehicleType2.get("vehicleReg")), String.valueOf(vehicleType2.get("km")));
            }

            List<Map<String, Object>> vehicleFuelList = new ArrayList<>();
            if ("2021".equals(startYear)) {
                vehicleFuelList = fuelData2021Repository.getFuelDataForVehicles(LocalDate.parse(startDate), LocalDate.parse(endDate), vehicleNumbers);
            } else if ("2022".equals(startYear)) {
                vehicleFuelList = fuelData2022Repository.getFuelDataForVehicles(LocalDate.parse(startDate), LocalDate.parse(endDate), vehicleNumbers);
            } else if ("2023".equals(startYear)) {
                vehicleFuelList = fuelData2023Repository.getFuelDataForVehicles(LocalDate.parse(startDate), LocalDate.parse(endDate), vehicleNumbers);
            } else if ("2024".equals(startYear)) {
                vehicleFuelList = fuelData2024Repository.getFuelDataForVehicles(LocalDate.parse(startDate), LocalDate.parse(endDate), vehicleNumbers);
            } else if ("2025".equals(startYear)) {
                vehicleFuelList = fuelData2025Repository.getFuelDataForVehicles(LocalDate.parse(startDate), LocalDate.parse(endDate), vehicleNumbers);
            } else if ("2026".equals(startYear)) {
                vehicleFuelList = fuelData2026Repository.getFuelDataForVehicles(LocalDate.parse(startDate), LocalDate.parse(endDate), vehicleNumbers);
            }
            Map<String, String> vehicleFuelMap = new HashMap<>();
            for (Map<String, Object> vehicleType3 : vehicleFuelList) {
                vehicleFuelMap.put(String.valueOf(vehicleType3.get("vehicleReg")), String.valueOf(vehicleType3.get("total")));
            }

            for (Map<String, Object> driver : driverDetails) {
                String driverId = String.valueOf(driver.get("id"));
                Map<String, String> temp = new HashMap<>();
                temp.put("id", driverId);
                temp.put("driver_name", String.valueOf(driver.get("driver_name")));
                temp.put("branch", String.valueOf(driver.get("branch")));

                if (driverVehicleMap.containsKey(driverId)) {
                    for (Map<String, Object> vehicledetails : driverVehicleMap.get(driverId)) {
                        String vehicleRegNumber = String.valueOf(vehicledetails.get("regNumber"));

                        if (Integer.parseInt(String.valueOf(vehicledetails.get("status"))) == 0) {
                            String vehicleInfo = vehicleRegNumber + " ( " + vehicledetails.get("fromDate") + " to " + vehicledetails.get("toDate") + " ): " + "expired ";
                            temp.put("vehicle", vehicleInfo);
                        } else {
                            String vehicleInfo = vehicleRegNumber + " ( " + vehicledetails.get("fromDate") + " to " + vehicledetails.get("toDate") + " ): " + "active ";
                            temp.put("vehicle", vehicleInfo);
                        }

                        // Get month allowance
                        String monthAllowance = vehicleTypeMap.get(vehicleRegNumber);
                        temp.put("month_allowance", monthAllowance != null ? monthAllowance : "");

                        // Get km
                        String km = vehicleKmMap.get(vehicleRegNumber);
                        temp.put("km", km != null ? String.valueOf(Math.round(Double.parseDouble(km))) : "");

                        // Get fuel data
                        String totalFuel = vehicleFuelMap.get(vehicleRegNumber);
                        temp.put("fuel_total", totalFuel != null ? String.valueOf(Math.round(Double.parseDouble(totalFuel))) : "");
                        if (totalFuel != null && temp.get("month_allowance") != null) {
                            if (Double.parseDouble(totalFuel) > Integer.parseInt(temp.get("month_allowance"))) {
                                temp.put("usage_sts", "exceeded");
                            } else {
                                temp.put("usage_sts", "");
                            }
                        }
                    }
                }
                fuelReports.add(temp);
            }
        }
        return fuelReports;
    }

    public List<Map<String, String>> getKmFuelDriver(String startDate, String endDate) {
        String startYear = startDate.split("-")[0];
        String endYear = endDate.split("-")[0];
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

//            String month_prev = "", year_prev = "";
//            if ("01".equals(month)) {
//                month_prev = "12";
//                year_prev = String.valueOf(Integer.parseInt(year) - 1);
//            } else {
//                month_prev = String.valueOf(Integer.parseInt(month) - 1);
//                year_prev = year;
//            }
//
//            LocalDate prevStartDate = LocalDate.of(Integer.parseInt(year_prev), Integer.parseInt(month_prev), 1);
//            LocalDate prevEndDate = prevStartDate.withDayOfMonth(prevStartDate.lengthOfMonth());

            List<Map<String, Object>> assignments = vehicleAssignmentRepository.getDataWithDates2(LocalDate.parse(endDate), regNumbers);
//            List<Map<String, Object>> assignments2 = vehicleAssignmentRepository.getDataWithDates2(prevEndDate, regNumbers);

//            List<Map<String, Object>> kmDetails2 = kmDetailsRepository.getDataWithVehicles(year_prev, month_prev, regNumbers);

            List<Map<String, Object>> totalAmounts = new ArrayList<>();
            if ("2021".equals(startYear)) {
                totalAmounts = fuelData2021Repository.getTotalAmounts(regNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if ("2022".equals(startYear)) {
                totalAmounts = fuelData2022Repository.getTotalAmounts(regNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if ("2023".equals(startYear)) {
                totalAmounts = fuelData2023Repository.getTotalAmounts(regNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if ("2024".equals(startYear)) {
                totalAmounts = fuelData2024Repository.getTotalAmounts(regNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if ("2025".equals(startYear)) {
                totalAmounts = fuelData2025Repository.getTotalAmounts(regNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else if ("2026".equals(startYear)) {
                totalAmounts = fuelData2026Repository.getTotalAmounts(regNumbers, LocalDate.parse(startDate), LocalDate.parse(endDate));
            }

            Map<String, Float> totalAmountMap = new HashMap<>();
            for (int i = 0; i < totalAmounts.size(); i++) {
                String plateNumber = String.valueOf(totalAmounts.get(i).get("vehiclePlateNumber"));
                Float totalAmount = Float.valueOf(String.valueOf(totalAmounts.get(i).get("total_usage")));

                // Put the plate number and total amount into the map
                totalAmountMap.put(plateNumber, totalAmount);
            }

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

            // Create a map for fast lookup of km details
//            Map<String, Map<String, Object>> kmDetailsMap2 = kmDetails2.stream()
//                    .collect(Collectors.toMap(
//                            km -> String.valueOf(km.get("vehicle_reg")),
//                            km -> km,
//                            (existing, replacement) -> {
//                                // Define how to handle duplicates; for example, keep the existing one
//                                return existing; // or you can merge them in a way that makes sense for your use case
//                            }
//                    ));

            // Create a map for fast lookup of assignments
            Map<String, List<Map<String, Object>>> assignmentsMap = new HashMap<>();
            for (Map<String, Object> assignment : assignments) {
                String regNumber = String.valueOf(assignment.get("regNumber"));
                assignmentsMap.computeIfAbsent(regNumber, k -> new ArrayList<>()).add(assignment);
            }

//            Map<String, List<Map<String, Object>>> assignmentsMap2 = new HashMap<>();
//            for (Map<String, Object> assignment : assignments2) {
//                String regNumber = String.valueOf(assignment.get("regNumber"));
//                assignmentsMap2.computeIfAbsent(regNumber, k -> new ArrayList<>()).add(assignment);
//            }

            for (Map<String, Object> row : vehicleDetails) {
                Map<String, String> temp = new HashMap<>();
                String regNumber = String.valueOf(row.get("regNumber"));

                // Get the current month name
//                LocalDate currentDate = LocalDate.of(LocalDate.now().getYear(), Month.of(Integer.parseInt(month)), 1);
//                String currentMonthName = currentDate.format(DateTimeFormatter.ofPattern("MMMM"));
//                temp.put("select_month", currentMonthName);

                // Get the previous month name
//                LocalDate previousDate = LocalDate.of(LocalDate.now().getYear(), Month.of(Integer.parseInt(month_prev)), 1);
//                String previousMonthName = previousDate.format(DateTimeFormatter.ofPattern("MMMM"));
//                temp.put("prev_month", previousMonthName);

                Float sum = totalAmountMap.get(regNumber);
                if (sum != null) {
                    temp.put("total_usage", String.valueOf(sum));
                }

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

//                Map<String, Object> kmData2 = kmDetailsMap2.get(regNumber);
//                if (kmData2 != null) {
//                    temp.put("km2", String.valueOf(kmData2.get("km")));
//                    temp.put("km_updated_date2", String.valueOf(kmData2.get("updatedDate")));
//                    temp.put("km_status2", "recorded");
//                } else {
//                    temp.put("km_status2", "No Records");
//                }

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

//                List<Map<String, Object>> driverAssignments2 = assignmentsMap2.get(regNumber);
//                if (driverAssignments2 != null && !driverAssignments2.isEmpty()) {
//                    List<String> driverData = new ArrayList<>();
//                    for (Map<String, Object> assignment : driverAssignments2) {
//                        driverData.add(String.format("%s [from: %s, to: %s, Branch: %s] status: %s",
//                                assignment.get("driverName"), assignment.get("fromDate"), assignment.get("toDate"),
//                                assignment.get("branch"), assignment.get("status")));
//                    }
//                    temp.put("driver_name2", String.join("</br>", driverData));
//                    // Set branch if status is 1
//                    driverAssignments.stream()
//                            .filter(a -> Integer.parseInt(String.valueOf(a.get("status"))) == 1)
//                            .findFirst()
//                            .ifPresent(a -> temp.put("branch", String.valueOf(a.get("branch"))));
//                } else {
//                    temp.put("driver_name2", "");
//                }

                kmReports.add(temp);
            }
        }
        return kmReports;
    }
}
