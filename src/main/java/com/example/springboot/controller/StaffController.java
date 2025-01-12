package com.example.springboot.controller;

import com.example.springboot.entity.DriverVacation;
import com.example.springboot.entity.StaffVacation;
import com.example.springboot.repository.StaffDetailsRepository;
import com.example.springboot.repository.StaffVacationRepository;
import com.example.springboot.service.DriverService;
import com.example.springboot.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StaffController {

    private final String UPLOAD_DIR_BASIC = "/home/ubuntu/uploads/";

    @Autowired
    StaffService staffService;

    @Autowired
    StaffVacationRepository staffVacationRepository;

    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    @PostMapping("/staffDashboard")
    public Map<String, List<Map<String, Object>>> getStaffDashboard(@RequestBody Map<String, String> payload) {
        String user = payload.get("user");
        String branch = payload.get("branch");
        String user_role = payload.get("user_role");
        String type = payload.get("type");

        if ("basic".equals(type)) {
            return staffService.getBasicStaffDetails();
        } else if ("notification".equals(type)) {
            return staffService.getStaffDashboard();
        } else {
            return null;
        }
    }

    @PostMapping("/staff")
    public Map<String, List<Map<String, String>>> getStaff(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");

        if ("leave_list".equals(type)) {
            return staffService.getLeavelist();
        } else if ("search".equals(type)) {
            String staffId = payload.get("val");

            return staffService.getSearch(staffId);
        } else if ("branch".equals(type)) {
            return staffService.getBranch();
        } else if ("list".equals(type)) {
            return staffService.getStaffList();
        } else if ("delete".equals(type)) {
            String staffId = payload.get("record");

            return staffService.deleteStaff(staffId);
        } else {
            return null;
        }
    }

    @PostMapping("/staffAction")
    public Integer staffAction(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");

        if ("updateleave".equals(type)) {
            String user = payload.get("user");
            String leave_from = payload.get("leave_from");
            String leave_to = payload.get("leave_to");
            String notes = payload.get("notes");
            String leave_type = payload.get("leave_type");
            String staff_id = payload.get("staff_id");
            String leave_form = payload.get("leave_form");
            String settlement_form = payload.get("settlement_form");
            String id = payload.get("id");
            String staff_name = payload.get("staff_name");
            String change_date = payload.get("change_date");
            String date_employed = payload.get("date_employed");

            return staffService.updateLeave(user, leave_from, leave_to, notes, leave_type, staff_id, leave_form, settlement_form,
                    id, staff_name, change_date, date_employed);
        } else {
            return 0;
        }
    }

    @PostMapping("/staffVacationFileUpload")
    public ResponseEntity<?> vacationFileUpload(@RequestParam("vacationid") String vacationId,
                                                @RequestParam("passportno") String passportno,
                                                @RequestParam(value = "leave_form", required = false) MultipartFile[] leaveForms,
                                                @RequestParam(value = "leaveform2", required = false) MultipartFile[] leaveForms2,
                                                @RequestParam(value = "settlement", required = false) MultipartFile[] settlements,
                                                @RequestParam(value = "settlementform2", required = false) MultipartFile[] settlementform2) {

        try {
            Long vacation_id = Long.parseLong(vacationId);
            StaffVacation vacation = staffVacationRepository.findById(vacation_id).orElseThrow(() -> new RuntimeException("Vacation not found"));
            String passportNumber = passportno.replace("-", "").replace(" ", "");

            // Process leave forms
            if (leaveForms != null && leaveForms.length > 0) {
                if (leaveForms2 != null && leaveForms2.length > 0) {
                    for (MultipartFile leaveForm : leaveForms2) {
                        String leaveFormPath = DriverService.uploadFile(leaveForm, passportNumber + "/leave_applications/");
                        vacation.setLeave_form(leaveFormPath);
                    }
                } else {
                    for (MultipartFile leaveForm : leaveForms) {
                        String leaveFormPath = DriverService.uploadFile(leaveForm, passportNumber + "/leave_applications/");
                        vacation.setLeave_form(leaveFormPath);
                    }
                }
            }

            // Process settlements
            if (settlements != null && settlements.length > 0) {
                if (settlementform2 != null && settlementform2.length > 0) {
                    for (MultipartFile settlement : settlementform2) {
                        String settlementPath = DriverService.uploadFile(settlement, passportNumber + "/leave_applications/Settlements/");
                        vacation.setSettlement_form(settlementPath);
                    }
                } else {
                    for (MultipartFile settlement : settlements) {
                        String settlementPath = DriverService.uploadFile(settlement, passportNumber + "/leave_applications/Settlements/");
                        vacation.setSettlement_form(settlementPath);
                    }
                }
            }

            staffVacationRepository.save(vacation);

            return ResponseEntity.ok("Files uploaded and vacation updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error uploading files: " + e.getMessage());
        }
    }

    @PostMapping("/addStaff")
    public Integer addStaff(@RequestBody Map<String, String> payload) {
        String user = payload.get("user");
        String branch = payload.get("branch");
        String designation = payload.get("designation");
        String first_name = payload.get("first_name");
        String last_name = payload.get("last_name");
        String email = payload.get("email");
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
        String res_address = payload.get("res_address");
        String res_city = payload.get("res_city");
        String notes = payload.get("notes");
        String passport_doc = payload.get("passport_doc");

        return staffService.addStaff(user, branch, designation, first_name, last_name, email, employee_number, mobile_number, emp_date, citizenship,
                passport_number, passport_expiry, license_type, license_expiry, change_date, work_permit_number, work_permit_expiry, dob,
                eid_number, license_number, visa_type, own_vehicle, home_contact, home_contact_no, res_address, res_city, notes, passport_doc);
    }

    @PostMapping("/updateStaff")
    public Map<String, String> updateStaff(@RequestBody Map<String, String> payload) {
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
        String res_address = payload.get("res_address");
        String res_city = payload.get("res_city");
        String notes = payload.get("notes");
        String ctc = payload.get("ctc");
        String designation = payload.get("designation");
        String email = payload.get("email");
        String staffid = payload.get("staffid");

        return staffService.updateStaff(user, branch, first_name, last_name, common_name, employee_number, mobile_number, emp_date,
                citizenship, passport_number, passport_expiry, license_type, license_expiry, change_date, work_permit_number, work_permit_expiry,
                dob, eid_number, license_number, visa_type, own_vehicle, home_contact, home_contact_no, res_address, res_city, notes,
                ctc, designation, email, staffid);
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
