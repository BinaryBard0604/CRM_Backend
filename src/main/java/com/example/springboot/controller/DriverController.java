package com.example.springboot.controller;

import com.example.springboot.dto.DriverDetailsDTO;
import com.example.springboot.dto.DriverSearchRequest;
import com.example.springboot.dto.DriverUpdateRequest;
import com.example.springboot.entity.DriverFinalSettlement;
import com.example.springboot.entity.DriverVacation;
import com.example.springboot.entity.VehicleAssignment;
import com.example.springboot.repository.DriverFinalSettlementRepository;
import com.example.springboot.repository.DriverVacationRepository;
import com.example.springboot.service.DriverService;
import com.example.springboot.entity.DriverDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DriverController {

    private final String UPLOAD_DIR_BASIC = "/home/ubuntu/uploads/";

    @Autowired
    private DriverService driverService; // Service layer for business logic

    @Autowired
    private DriverVacationRepository driverVacationRepository;

    @Autowired
    private DriverFinalSettlementRepository driverFinalSettlementRepository;

    private static final Logger logger = LoggerFactory.getLogger(DriverController.class);

    @PostMapping("/addDriver")
    public ResponseEntity<?> addDriver(@RequestBody Map<String, String> payload) {
        try {
            String user = payload.get("user");
            String branch = payload.get("branch");
            String first_name = payload.get("first_name");
            String last_name = payload.get("last_name");
            String common_name = payload.get("common_name");
            String employee_number = payload.get("employee_number");
            String mobile_number = payload.get("mobile_number");
            String emp_date = payload.get("emp_date");
            String citizenship = payload.get("citizenship");
            String passport_number = payload.get("passport_number");
            String passport_expiry = payload.get("passport_expiry");
            String license_type = payload.get("license_type");
            String license_expiry = payload.get("license_expiry");
            String change_date = payload.get("change_date");
            String work_permit_number = payload.get("work_permit_number");
            String work_permit_expiry = payload.get("work_permit_expiry");
            String dob = payload.get("dob");
            String eid_number = payload.get("eid_number");
            String license_number = payload.get("license_number");
            String visa_type = payload.get("visa_type");
            String own_vehicle = payload.get("own_vehicle");
            String home_contact = payload.get("home_contact");
            String home_contact_no = payload.get("home_contact_no");
            String res_address = payload.get("res_address");;
            String res_city = payload.get("res_city");
            String ctc = payload.get("ctc");
            String cpk = payload.get("cpk");
            String notes = payload.get("notes");

            LocalDate currentDate = LocalDate.now();

            DriverDetails driverData = new DriverDetails();
            driverData.setBranch(branch);
            driverData.setFirstName(first_name);
            driverData.setLastName(last_name);
            driverData.setCommonName(common_name);
            driverData.setEmployeeNumber(employee_number);
            driverData.setPhone(mobile_number);
            driverData.setHomeContact(home_contact);
            driverData.setHomeContactNo(home_contact_no);
            if (emp_date == null || emp_date == "") {
                driverData.setDateEmployed(null);
            } else {
                driverData.setDateEmployed(LocalDate.parse(emp_date));
            }
            driverData.setCitizenship(citizenship);
            driverData.setPassportNumber(passport_number);
            driverData.setChangeDate(change_date);
            driverData.setLicenseType(license_type);
            if (license_expiry == null || license_expiry == "") {
                driverData.setLicenseExpiryDate(null);
            } else {
                driverData.setLicenseExpiryDate(LocalDate.parse(license_expiry));
            }
            if (passport_expiry == null || passport_expiry == "") {
                driverData.setPdrpExpiryDate(null);
            } else {
                driverData.setPdrpExpiryDate(LocalDate.parse(passport_expiry));
            }
            driverData.setWorkPermitNumber(work_permit_number);
            if (work_permit_expiry == null || work_permit_expiry == "") {
                driverData.setWorkPermitExpiry(null);
            } else {
                driverData.setWorkPermitExpiry(LocalDate.parse(work_permit_expiry));
            }
            driverData.setCtc(ctc);
            driverData.setCpk(cpk);
            driverData.setResAddress(res_address);
            driverData.setResCity(res_city);
            driverData.setNotes(notes);
            driverData.setVisaType(visa_type);
            driverData.setOwnVehicle(own_vehicle);
            if (dob == null || dob == "") {
                driverData.setDob(null);
            } else {
                driverData.setDob(LocalDate.parse(dob));
            }
            driverData.setEmiratesIdNo(eid_number);
            driverData.setLicenseNo(license_number);
            driverData.setStatus(1);
            driverData.setUpdatedBy(Integer.parseInt(user));
            driverData.setUpdatedDate(currentDate);

            // Call the service to save the driver
            int driverId = driverService.saveDriver(driverData);

            // Return success response with the new driver's ID
            return ResponseEntity.ok(Map.of("success", true, "status", driverId));
        } catch (Exception e) {
            // Handle errors and return a failure response
            logger.info(">>>>>>>>>>>" + e.getMessage());
            return ResponseEntity.badRequest().body(Map.of("success", false, "status", 0, "msg", e.getMessage()));
        }
    }

    @PostMapping("/getDrivers")
    public ResponseEntity<List<Map<String, Object>>> getDrivers(@RequestBody DriverSearchRequest payload) {
        String branch = payload.getBranch();
        String user_role = payload.getUser_role();
        String status1 = payload.getStatus();
        String type = payload.getType();

        int userBranch = Integer.parseInt(branch);
        int userRole = Integer.parseInt(user_role);
        int status = Integer.parseInt(status1);

        if ("search".equals(type)) {
            Map<String, String> searchData = payload.getSearchdata();;
            List<DriverDetailsDTO> drivers = driverService.searchDrivers(status, userBranch, userRole, searchData);
            List<Map<String, Object>> updateDrivers = driverService.updateDriverStatus(drivers);
            return ResponseEntity.ok(updateDrivers);
        } else {
            List<DriverDetailsDTO> drivers = driverService.getDrivers(status, userBranch, userRole);
            List<Map<String, Object>> updateDrivers = driverService.updateDriverStatus(drivers);
            return ResponseEntity.ok(updateDrivers);
       }
    }

    @PostMapping("/getDriverData")
    public Map<String, ?> getDriverData(@RequestBody Map<String, String> request) {
        String driver_id = request.get("driverId");
        Long driverId = Long.parseLong(driver_id);

        return driverService.getDriverData(driverId);
    }

    @PostMapping("/getDriverVisaDetails")
    public Map<String, String> getDriverVisaDetails(@RequestBody Map<String, String> request) {
        String driver_id = request.get("driverId");
        Integer driverId = Integer.parseInt(driver_id);

        return driverService.getDriverDetails(driverId);
    }

    @PostMapping("/userActions")
    public String deleteDriver(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");
        logger.info(type);
        String user = payload.get("user");
        String result = "";

        if ("delete".equals(type)) {
            String record = payload.get("record");
            driverService.deleteDriverDetails(user, record, type);

            result = "Driver Action successfully";
        }

        if ("delete_leave".equals(type)) {
            String record = payload.get("record");
            driverService.deleteDriverVacation(user, record, type);

            result = "Driver Action successfully";
        }

        if ("updateleave".equals(type)) {
            String driverId = payload.get("driver_id");
            String leave_from = payload.get("leave_from");
            String leave_to = payload.get("leave_to");
            String leave_type = payload.get("leave_type");
            String notes = payload.get("notes");

            Integer updatedId = driverService.updateLeave(user, driverId, leave_from, leave_to, leave_type, notes);

            result = String.valueOf(updatedId);
        }

        if ("apply_visa".equals(type)) {
            String driver_name = payload.get("driver_name");
            String visa_type = payload.get("visa_type");
            String date_employed = payload.get("date_employed");
            String branch = payload.get("branch");
            String passport_number = payload.get("passport_number");
            String phone = payload.get("phone");
            String notes = payload.get("notes");
            String applied_for = payload.get("applied_for");
            String driver = payload.get("driver");
            String updatedId = driverService.applyVisa(user, driver_name, visa_type, date_employed, branch, passport_number, phone, driver, notes, applied_for);

            result = updatedId;
        }

        if ("settle".equals(type)) {
            String driverId = payload.get("driver");
            String resign_date = payload.get("resign_date");
            String cancel_date = payload.get("cancel_date");
            String type_of_leaving = payload.get("type_of_leaving");
            String notes = payload.get("notes");
            String reason = payload.get("reason");
            String updatedId = driverService.settle(user, driverId, resign_date, cancel_date, type_of_leaving, notes, reason);

            result = updatedId;
        }

        return result;
    }

    @PostMapping("/vacationFileUpload")
    public ResponseEntity<?> vacationFileUpload(@RequestParam("vacationid") String vacationId,
                                     @RequestParam("passportno") String passportno,
                                     @RequestParam(value = "leave_form", required = false) MultipartFile[] leaveForms,
                                     @RequestParam(value = "leaveform2", required = false) MultipartFile[] leaveForms2,
                                     @RequestParam(value = "settlement", required = false) MultipartFile[] settlements,
                                     @RequestParam(value = "settlementform2", required = false) MultipartFile[] settlementform2) {

        try {
            Long vacation_id = Long.parseLong(vacationId);
            DriverVacation vacation = driverVacationRepository.findById(vacation_id).orElseThrow(() -> new RuntimeException("Vacation not found"));
            String passportNumber = passportno.replace("-", "").replace(" ", "");

            // Process leave forms
            if (leaveForms != null && leaveForms.length > 0) {
                if (leaveForms2 != null && leaveForms2.length > 0) {
                    for (MultipartFile leaveForm : leaveForms2) {
                        String leaveFormPath = DriverService.uploadFile(leaveForm, passportNumber + "/leave_applications/");
                        vacation.setLeaveForm(leaveFormPath);
                    }
                } else {
                    for (MultipartFile leaveForm : leaveForms) {
                        String leaveFormPath = DriverService.uploadFile(leaveForm, passportNumber + "/leave_applications/");
                        vacation.setLeaveForm(leaveFormPath);
                    }
                }
            }

            // Process settlements
            if (settlements != null && settlements.length > 0) {
                if (settlementform2 != null && settlementform2.length > 0) {
                    for (MultipartFile settlement : settlementform2) {
                        String settlementPath = DriverService.uploadFile(settlement, passportNumber + "/leave_applications/Settlements/");
                        vacation.setSettlementForm(settlementPath);
                    }
                } else {
                    for (MultipartFile settlement : settlements) {
                        String settlementPath = DriverService.uploadFile(settlement, passportNumber + "/leave_applications/Settlements/");
                        vacation.setSettlementForm(settlementPath);
                    }
                }
            }

            driverVacationRepository.save(vacation);

            return ResponseEntity.ok("Files uploaded and vacation updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading files: " + e.getMessage());
        }
    }

    @PostMapping("/getDriverVehicles")
    public List<Map<String, ?>> getVehicleDetails(@RequestBody Map<String, String> payload) {
        String driverId = payload.get("driverId");

        return driverService.getVehicleDetailsByDriverId(Long.parseLong(driverId));
    }

    @PostMapping("/getDriverLeaves")
    public List<Map<String, ?>> getDriverLeaves(@RequestBody Map<String, String> payload) {
        String driverId = payload.get("driverId");

        return driverService.getDriverLeavesByDriverId(Long.parseLong(driverId));
    }

    @PostMapping("/basicDetails")
    public List<Map<String, ?>> getBasicDetails(@RequestBody Map<String, String> request) {
        String type = request.get("type");

        if ("driver".equals(type)) {
            String driverId = request.get("driverid");

            return driverService.getBasicDetailsWithDriver(type, driverId);
        } else if ("staff".equals(type)) {
            String staffId = request.get("staffid");

            return driverService.getBasicStaffDetails(staffId);
        } else if ("staff_vacation".equals(type)) {
            String staffId = request.get("staffid");

            return driverService.getStaffVacationWithStaffId(staffId);
        } else if ("close_staff_leave".equals(type)) {
            String staffId = request.get("staffid");
            String rejoinDate = request.get("rejoindate");
            String vacationId = request.get("vacationid");
            String user = request.get("user");

            return driverService.closeStaffLeave(staffId, rejoinDate, vacationId, user);
        } else if ("driver_vacation".equals(type)) {
            String driverId = request.get("driverid");

            return driverService.getDriverVacationWithDriverId(driverId);
        } else if ("close_driver_leave".equals(type)) {
            String driverId = request.get("driverid");
            String rejoinDate = request.get("rejoindate");
            String vacationId = request.get("vacationid");
            String user = request.get("user");
            String notes = request.get("rejoinnotes");

            return driverService.closeDriverLeaves(driverId, rejoinDate, vacationId, user, notes);
        } else {
            return driverService. getBasicDetailsWithDriver(type, "1");
        }
    }

    @PostMapping("/updateDriver")
    public Map<String, ?> updateDriver(@RequestBody DriverUpdateRequest request) {
        return driverService.updateDriver(request.getUser(), request.getDriverData(), request.getDriverId());
    }

    @PostMapping("/getSettlementDetails")
    public Map<String, Object> getSettlementDetails(@RequestBody Map<String, String> request) {
        String driver_id = request.get("driverId");
        Map<String, Object> response = new HashMap<>();

        Long driverId = Long.parseLong(driver_id);
        // Fetch Driver Final Settlement Details
        Optional<DriverFinalSettlement> settlementDetails = driverService.getDriverFinalSettlementByDriverId(driverId);
        if (settlementDetails.isEmpty()) {
            response.put("settlementdetails", null);
        } else {
            Map<String, String> result = new HashMap<>();
            DriverFinalSettlement settlement = settlementDetails.get();

            result.put("id", String.valueOf(settlement.getId()));
            result.put("driver_id", String.valueOf(settlement.getDriverId()));
            result.put("resign_date", String.valueOf(settlement.getResignDate()));
            result.put("cancel_date", String.valueOf(settlement.getCancelDate()));
            result.put("type_of_leaving", String.valueOf(settlement.getTypeOfLeaving()));
            result.put("settle_form", String.valueOf(settlement.getSettleForm()));
            result.put("cancel_form", String.valueOf(settlement.getCancelForm()));
            result.put("resign_form", String.valueOf(settlement.getResignForm()));
            result.put("notes", String.valueOf(settlement.getNotes()));
            result.put("reason", String.valueOf(settlement.getReason()));
            result.put("updated_by", String.valueOf(settlement.getUpdatedBy()));
            result.put("updated_date", String.valueOf(settlement.getUpdatedDate()));

            response.put("settlementdetails", result);
        }

        // Fetch Driver Join Details
        Optional<DriverDetails> joinDetails = driverService.getDriverDetailsById(driverId);
        if (joinDetails.isEmpty()) {
            response.put("joindetails", null);
        } else {
            Map<String, String> result = new HashMap<>();
            DriverDetails driverDetails = joinDetails.get();

            result.put("id", String.valueOf(driverDetails.getId()));
            result.put("driver_name", driverDetails.getFirstName() + " " + driverDetails.getLastName());
            result.put("passport_number", String.valueOf(driverDetails.getPassportNumber()));
            result.put("change_date", String.valueOf(driverDetails.getChangeDate()));
            result.put("date_employed", String.valueOf(driverDetails.getDateEmployed()));

            response.put("joindetails", result);
        }

        return response;
    }

    @PostMapping("/settleFileUpload")
    public ResponseEntity<?> settleFileUpload(
            @RequestParam("settleid") Long settleid,
            @RequestParam("passportno") String passportNumber,
            @RequestParam(value = "settle_form", required = false) MultipartFile[] settleForm,
            @RequestParam(value = "resign_form", required = false) MultipartFile[] resignForm,
            @RequestParam(value = "cancel_form", required = false) MultipartFile[] cancelForm,
            @RequestParam(value = "settle_form2", required = false) String settleForm1,
            @RequestParam(value = "resign_form2", required = false) String resignForm1,
            @RequestParam(value = "cancel_form2", required = false) String cancelForm1) throws IOException {

        Map<String, String> filePaths = new HashMap<>();
        int errorCount = 0;

//        try {
        // Clean passport number (replace '-' and spaces)
        passportNumber = passportNumber.replace("-", "").replace(" ", "");

        // Handle "add" or "edit" logic
        filePaths.put("settle_form", null);
        filePaths.put("resign_form", null);
        filePaths.put("cancel_form", null);

        // Save files for each type
        if (settleForm != null) {
            filePaths.put("settle_form", saveFiles(settleForm, passportNumber, "photo"));
        } else {
            if (settleForm1 != "" && settleForm1 != null) {
                filePaths.put("settle_form", settleForm1);
            }
        }
        if (resignForm != null) {
            filePaths.put("resign_form", saveFiles(resignForm, passportNumber, "passport"));
        } else {
            if (resignForm1 != "" && resignForm1 != null) {
                filePaths.put("resign_form", resignForm1);
            }
        }
        if (cancelForm != null) {
            filePaths.put("cancel_form", saveFiles(cancelForm, passportNumber, "visa"));
        } else {
            if (cancelForm1 != "" && cancelForm1 != null) {
                filePaths.put("cancel_form", cancelForm1);
            }
        }

        // Update database record
        DriverFinalSettlement driverFinalSettlement = driverFinalSettlementRepository.findById(settleid).orElse(new DriverFinalSettlement());
        driverFinalSettlement.setSettleForm(filePaths.get("settle_form"));
        driverFinalSettlement.setResignForm(filePaths.get("resign_form"));
        driverFinalSettlement.setCancelForm(filePaths.get("cancel_form"));

        driverFinalSettlementRepository.save(driverFinalSettlement);

//        } catch (Exception e) {
//            errorCount++;
//        }

        return ResponseEntity.ok(Map.of("msg", "Details Updated Successfully"));
    }

    @PostMapping("/getDriverDetails")
    public Map<String, List<Map<String, Object>>> getDriverDetails(@RequestBody Map<String, String> payload) {
        String driverId = payload.get("driverId");
        return driverService.getDriverDetails(driverId);
    }

    @PostMapping("/getCountries")
    public List<Map<String, String>> getCountries() {
        return driverService.getCountries();
    }

    private String saveFiles(MultipartFile[] files, String passportNumber, String folderName) throws IOException {
        String uploadDir = UPLOAD_DIR_BASIC + passportNumber + "/" + folderName + "/";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                logger.error("Failed to create directory: " + uploadDir);
                throw new IOException("Failed to create directory: " + uploadDir);
            }
        }

        StringBuilder savedFilePath = new StringBuilder();
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String randomName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
                randomName = randomName.replaceAll("\\s+", "-").toLowerCase();
                String uploadName = uploadDir + randomName;
                file.transferTo(new File(uploadName));
                savedFilePath.append(uploadName).append(";"); // Save multiple paths separated by semicolon
            }
        }

        // Create the relative path
        String relativePath = "../" + savedFilePath.toString().replace("/home/ubuntu/", "");
        relativePath = relativePath.substring(0, relativePath.length() - 1);

        return relativePath;
    }
}
