package com.example.springboot.controller;

import com.example.springboot.entity.DriverDetails;
import com.example.springboot.entity.StaffDetails;
import com.example.springboot.repository.DriverDetailsRepository;
import com.example.springboot.repository.StaffDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class FileUploadController {

    private final String UPLOAD_DIR_BASIC = "/home/ubuntu/uploads/";

    @Autowired
    private DriverDetailsRepository driverDetailsRepository;

    @Autowired
    private StaffDetailsRepository staffDetailsRepository;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping("/fileUpload")
    public ResponseEntity<?> uploadFiles(
            @RequestParam("orderno") Long orderNo,
            @RequestParam("passportno") String passportNumber,
            @RequestParam("uploadtype") String uploadType,
            @RequestParam(value = "photo", required = false) MultipartFile[] photo,
            @RequestParam(value = "passport", required = false) MultipartFile[] passport,
            @RequestParam(value = "visa", required = false) MultipartFile[] visa,
            @RequestParam(value = "emirates_id", required = false) MultipartFile[] emiratesId,
            @RequestParam(value = "license", required = false) MultipartFile[] license,
            @RequestParam(value = "labor_contract", required = false) MultipartFile[] laborContract,
            @RequestParam(value = "other", required = false) MultipartFile[] other,
            @RequestParam(value = "photo1", required = false) String photo1,
            @RequestParam(value = "passport1", required = false) String passport1,
            @RequestParam(value = "visa1", required = false) String visa1,
            @RequestParam(value = "emirates_id1", required = false) String emiratesId1,
            @RequestParam(value = "license1", required = false) String license1,
            @RequestParam(value = "labour_contract1", required = false) String labourContract1,
            @RequestParam(value = "other_doc1", required = false) String otherDoc1) throws IOException {

        Map<String, String> filePaths = new HashMap<>();
        int errorCount = 0;

//        try {
            // Clean passport number (replace '-' and spaces)
            passportNumber = passportNumber.replace("-", "").replace(" ", "");

            // Handle "add" or "edit" logic
            if ("add".equalsIgnoreCase(uploadType)) {
                filePaths.put("photo", null);
                filePaths.put("passport", null);
                filePaths.put("visa", null);
                filePaths.put("emirates_id", null);
                filePaths.put("license", null);
                filePaths.put("labor_contract", null);
                filePaths.put("other", null);

                // Save files for each type
                if (photo != null) {
                    filePaths.put("photo", saveFiles(photo, passportNumber, "photo"));
                }
                if (passport != null) {
                    filePaths.put("passport", saveFiles(passport, passportNumber, "passport"));
                }
                if (visa != null) {
                    filePaths.put("visa", saveFiles(visa, passportNumber, "visa"));
                }
                if (emiratesId != null) {
                    filePaths.put("emirates_id", saveFiles(emiratesId, passportNumber, "emirates_id"));
                }
                if (license != null) {
                    filePaths.put("license", saveFiles(license, passportNumber, "license"));
                }
                if (laborContract != null) {
                    filePaths.put("labor_contract", saveFiles(laborContract, passportNumber, "labor_contract"));
                }
                if (other != null) {
                    filePaths.put("other", saveFiles(other, passportNumber, "other_doc"));
                }
            }

        // Handle "add" or "edit" logic
        if ("edit".equalsIgnoreCase(uploadType)) {
            filePaths.put("photo", null);
            filePaths.put("passport", null);
            filePaths.put("visa", null);
            filePaths.put("emirates_id", null);
            filePaths.put("license", null);
            filePaths.put("labor_contract", null);
            filePaths.put("other", null);

            // Save files for each type
            if (photo != null) {
                filePaths.put("photo", saveFiles(photo, passportNumber, "photo"));
            } else {
                if (photo1 != "" && photo1 != null) {
                    filePaths.put("photo", photo1);
                }
            }
            if (passport != null) {
                filePaths.put("passport", saveFiles(passport, passportNumber, "passport"));
            } else {
                if (passport1 != "" && passport1 != null) {
                    filePaths.put("passport", passport1);
                }
            }
            if (visa != null) {
                filePaths.put("visa", saveFiles(visa, passportNumber, "visa"));
            } else {
                if (visa1 != "" && visa1 != null) {
                    filePaths.put("visa", visa1);
                }
            }
            if (emiratesId != null) {
                filePaths.put("emirates_id", saveFiles(emiratesId, passportNumber, "emirates_id"));
            } else {
                if (emiratesId1 != "" && emiratesId1 != null) {
                    filePaths.put("emirates_id", emiratesId1);
                }
            }
            if (license != null) {
                filePaths.put("license", saveFiles(license, passportNumber, "license"));
            } else {
                if (license1 != "" && license1 != null) {
                    filePaths.put("license", license1);
                }
            }
            if (laborContract != null) {
                filePaths.put("labor_contract", saveFiles(laborContract, passportNumber, "labor_contract"));
            } else {
                if (labourContract1 != "" && labourContract1 != null) {
                    filePaths.put("labor_contract", labourContract1);
                }
            }
            if (other != null) {
                filePaths.put("other", saveFiles(other, passportNumber, "other_doc"));
            } else {
                if (otherDoc1 != "" && otherDoc1 != null) {
                    filePaths.put("other", otherDoc1);
                }
            }
        }

            // Update database record
            DriverDetails driverDetails = driverDetailsRepository.findById(orderNo).orElse(new DriverDetails());
            driverDetails.setPhoto(filePaths.get("photo"));
            driverDetails.setPassport(filePaths.get("passport"));
            driverDetails.setVisa(filePaths.get("visa"));
            driverDetails.setEmiratesId(filePaths.get("emirates_id"));
            driverDetails.setDrivingLicense(filePaths.get("license"));
            driverDetails.setLabourContract(filePaths.get("labor_contract"));
            driverDetails.setOtherDoc(filePaths.get("other"));

            driverDetailsRepository.save(driverDetails);

//        } catch (Exception e) {
//            errorCount++;
//        }

        return ResponseEntity.ok(Map.of("errorCount", errorCount));
    }

    @PostMapping("/staffFileUpload")
    public ResponseEntity<?> staffFileUploads(
            @RequestParam("orderno") Long orderNo,
            @RequestParam("passportno") String passportNumber,
            @RequestParam("uploadtype") String uploadType,
            @RequestParam(value = "photo", required = false) MultipartFile[] photo,
            @RequestParam(value = "passport", required = false) MultipartFile[] passport,
            @RequestParam(value = "visa", required = false) MultipartFile[] visa,
            @RequestParam(value = "emirates_id", required = false) MultipartFile[] emiratesId,
            @RequestParam(value = "license", required = false) MultipartFile[] license,
            @RequestParam(value = "labor_contract", required = false) MultipartFile[] laborContract,
            @RequestParam(value = "other", required = false) MultipartFile[] other,
            @RequestParam(value = "photo1", required = false) String photo1,
            @RequestParam(value = "passport1", required = false) String passport1,
            @RequestParam(value = "visa1", required = false) String visa1,
            @RequestParam(value = "emirates_id1", required = false) String emiratesId1,
            @RequestParam(value = "license1", required = false) String license1,
            @RequestParam(value = "labour_contract1", required = false) String labourContract1,
            @RequestParam(value = "other_doc1", required = false) String otherDoc1) throws IOException {

        Map<String, String> filePaths = new HashMap<>();
        int errorCount = 0;

//        try {
        // Clean passport number (replace '-' and spaces)
        passportNumber = passportNumber.replace("-", "").replace(" ", "");

        // Handle "add" or "edit" logic
        if ("add".equalsIgnoreCase(uploadType)) {
            filePaths.put("photo", null);
            filePaths.put("passport", null);
            filePaths.put("visa", null);
            filePaths.put("emirates_id", null);
            filePaths.put("license", null);
            filePaths.put("labor_contract", null);
            filePaths.put("other", null);

            // Save files for each type
            if (photo != null) {
                filePaths.put("photo", saveFiles(photo, passportNumber, "photo"));
            }
            if (passport != null) {
                filePaths.put("passport", saveFiles(passport, passportNumber, "passport"));
            }
            if (visa != null) {
                filePaths.put("visa", saveFiles(visa, passportNumber, "visa"));
            }
            if (emiratesId != null) {
                filePaths.put("emirates_id", saveFiles(emiratesId, passportNumber, "emirates_id"));
            }
            if (license != null) {
                filePaths.put("license", saveFiles(license, passportNumber, "license"));
            }
            if (laborContract != null) {
                filePaths.put("labor_contract", saveFiles(laborContract, passportNumber, "labor_contract"));
            }
            if (other != null) {
                filePaths.put("other", saveFiles(other, passportNumber, "other_doc"));
            }
        }

        // Handle "add" or "edit" logic
        if ("edit".equalsIgnoreCase(uploadType)) {
            filePaths.put("photo", null);
            filePaths.put("passport", null);
            filePaths.put("visa", null);
            filePaths.put("emirates_id", null);
            filePaths.put("license", null);
            filePaths.put("labor_contract", null);
            filePaths.put("other", null);

            // Save files for each type
            if (photo != null) {
                filePaths.put("photo", saveFiles(photo, passportNumber, "photo"));
            } else {
                if (photo1 != "" && photo1 != null) {
                    filePaths.put("photo", photo1);
                }
            }
            if (passport != null) {
                filePaths.put("passport", saveFiles(passport, passportNumber, "passport"));
            } else {
                if (passport1 != "" && passport1 != null) {
                    filePaths.put("passport", passport1);
                }
            }
            if (visa != null) {
                filePaths.put("visa", saveFiles(visa, passportNumber, "visa"));
            } else {
                if (visa1 != "" && visa1 != null) {
                    filePaths.put("visa", visa1);
                }
            }
            if (emiratesId != null) {
                filePaths.put("emirates_id", saveFiles(emiratesId, passportNumber, "emirates_id"));
            } else {
                if (emiratesId1 != "" && emiratesId1 != null) {
                    filePaths.put("emirates_id", emiratesId1);
                }
            }
            if (license != null) {
                filePaths.put("license", saveFiles(license, passportNumber, "license"));
            } else {
                if (license1 != "" && license1 != null) {
                    filePaths.put("license", license1);
                }
            }
            if (laborContract != null) {
                filePaths.put("labor_contract", saveFiles(laborContract, passportNumber, "labor_contract"));
            } else {
                if (labourContract1 != "" && labourContract1 != null) {
                    filePaths.put("labor_contract", labourContract1);
                }
            }
            if (other != null) {
                filePaths.put("other", saveFiles(other, passportNumber, "other_doc"));
            } else {
                if (otherDoc1 != "" && otherDoc1 != null) {
                    filePaths.put("other", otherDoc1);
                }
            }
        }

        // Update database record
        StaffDetails staffDetails = staffDetailsRepository.findById(orderNo).orElse(new StaffDetails());
        staffDetails.setPhoto(filePaths.get("photo"));
        staffDetails.setPassport(filePaths.get("passport"));
        staffDetails.setVisa(filePaths.get("visa"));
        staffDetails.setEmirates_id(filePaths.get("emirates_id"));
        staffDetails.setDriving_license(filePaths.get("license"));
        staffDetails.setLabour_contract(filePaths.get("labor_contract"));
        staffDetails.setOther_doc(filePaths.get("other"));

        staffDetailsRepository.save(staffDetails);

        return ResponseEntity.ok(Map.of("errorCount", errorCount));
    }

    private String saveFiles(MultipartFile[] files, String passportNumber, String folderName) throws IOException {
        try {
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
