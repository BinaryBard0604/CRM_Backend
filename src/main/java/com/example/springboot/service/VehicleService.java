package com.example.springboot.service;

import com.example.springboot.controller.DriverController;
import com.example.springboot.entity.*;
import com.example.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    private VehicleDetailsRepository vehicleDetailsRepository;

    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    private VehicleBrandRepository vehicleBrandRepository;

    @Autowired
    private VehicleNameRepository vehicleNameRepository;

    @Autowired
    private VehicleAssignmentRepository vehicleAssignmentRepository;

    @Autowired
    private DriverDetailsRepository driverDetailsRepository;

    @Autowired
    private KmDetailsRepository kmDetailsRepository;

    @Autowired
    private CustomerBranchRepository customerBranchRepository;

    private static final Logger logger = LoggerFactory.getLogger(VehicleService.class);

    public List<Map<String, String>> addVehicles(String reg_number, String veh_type, String veh_gear, String make, String vehicle_name, String model, String reg_emirate,
                                                 String plate_number, String purchase_type, String purchase_company, String initial_mileage, String mileage,
                                                 String rental_from, String rental_to, String lease_from, String lease_to, String replace_from, String replace_to,
                                                 String notes, String plan, String userid) {
        List<VehicleDetails> vehicleDetail1 = vehicleDetailsRepository.getVehicleDetailsByReg1(reg_number);
        String message = "";
        String status = "";
        String purchaseFrom = null;
        String purchaseTo = null;

        if (!vehicleDetail1.isEmpty()) {
            message = "Reg Number Already Existing!";
            status = "0";
        } else {
            List<VehicleDetails> vehicleDetail0 = vehicleDetailsRepository.getVehicleDetailsByReg0(reg_number);

            if (!vehicleDetail0.isEmpty()) {
                message = "Reg number exist in offloaded vehicle. Please restore the vehilce from offloaded vehicle";
                status = "0";
            } else {
                logger.info(">>>>>>>>>>>" + purchase_type);
//                try {
                    if (!"".equals(purchase_type) && purchase_type != null) {
                        if ("Rental".equals(purchase_type)) {
                            purchaseFrom = rental_from;
                            purchaseTo = rental_to;
                        } else if ("Lease".equals(purchase_type)) {
                            purchaseFrom = lease_from;
                            purchaseTo = lease_to;
                        } else {
                            purchaseFrom = replace_from;
                            purchaseTo = replace_to;
                        }
                    } else {
                        purchaseFrom = null;
                        purchaseTo = null;
                    }

                    List<KmDetails> kmDetailsList = kmDetailsRepository.getKmDetailsByKm(mileage, reg_number);

                    if (kmDetailsList.isEmpty()) {
                        KmDetails kmDetails = new KmDetails();
                        LocalDate today = LocalDate.now();

                        kmDetails.setVehicleReg(reg_number);
                        kmDetails.setKm(mileage);
                        kmDetails.setUpdatedBy(Integer.parseInt(userid));
                        kmDetails.setUpdatedDate(today);

                        kmDetailsRepository.save(kmDetails);
                    }

                    VehicleDetails vehicleDetails = new VehicleDetails();
                    LocalDate today = LocalDate.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    vehicleDetails.setVehicleType(Integer.parseInt(veh_type));
                    vehicleDetails.setMake(make);
                    vehicleDetails.setPlateNumber(plate_number);
                    vehicleDetails.setRegNumber(reg_number);
                    vehicleDetails.setRegEmirate(reg_emirate);
                    vehicleDetails.setVehicleName(vehicle_name);
                    vehicleDetails.setModel(model);
                    vehicleDetails.setInitialMileage(initial_mileage);
                    vehicleDetails.setMileage(mileage);
                    vehicleDetails.setPurchaseType(purchase_type);
                    vehicleDetails.setPurchaseCompany(purchase_company);
                    if (purchaseFrom == null) {
                        vehicleDetails.setPurchaseFrom(null);
                    } else {
                        vehicleDetails.setPurchaseFrom(LocalDate.parse(purchaseFrom, formatter));
                    }
                    if (purchaseTo == null) {
                        vehicleDetails.setPurchaseTo(null);
                    } else {
                        vehicleDetails.setPurchaseTo(LocalDate.parse(purchaseTo, formatter));
                    }
                    vehicleDetails.setNotes(notes);
                    vehicleDetails.setVehicleGear(veh_gear);
                    vehicleDetails.setPlan(plan);
                    vehicleDetails.setUpdatedBy(Integer.parseInt(userid));
                    vehicleDetails.setUpdatedDate(String.valueOf(today));

                    vehicleDetailsRepository.save(vehicleDetails);
                    message = "Vehicle added successfully";
                    status = "1";
//                } catch (Exception e) {
//                    logger.info(">>>>>>>>>>>>" + e.getMessage());
//                    message = "Add vehicle failed";
//                    status = "0";
//                }
            }
        }

        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status);

        // Add the map to the list
        result.add(map);
        return result;
    };

    public List<Map<String, String>> getVehicleType(String type) {
        return vehicleTypeRepository.getVehicleTypeByStatus();
    }

    public List<Map<String, String>> getBrandName(String brand_id) {
        return vehicleBrandRepository.getVehicleBrandByType(brand_id);
    }

    public List<Map<String, String>> getMakeName(String make_id) {
        return vehicleNameRepository.getMakeNameByBrand(make_id);
    }

    public List<Map<String, String>> editVehicle(String vehicle_id) {
        return vehicleDetailsRepository.getVehicleDetailsByVehicleId(Integer.parseInt(vehicle_id));
    }

    public List<Map<String, String>> updateVehicles(String reg_number, String veh_type, String veh_gear, String make, String vehicle_name, String model, String reg_emirate,
                                                 String plate_number, String purchase_type, String purchase_company, String initial_mileage, String mileage,
                                                 String rental_from, String rental_to, String lease_from, String lease_to, String replace_from, String replace_to,
                                                 String notes, String plan, String userid, String id) {
        List<VehicleDetails> vehicleDetail1 = vehicleDetailsRepository.getVehicleDetailsByReg1(reg_number);
        String message = "";
        String status = "";
        String purchaseFrom = "";
        String purchaseTo = "";

        if (Integer.parseInt(id) <= 0) {
            message = "Unable to update the details";
            status = "0";
        } else {
            try {
                if (!"".equals(purchase_type) && purchase_type != null) {
                    if ("Rental".equals(purchase_type)) {
                        purchaseFrom = rental_from;
                        purchaseTo = rental_to;
                    } else if ("Lease".equals(purchase_type)) {
                        purchaseFrom = lease_from;
                        purchaseTo = lease_to;
                    } else {
                        purchaseFrom = replace_from;
                        purchaseTo = replace_to;
                    }
                } else {
                    purchaseFrom = "";
                    purchaseTo = "";
                }

                List<KmDetails> kmDetailsList = kmDetailsRepository.getKmDetailsByKm(mileage, reg_number);

                if (kmDetailsList.isEmpty()) {
                    KmDetails kmDetails = new KmDetails();
                    LocalDate today = LocalDate.now();

                    kmDetails.setVehicleReg(reg_number);
                    kmDetails.setKm(mileage);
                    kmDetails.setUpdatedBy(Integer.parseInt(userid));
                    kmDetails.setUpdatedDate(today);

                    kmDetailsRepository.save(kmDetails);
                }

                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                String finalPurchaseFrom = purchaseFrom;
                String finalPurchaseTo = purchaseTo;

                vehicleDetailsRepository.findById(Long.parseLong(id)).map(existingItem -> {
                    existingItem.setVehicleType(Integer.parseInt(veh_type));
                    existingItem.setMake(make);
                    existingItem.setPlateNumber(plate_number);
                    existingItem.setRegNumber(reg_number);
                    existingItem.setRegEmirate(reg_emirate);
                    existingItem.setVehicleName(vehicle_name);
                    existingItem.setModel(model);
                    existingItem.setInitialMileage(initial_mileage);
                    existingItem.setMileage(mileage);
                    existingItem.setPurchaseType(purchase_type);
                    existingItem.setPurchaseCompany(purchase_company);
                    if (finalPurchaseFrom == "") {
                        existingItem.setPurchaseFrom(null);
                    } else {
                        existingItem.setPurchaseFrom(LocalDate.parse(finalPurchaseFrom, formatter));
                    }
                    if (finalPurchaseFrom == "") {
                        existingItem.setPurchaseTo(null);
                    } else {
                        existingItem.setPurchaseTo(LocalDate.parse(finalPurchaseTo, formatter));
                    }
                    existingItem.setNotes(notes);
                    existingItem.setVehicleGear(veh_gear);
                    existingItem.setPlan(plan);
                    existingItem.setUpdatedBy(Integer.parseInt(userid));
                    existingItem.setUpdatedDate(String.valueOf(today));
                    if ("".equals(finalPurchaseFrom)) {
                        existingItem.setPurchaseFrom(null);
                    } else {
                        existingItem.setPurchaseFrom(LocalDate.parse(finalPurchaseFrom));
                    }
                    if ("".equals(finalPurchaseTo)) {
                        existingItem.setPurchaseTo(null);
                    } else {
                        existingItem.setPurchaseTo(LocalDate.parse(finalPurchaseTo));
                    }

                    return vehicleDetailsRepository.save(existingItem);
                });

                message = "Vehicle Details Updated.";
                status = "1";
                logger.info(message);
            } catch (Exception e) {
                logger.error(e.getMessage());
                message = "Unable to update the details";
                status = "0";
            }
        }

        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status);

        // Add the map to the list
        result.add(map);
        return result;
    };

    public List<Map<String, String>> getVehicles(String branch, String user_role, String status, String type, String type2) {
        if ("all".equals(type2)) {
            branch = "0";
        }

        List<Map<String, ?>> vehicleDetailsList = new ArrayList<>();
        if (Integer.parseInt(branch) == 0 || Integer.parseInt(user_role) == 1) {
            vehicleDetailsList = vehicleDetailsRepository.findVehicleDetails();
        } else {
            vehicleDetailsList = vehicleDetailsRepository.findVehicleDetailsWithBranch(branch);
        }

        List<Map<String, String>> processedVehicles = new ArrayList<>();
        if (!vehicleDetailsList.isEmpty()) {
            // Fetch all vehicle assignments and km details in a single query
            List<VehicleAssignment> assignments = vehicleAssignmentRepository.findAllByVehicleIdsAndStatus(
                    vehicleDetailsList.stream()
                            .map(vehicle -> Integer.parseInt(String.valueOf(vehicle.get("id"))))
                            .collect(Collectors.toList()),
                    1
            );

            Map<Integer, VehicleAssignment> assignmentMap = assignments.stream()
                    .collect(Collectors.toMap(VehicleAssignment::getVehicleId, Function.identity()));

            List<KmDetails> kmDetailsList = kmDetailsRepository.findLatestByVehicleRegs(
                    vehicleDetailsList.stream()
                            .map(vehicle -> String.valueOf(vehicle.get("reg_number")))
                            .collect(Collectors.toList())
            );

            Map<String, KmDetails> kmDetailsMap = kmDetailsList.stream()
                    .collect(Collectors.toMap(
                            KmDetails::getVehicleReg,
                            Function.identity()
//                            (existing, replacement) -> replacement // Keep the latest entry
                    ));

            Integer serialNo = 1;
            for (Map<String, ?> vehicle : vehicleDetailsList) {
                String vehicleId = String.valueOf(vehicle.get("id"));
                LocalDate today = LocalDate.now();

                // Check if the vehicle is assigned
                VehicleAssignment assignment = assignmentMap.get(Integer.parseInt(vehicleId));
                String assignmentStatus = "Free";

                if (assignment != null) {
                    if (today.isAfter(LocalDate.parse(String.valueOf(vehicle.get("to_date"))))) {
                        assignmentStatus = "Expired";
                    } else {
                        assignmentStatus = "Active";
                    }
                }

                // Add assignment status and serial number
                Map<String, String> processedVehicle = new HashMap<>();
                processedVehicle.put("assignment_sts", assignmentStatus);
                processedVehicle.put("serial_no", String.valueOf(serialNo));

                if (assignment != null && vehicle.get("driverName") != null && vehicle.get("driverName") != "") {
                    processedVehicle.put("drivername", vehicle.get("driverName") + " [" + assignment.getFromDate() + " " + assignment.getFromTime() + "]");
                } else {
                    processedVehicle.put("drivername", "");
                }

                KmDetails kmDetails = kmDetailsMap.get(String.valueOf(vehicle.get("reg_number")));
                if (kmDetails != null) {
                    processedVehicle.put("mileage", kmDetails.getKm() + " (" + kmDetails.getUpdatedDate() + ")");
                } else {
                    processedVehicle.put("mileage", "");
                }

                // Populate other fields
                processedVehicle.put("branch", String.valueOf(vehicle.get("branch")));
                processedVehicle.put("branch_id", String.valueOf(vehicle.get("branchId")));
                processedVehicle.put("branchid", String.valueOf(vehicle.get("branchid")));
                processedVehicle.put("brand", String.valueOf(vehicle.get("brand")));
                processedVehicle.put("driver_id", String.valueOf(vehicle.get("driver_id")));
                processedVehicle.put("from_date", String.valueOf(vehicle.get("from_date")));
                processedVehicle.put("id", String.valueOf(vehicle.get("id")));
                processedVehicle.put("make", String.valueOf(vehicle.get("make")));
                processedVehicle.put("plate_number", String.valueOf(vehicle.get("plate_number")));
                processedVehicle.put("purchase_to", String.valueOf(vehicle.get("purchase_to")));
                processedVehicle.put("reg_emirate", String.valueOf(vehicle.get("reg_emirate")));
                processedVehicle.put("reg_number", String.valueOf(vehicle.get("reg_number")));
                processedVehicle.put("status", String.valueOf(vehicle.get("status")));
                processedVehicle.put("to_date", String.valueOf(vehicle.get("to_date")));
                processedVehicle.put("to_time", String.valueOf(vehicle.get("from_time")));
                processedVehicle.put("vehicle_name", String.valueOf(vehicle.get("vehicle_name")));

                processedVehicles.add(processedVehicle);
                serialNo++;
            }
        }

        return processedVehicles;
    }

    public List<Map<String, String>> getVehicleTypes() {
        return vehicleTypeRepository.getVehicleTypeByStatus();
    }

    public List<Map<String, String>> getVehicleName() {
        return vehicleNameRepository.getVehiceNameByStatus();
    }

    public List<Map<String, String>> getOffVehicles() {
        List<VehicleDetails> vehicles = vehicleDetailsRepository.findOffVehicles();

        // Fetch all assignments for the off vehicles in one query
        List<Map<String, Object>> allAssignments = vehicleAssignmentRepository.findAssignmentsByVehicleIds(
                vehicles.stream().map(VehicleDetails::getId).collect(Collectors.toList())
        );

        // Group assignments by vehicleId
        Map<Integer, List<Map<String, Object>>> assignmentsByVehicleId = allAssignments.stream()
                .collect(Collectors.groupingBy(a -> (Integer) a.get("vehicleId")));

        List<Map<String, String>> vehicleData = new ArrayList<>();
        for (VehicleDetails vehicle : vehicles) {
            Map<String, String> vehicleMap = new HashMap<>();
            vehicleMap.put("id", String.valueOf(vehicle.getId()));
            vehicleMap.put("reg_number", vehicle.getRegNumber());
            vehicleMap.put("plate_number", vehicle.getPlateNumber());
            vehicleMap.put("status", String.valueOf(vehicle.getStatus()));
            vehicleMap.put("notes", vehicle.getNotes());

            // Get assignments for this vehicle
            List<Map<String, Object>> assignments = assignmentsByVehicleId.getOrDefault(vehicle.getId(), Collections.emptyList());
            if (!assignments.isEmpty()) {
                Map<String, Object> latestAssignment = assignments.get(0); // Get the latest assignment

                vehicleMap.put("driver", String.valueOf(latestAssignment.get("driver")));
                vehicleMap.put("from_date", formatDateTime(latestAssignment.get("fromDate"), latestAssignment.get("fromTime")));
                vehicleMap.put("to_date", formatDateTime(latestAssignment.get("toDate"), latestAssignment.get("toTime")));
                vehicleMap.put("driver_notes", String.valueOf(latestAssignment.get("notes")));
                vehicleMap.put("branch", String.valueOf(latestAssignment.get("branch")));
            } else {
                vehicleMap.put("driver", "");
                vehicleMap.put("from_date", "");
                vehicleMap.put("to_date", "");
                vehicleMap.put("driver_notes", "");
                vehicleMap.put("branch", "");
            }

            vehicleData.add(vehicleMap);
        }

        return vehicleData;
    }

    private String formatDateTime(Object date, Object time) {
        if (date == null || time == null) return "";
        return date + " " + time;
    }

    public List<Map<String, String>> getVehicleActionOld() {
        List<Object[]> results = vehicleDetailsRepository.findFilteredVehicles();

        List<Map<String, String>> vehicles = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, String> vehicleMap = new HashMap<>();
            vehicleMap.put("id", String.valueOf(row[0]));
            vehicleMap.put("reg_number", String.valueOf(row[2]));
            vehicleMap.put("reg_emirate", String.valueOf(row[3]));
            vehicleMap.put("make", String.valueOf(row[4]));
            vehicleMap.put("plate_number", String.valueOf(row[5]));
            vehicleMap.put("initial_mileage", String.valueOf(row[6]));
            vehicleMap.put("mileage", String.valueOf(row[7]));
            vehicleMap.put("driver_name", String.valueOf(row[8]));
            vehicleMap.put("branch_id", String.valueOf(row[9]));
            vehicleMap.put("branch", String.valueOf(row[10]));
            vehicleMap.put("purchase_to", String.valueOf(row[11]));
            vehicleMap.put("driver_id", String.valueOf(row[12]));
            vehicleMap.put("assignsts", String.valueOf(row[13]));
            vehicleMap.put("branchid", String.valueOf(row[14]));
            vehicleMap.put("from_date", String.valueOf(row[15]));
            vehicleMap.put("from_time", String.valueOf(row[16]));
            vehicleMap.put("to_date", String.valueOf(row[17]));
            vehicleMap.put("to_time", String.valueOf(row[18]));
            vehicleMap.put("brand", String.valueOf(row[19]));
            vehicleMap.put("plan", String.valueOf(row[20]));

            // Add custom fields
            Integer mileage = Integer.parseInt(String.valueOf(row[7]));
            if (mileage >= 185000 && mileage < 210000) {
                vehicleMap.put("comment", "over 185000");
            } else if (mileage >= 210000) {
                vehicleMap.put("comment", "over 210000");
            }

            String assignStatus = String.valueOf(row[13]);
            if (row[13] == null) {
                vehicleMap.put("status", "FREE");
            } else {
                vehicleMap.put("status", Integer.parseInt(assignStatus) == 1 ? "Active" : "FREE");
            }

            vehicleMap.put("vehicle_name", String.valueOf(row[19]) + " " + String.valueOf(String.valueOf(row[1])));

            Integer initialMileage = Integer.parseInt(String.valueOf(row[6]).trim());
            vehicleMap.put("difference", String.valueOf(mileage - initialMileage));

            vehicles.add(vehicleMap);
        }
        return vehicles;
    }

    public List<Map<String, String>> deleteVehicle(String record, String user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String formattedToday = today.format(formatter);

        int rowsUpdated = vehicleDetailsRepository.updateVehicleStatus(Integer.parseInt(record), Integer.parseInt(user), String.valueOf(formattedToday));

        // Prepare the response
        if (rowsUpdated > 0) {
            return List.of(Map.of("msg", "Delete successfully"));
        } else {
            return List.of(Map.of("msg", "No record found to delete"));
        }
    }

    public List<Map<String, String>> createVehicleType(String veh_type, String km_allowance, String fuel_allowance) {
        VehicleType vehicleType = new VehicleType();

        try {
            vehicleType.setVehicleType(veh_type);
            vehicleType.setKmAllowance(km_allowance);
            vehicleType.setMonthAllowance(fuel_allowance);
            vehicleType.setStatus(1);
            vehicleTypeRepository.save(vehicleType);

            return List.of(Map.of("message", "Vehicle Type added successfully", "allowed", "0"));
        } catch (Exception e) {
            return List.of(Map.of("message", "Error Adding Vehicle type. Please try again",  "allowed", "1"));
        }
    }

    public List<Map<String, String>> createVehicleName(String veh_name, String brandId) {
        VehicleName vehicleName = new VehicleName();

        try {
            vehicleName.setBrandId(Integer.parseInt(brandId));
            vehicleName.setVehicleName(veh_name);
            vehicleName.setStatus(1);
            vehicleNameRepository.save(vehicleName);

            return List.of(Map.of("message", "Vehicle Name added successfully", "allowed", "0"));
        } catch (Exception e) {
            return List.of(Map.of("message", "Error Adding Vehicle Name. Please try again",  "allowed", "1"));
        }
    }

    public List<Map<String, String>> updateVehicleName(String id, String veh_name, String brandId) {
        try {
            vehicleNameRepository.updateVehicleNameWithData(Integer.parseInt(id), veh_name, Integer.parseInt(brandId));

            return List.of(Map.of("message", "Vehicle Name Updated successfully", "allowed", "0"));
        } catch (Exception e) {
            return List.of(Map.of("message", "Error Updating Vehicle Name. Please try again",  "allowed", "1"));
        }
    }

    public List<Map<String, String>> updateVehicleType(String id, String veh_type, String km_allowance, String fuel_allowance) {
        try {
            vehicleTypeRepository.updateVehicleTypeWithData(Integer.parseInt(id), veh_type, km_allowance, fuel_allowance);

            return List.of(Map.of("message", "Vehicle Type Updated successfully", "allowed", "0"));
        } catch (Exception e) {
            return List.of(Map.of("message", "Error Updating Vehicle type. Please try again",  "allowed", "1"));
        }
    }

    public List<Map<String, String>> deleteVehicleType(String record) {
        int rowsUpdated = vehicleTypeRepository.updateVehicleType(Integer.parseInt(record));

        // Prepare the response
        if (rowsUpdated > 0) {
            return List.of(Map.of("msg", "Delete successfully"));
        } else {
            return List.of(Map.of("msg", "No record found to delete"));
        }
    }

    public List<Map<String, String>> deleteVehicleName(String record) {
        int rowsUpdated = vehicleNameRepository.updateVehicleName(Integer.parseInt(record));

        // Prepare the response
        if (rowsUpdated > 0) {
            return List.of(Map.of("msg", "Delete successfully"));
        } else {
            return List.of(Map.of("msg", "No record found to delete"));
        }
    }

    public List<Map<String, String>> getVehicleTypeDetail(String vehicletypeId) {
        return vehicleTypeRepository.getVehicleTypeDetail(Integer.parseInt(vehicletypeId));
    }

    public List<Map<String, String>> getVehicleNameDetail(String vehicleNameId) {
        return vehicleNameRepository.getVehicleNameDetail(Integer.parseInt(vehicleNameId));
    }

    public List<Map<String, String>>  restoreVehicle(String user, String record) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        String formattedToday = today.format(formatter);

        try {
            Integer update1 = vehicleDetailsRepository.restore(user, Integer.parseInt(record), formattedToday);
            Integer update2 = vehicleAssignmentRepository.restoreVehicleAssignment(user, Integer.parseInt(record), today);

            if (update1 > 0 && update2 > 0) {
                return List.of(Map.of("msg1", "Vehicle restore successfully", "msg2", "Vehicle Assignment restore successfully"));
            } else if (update1 > 0 && update2 == 0) {
                return List.of(Map.of("msg1", "Vehicle restore successfully", "msg2", "Vehicle Assignment recovery failed"));
            } else if (update1 == 0 && update2 > 0) {
                return List.of(Map.of("msg1", "Vehicle recovery failed", "msg2", "Vehicle Assignment restore successfully"));
            } else {
                return List.of(Map.of("msg1", "Vehicle recovery failed", "msg2", "Vehicle Assignment recovery failed"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, String>> searchVehicle(String searchData) {
        List<Map<String, Object>> searchVehicleData = vehicleAssignmentRepository.searchVehicle(searchData);
        List<Map<String, String>> result = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

        if (searchVehicleData != null && !searchVehicleData.isEmpty()) {
            for (Map<String, Object> row : searchVehicleData) {
                Map<String, String> temp = new HashMap<>();

//                LocalDate fromDate = (row.get("fromDate") == null) ? null : LocalDate.parse(String.valueOf(row.get("fromDate")));
//                LocalDate fromTime = (row.get("fromTime") == null) ? null : LocalDate.parse(String.valueOf(row.get("fromTime")));
//                LocalDate toDate = (row.get("toDate") == null) ? null : LocalDate.parse(String.valueOf(row.get("toDate")));
//                LocalDate toTime = (row.get("toTime") == null) ? null : LocalDate.parse(String.valueOf(row.get("toTime")));
//                temp.put("from_date", fromDate.format(dateFormatter) + " " + fromTime.format(timeFormatter));
//                temp.put("to_date", toDate.format(dateFormatter) + " " + toTime.format(timeFormatter));
                temp.put("driver_id", String.valueOf(row.get("driverId")));
                temp.put("branch", String.valueOf(row.get("branch")));
                temp.put("notes", String.valueOf(row.get("notes")));
                temp.put("driver_name", String.valueOf(row.get("driverName")));
                temp.put("vehicle_id", String.valueOf(row.get("vehicleId")));
                temp.put("from_date", String.valueOf(row.get("fromDate")));
                temp.put("to_date", String.valueOf(row.get("toDate")));
                temp.put("from_time", String.valueOf(row.get("fromTime")));
                temp.put("to_time", String.valueOf(row.get("toTime")));
                temp.put("plate_number", String.valueOf(row.get("plateNumber")));
                temp.put("status", String.valueOf(row.get("status")));
                temp.put("created_at", String.valueOf(row.get("createdAt")));
                temp.put("vehicle_notes", String.valueOf(row.get("vehicleNotes")));
                result.add(temp);
            }
        }

        return result;
    }

    public Map<String, List<Map<String, String>>> getVehicleDetails(String vehicleId) {
        Map<String, List<Map<String, String>>> result = new HashMap<>();

        List<Map<String, String>> vehicle_details = vehicleDetailsRepository.getVehicleDetailsData(Integer.parseInt(vehicleId));
        List<Map<String, String>> assign_details = vehicleDetailsRepository.getAssignDetails(Integer.parseInt(vehicleId));
        List<Map<String, String>> drivers = driverDetailsRepository.getDrivers();
        List<Map<String, String>> branches = customerBranchRepository.getBranch();

        result.put("vehicle_details", vehicle_details);
        result.put("assign_details", assign_details);
        result.put("drivers", drivers);
        result.put("branches", branches);

        return result;
    }

    public Map<String, List<Map<String, String>>> getBranchVehicleAction(String driverId, String vehicleId) {
        Map<String, List<Map<String, String>>> result = new HashMap<>();

        List<Map<String, String>> branch_details = driverDetailsRepository.getBranchDetails(Integer.parseInt(driverId));

        List<Map<String, ?>> check = vehicleAssignmentRepository.getDriverId(Integer.parseInt(driverId), Integer.parseInt(vehicleId));
        Map<String, String> result1 = new HashMap<>();
        Map<String, String> result2 = new HashMap<>();
        if (!check.isEmpty() && check != null) {
            result1.put("result", "0");
            result2.put("result", "Please close the existing vehicle assignment before assigning to a new driver");
        } else {
            List<Map<String, ?>> check2 = vehicleAssignmentRepository.getData(Integer.parseInt(driverId), Integer.parseInt(vehicleId));

            if (!check2.isEmpty() && check2 != null) {
                String driverName = String.valueOf(check2.get(0).get("drivername"));

                result1.put("result", "0");
                result2.put("result", driverName + " Assigned with another vehicle. Please close the driver current assignment before assigning new vehicle");
            } else {
                result1.put("result", "1");
                result2.put("result", "");
            }
        }

        List<Map<String, String>> allowed = new ArrayList<>();
        List<Map<String, String>> message = new ArrayList<>();

        allowed.add(result1);
        message.add(result2);

        result.put("branch_details", branch_details);
        result.put("allowed", allowed);
        result.put("message", message);

        return result;
    }

    public List<Map<String, String>> getVehicleTypeList() {
        return vehicleTypeRepository.getList();
    }

    public List<Map<String, String>> getBrandList() {
        return vehicleBrandRepository.getBrandList();
    }
}
