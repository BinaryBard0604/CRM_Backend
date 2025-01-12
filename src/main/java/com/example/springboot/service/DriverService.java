package com.example.springboot.service;

import com.example.springboot.controller.FileUploadController;
import com.example.springboot.dto.DriverDetailsDTO;
import com.example.springboot.entity.*;
import com.example.springboot.repository.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DriverService {

    @Autowired
    private DriverDetailsRepository driverDetailsRepository;

    @Autowired
    private CustomerBranchRepository customerBranchRepository;

    @Autowired
    private DriverFinalSettlementRepository driverFinalSettlementRepository;

    @Autowired
    private DriverBranchAssignmentRepository driverBranchAssignmentRepository;

    @Autowired
    private DriverVacationRepository driverVacationRepository;

    @Autowired
    private SimAssignmentRepository simAssignmentRepository;

    @Autowired
    private DriverVisaApplicationRepository driverVisaApplicationRepository;

    @Autowired
    private VehicleAssignmentRepository vehicleAssignmentRepository;

    @Autowired
    private StaffDetailsRepository staffDetailsRepository;

    @Autowired
    private StaffVacationRepository staffVacationRepository;

    @Autowired
    private WpCountryRepository wpCountryRepository;

    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Transactional
    public Integer saveDriver(DriverDetails driverDetails) {
        // Save driver details and get the ID
        DriverDetails savedDriver = driverDetailsRepository.save(driverDetails);

        // Save branch assignment
        DriverBranchAssignment branchAssignment = new DriverBranchAssignment();
        branchAssignment.setDriverId(savedDriver.getId());
        branchAssignment.setBranchId(Integer.parseInt(driverDetails.getBranch()));
        branchAssignment.setUpdatedBy(driverDetails.getUpdatedBy());
        branchAssignment.setUpdatedDate(driverDetails.getUpdatedDate());
        driverBranchAssignmentRepository.save(branchAssignment);

        // Save SIM assignment
        SimAssignment simAssignment = new SimAssignment();
        simAssignment.setDriverId(savedDriver.getId());
        simAssignment.setMobile(driverDetails.getPhone());
        simAssignment.setUpdatedBy(driverDetails.getUpdatedBy());
        simAssignment.setUpdatedDate(String.valueOf(driverDetails.getUpdatedDate()));
        simAssignmentRepository.save(simAssignment);

        return savedDriver.getId();
    }

    public List<DriverDetailsDTO> getDrivers(int status, int userBranch, int userRole) {
        if (userBranch == 0 || userRole == 1) {
            if (status == 1) {
                return driverDetailsRepository.findByStatusAndBranch();
            } else {
                return driverDetailsRepository.findInactiveDrivers();
            }
        } else {
            if (status == 1) {
                return driverDetailsRepository.findByStatusAndBranchWithBranch(String.valueOf(userBranch));
            } else {
                return driverDetailsRepository.findInactiveDriversWithBranch(String.valueOf(userBranch));
            }
        }
    }

    public List<DriverDetailsDTO> searchDrivers(int status, int userBranch, int userRole, Map<String, String> searchData) {
        String startDate = searchData.get("start");
        String endDate = searchData.get("end");
        if (userBranch == 0 || userRole == 1) {
            if (status == 1) {
                return driverDetailsRepository.findByStatusAndBranchWithDay(LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else {
                return driverDetailsRepository.findInactiveDriversWithDay(LocalDate.parse(startDate), LocalDate.parse(endDate));
            }
        } else {
            if (status == 1) {
                return driverDetailsRepository.findByStatusAndBranchWithBranchWithDay(String.valueOf(userBranch), LocalDate.parse(startDate), LocalDate.parse(endDate));
            } else {
                return driverDetailsRepository.findInactiveDriversWithBranchWithDay(String.valueOf(userBranch), LocalDate.parse(startDate), LocalDate.parse(endDate));
            }
        }
    }

    public List<Map<String, Object>> updateDriverStatus(List<DriverDetailsDTO> drivers) {
        LocalDate today = LocalDate.now();
        List<Integer> driverIds = drivers.stream().map(DriverDetailsDTO::getDriverDetails).map(DriverDetails::getId).toList();

        // Fetch all required data in batch
        List<DriverVacation> vacations = driverVacationRepository.findLatestVacationsByDrivers(driverIds);
        List<DriverVisaApplication> visaApplications = driverVisaApplicationRepository.findVisaApprovalStatusesByDrivers(driverIds);
        List<VehicleAssignment> vehicleAssignments = vehicleAssignmentRepository.findVehicleAssignmentsByDrivers(driverIds);

        // Organize fetched data into maps for quick lookup
        Map<Integer, DriverVacation> vacationMap = vacations.stream()
                .collect(Collectors.toMap(DriverVacation::getDriverId, v -> v, (v1, v2) -> v1)); // Keep the latest
        Map<Integer, DriverVisaApplication> visaMap = visaApplications.stream()
                .collect(Collectors.toMap(DriverVisaApplication::getDriverId, v -> v, (v1, v2) -> v1)); // Keep the latest
        Map<Integer, VehicleAssignment> assignmentMap = vehicleAssignments.stream()
                .collect(Collectors.toMap(VehicleAssignment::getDriverId, v -> v));

        // Process each driver
        return drivers.stream().map(driver -> {
            Map<String, Object> response = new HashMap<>();

            // License Expiry Status
            if (driver.getDriverDetails().getLicenseExpiryDate() != null) {
                response.put("license_expiry_date", driver.getDriverDetails().getLicenseExpiryDate().isAfter(today) ? "YES" : "NO");
            } else {
                response.put("license_expiry_date", "NO");
            }

            // Vacation Status
            DriverVacation vacation = vacationMap.get(driver.getDriverDetails().getId());
            if (vacation != null) {
                if (driver.getDriverDetails().getStatus() == 0) {
                    response.put("job_sts", "No Longer Working");
                } else if (vacation.getLeaveFrom() != null && vacation.getLeaveTo() != null) {
                    if (today.isAfter(vacation.getLeaveFrom()) && today.isBefore(vacation.getLeaveTo()) && vacation.getAppovalStatus() == 1) {
                        response.put("job_sts", "Leave");
                    } else {
                        response.put("job_sts", "Active");
                    }
                } else {
                    response.put("job_sts", "Active");
                }

                if (vacation.getAppovalStatus() == 1 && vacation.getStatus() == 1) {
                    response.put("leave_approval", "Approved");
                } else if (vacation.getAppovalStatus() == 0 && vacation.getStatus() == 1) {
                    response.put("leave_approval", "Pending");
                } else if (vacation.getAppovalStatus() == 2 && vacation.getStatus() == 1) {
                    response.put("leave_approval", "Rejected");
                } else {
                    response.put("leave_approval", "");
                }
            } else {
                response.put("job_sts", "Active");
                response.put("leave_approval", "");
            }

            // Visa Status
            DriverVisaApplication visaApproval = visaMap.get(driver.getDriverDetails().getId());
            if (visaApproval != null) {
                switch (visaApproval.getApprovalStatus()) {
                    case 0 -> response.put("visa_app_status", "Pending");
                    case 1 -> response.put("visa_app_status", "Approved");
                    case 2 -> response.put("visa_app_status", "Rejected");
                    default -> response.put("visa_app_status", "");
                }
            } else {
                response.put("visa_app_status", "");
            }

            // Vehicle Assignment
            VehicleAssignment vehicleAssignment = assignmentMap.get(driver.getDriverDetails().getId());
            if (vehicleAssignment == null) {
                response.put("assignment_sts", "No current vehicle assignment");
            } else {
                response.put("assignment_sts", "");
            }

            response.put("branch", driver.getBranch());
            response.put("common_name", driver.getDriverDetails().getCommonName());
            response.put("cpk", driver.getDriverDetails().getCpk());
            response.put("date_employed", driver.getDriverDetails().getDateEmployed());
            response.put("employee_number", driver.getDriverDetails().getEmployeeNumber());
            response.put("full_name", driver.getDriverDetails().getFirstName() + " " + driver.getDriverDetails().getLastName());
            response.put("id", driver.getDriverDetails().getId());
            response.put("license_type", driver.getDriverDetails().getLicenseType());
            response.put("passport_number", driver.getDriverDetails().getPassportNumber());
            response.put("pdrp_expiry_date", driver.getDriverDetails().getPdrpExpiryDate());
            response.put("phone", driver.getDriverDetails().getPhone());
            response.put("status", driver.getDriverDetails().getStatus());
            response.put("visa_type", driver.getDriverDetails().getVisaType());
            response.put("work_permit_expiry", driver.getDriverDetails().getWorkPermitExpiry());

            return response;
        }).toList();
    }

    public Map<String, Object> getDriverData(Long driverId) {
        Map<String, Object> result = new HashMap<>();

        // Get vacation details
        List<DriverVacation> vacationDetails = driverVacationRepository.findByDriverIdAndStatusOrderByIdDesc(driverId);
        if (!vacationDetails.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("appoval_status", String.valueOf(vacationDetails.get(0).getAppovalStatus()));
            response.put("driver_id", String.valueOf(vacationDetails.get(0).getDriverId()));
            response.put("id", String.valueOf(vacationDetails.get(0).getId()));
            response.put("leave_form", vacationDetails.get(0).getLeaveForm());
            response.put("leave_from", String.valueOf(vacationDetails.get(0).getLeaveFrom()));
            response.put("leave_to", String.valueOf(vacationDetails.get(0).getLeaveTo()));
            response.put("leave_type", vacationDetails.get(0).getLeaveType());
            response.put("notes", vacationDetails.get(0).getNotes());
            response.put("rejoin_date", String.valueOf(vacationDetails.get(0).getRejoinDate()));
            response.put("settlement_form", vacationDetails.get(0).getSettlementForm());
            response.put("status", String.valueOf(vacationDetails.get(0).getStatus()));

            result.put("vacationdetails", response); // Get the latest vacation detail
        } else {
            result.put("vacationdetails", null);
        }

        // Get driver details
        DriverDetails driverDetails = driverDetailsRepository.findById(driverId).orElse(null);

        if (!vacationDetails.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("change_date", driverDetails.getChangeDate());
            response.put("date_employed", String.valueOf(driverDetails.getDateEmployed()));
            response.put("driver_name", driverDetails.getFirstName() + " " + driverDetails.getLastName());
            response.put("id", String.valueOf(driverDetails.getId()));
            response.put("passport_number", driverDetails.getPassportNumber());

            result.put("joindetails", response);
        } else {
            result.put("joindetails", null);
        }

        return result;
    }

    public Map<String, String> getDriverDetails(Integer driverId) {
        DriverDetails driverDetails = driverDetailsRepository.findDriverDetailsById(driverId);
        CustomerBranch customerBranch = driverDetailsRepository.findCustomerBranchById(driverId);
        DriverVisaApplication driverVisaApplication = driverDetailsRepository.findDriverVisaApplicationById(driverId);

        Map<String, String> response = new HashMap<>();

        if (driverDetails != null) {
            response.put("id", String.valueOf(driverDetails.getId()));
            response.put("driver_name", driverDetails.getFirstName() + " " + driverDetails.getLastName());
            response.put("phone", driverDetails.getPhone());
            response.put("passport_number", driverDetails.getPassportNumber());
            response.put("date_employed", String.valueOf(driverDetails.getDateEmployed()));
            if ("1".equals(driverDetails.getVisaType())) {
                response.put("visa_type", "Time Express Visa");
            } else if ("2".equals(driverDetails.getVisaType())) {
                response.put("visa_type", "Visit Visa");
            } else if ("3".equals(driverDetails.getVisaType())) {
                response.put("visa_type", "Freelance Visa");
            } else if ("4".equals(driverDetails.getVisaType())) {
                response.put("visa_type", "Cancelled");
            } else if ("5".equals(driverDetails.getVisaType())) {
                response.put("visa_type", "Other");
            }
        } else {
            response.put("id", null);
            response.put("driver_name", null);
            response.put("phone", null);
            response.put("passport_number", null);
            response.put("date_employed", null);
            response.put("visa_type", null);
        }

        if (customerBranch != null) {
            response.put("branch", customerBranch.getBranch());
        } else {
            response.put("branch", null);
        }

        if (driverVisaApplication != null) {
            response.put("notes", driverVisaApplication.getNotes());
            response.put("applied_for", driverVisaApplication.getAppliedFor());
            response.put("approval_status", String.valueOf(driverVisaApplication.getApprovalStatus()));
        } else {
            response.put("notes", null);
            response.put("applied_for", null);
            response.put("approval_status", null);
        }

        return response;
    }

    public void deleteDriverDetails(String user, String record, String type) {
        Long driverId = Long.parseLong(record);

        driverDetailsRepository.findById(driverId).map(existingData -> {
            existingData.setStatus(0);
            return driverDetailsRepository.save(existingData);
        });
    }

    public void deleteDriverVacation(String user, String record, String type) {
        Integer driverId = Integer.parseInt(record);

        driverVacationRepository.findById(Long.parseLong(record)).map(existingData -> {
            existingData.setStatus(0);
            return driverVacationRepository.save(existingData);
        });
    }

    public static String uploadFile(MultipartFile file, String subDir) throws IOException {
        String uploadDir = "C:/uploads/";
        String directoryPath = uploadDir + subDir;

        // Create directories if they don't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generate unique file name
        String randomName = System.currentTimeMillis() + "-" + file.getOriginalFilename().replaceAll("\\s+", "-").toLowerCase();
        File destinationFile = new File(directoryPath + "/" + randomName);

        // Save the file
        file.transferTo(destinationFile);

        return destinationFile.getAbsolutePath();
    }

    public Integer updateLeave(String user, String driverId, String leave_from, String leave_to, String leave_type, String notes) {
        Optional<DriverVacation> driverVacation = driverVacationRepository.findDriverIdWithStatus(Integer.parseInt(driverId));

        if (driverVacation.isPresent()) {
            driverVacationRepository.deactivateVacationByDriverId(Integer.parseInt(driverId));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();

        DriverVacation newVacation = new DriverVacation();
        newVacation.setDriverId(Integer.parseInt(driverId));
        newVacation.setLeaveType(leave_type);
        newVacation.setLeaveFrom(LocalDate.parse(leave_from, formatter));
        newVacation.setLeaveTo(LocalDate.parse(leave_to, formatter));
        newVacation.setLeaveForm("");
        newVacation.setAppovalStatus(0); // Default value
        newVacation.setSettlementForm(""); // Default value
        newVacation.setStatus(1); // Active status
        newVacation.setNotes(notes);
        newVacation.setRejoinDate(String.valueOf(today));
        newVacation.setUpdatedBy(Integer.parseInt(user));
        newVacation.setUpdatedDate(today);

        DriverVacation savedVacation = driverVacationRepository.save(newVacation);

        return savedVacation.getId();
    }

    public String applyVisa(String user, String driver_name, String visa_type, String date_employed, String branch, String passport_number, String phone, String driver, String notes, String applied_for) {
        try {
            List<DriverVisaApplication> driverVisaApplication = driverVisaApplicationRepository.findDriverVisaApplicationIdWithStatus(Integer.parseInt(driver));

            if (!driverVisaApplication.isEmpty()) {
                driverVisaApplicationRepository.setDriverVisaApplicationWithDriverId(Integer.parseInt(driver));
            }

            LocalDate today = LocalDate.now();

            DriverVisaApplication newDriverVisaApplication = new DriverVisaApplication();
            newDriverVisaApplication.setDriverId(Integer.parseInt(driver));
            newDriverVisaApplication.setAppliedFor(applied_for);
            newDriverVisaApplication.setApprovalStatus(0);
            newDriverVisaApplication.setNotes(notes);
            newDriverVisaApplication.setUpdatedBy(Integer.parseInt(user));
            newDriverVisaApplication.setUpdatedDate(today); // Default value
            newDriverVisaApplication.setStatus(1);

            DriverVisaApplication savedVisa = driverVisaApplicationRepository.save(newDriverVisaApplication);

            return "Visa Request Submitted Successfully";
        } catch (Exception e) {
            logger.error(String.valueOf(e));
            logger.info(">>>>>>>>>>>>>>" + e.getMessage());
            return "Error updating details";
        }
    }

    public String settle(String user, String driverId, String resign_date, String cancel_date, String type_of_leaving, String notes, String reason) {
        try {
            List<DriverFinalSettlement> driverFinalSettlements = driverFinalSettlementRepository.getDriverFinalSettlementsWithDriverId(Integer.parseInt(driverId));

            LocalDate today = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            if (!driverFinalSettlements.isEmpty()) {
                int i = driverFinalSettlementRepository.updateDriverFinalSettlement(Integer.parseInt(driverId));

                return String.valueOf(i);
            } else {
                DriverFinalSettlement newDriverFinalSettlement = new DriverFinalSettlement();
                newDriverFinalSettlement.setDriverId(Integer.parseInt(driverId));
                if (resign_date == null) {
                    newDriverFinalSettlement.setResignDate(null);
                } else {
                    newDriverFinalSettlement.setResignDate(LocalDate.parse(resign_date, formatter));
                }
                if (cancel_date == null) {
                    newDriverFinalSettlement.setCancelDate(null);
                } else {
                    newDriverFinalSettlement.setCancelDate(LocalDate.parse(cancel_date, formatter));
                }
                newDriverFinalSettlement.setTypeOfLeaving(type_of_leaving);
                newDriverFinalSettlement.setSettleForm("");
                newDriverFinalSettlement.setCancelForm(""); // Default value
                newDriverFinalSettlement.setResignForm("");
                newDriverFinalSettlement.setNotes(notes);
                newDriverFinalSettlement.setReason(reason); // Default value
                newDriverFinalSettlement.setStatus(1);
                newDriverFinalSettlement.setUpdatedBy(Integer.parseInt(user));
                newDriverFinalSettlement.setUpdatedDate(today);

                DriverFinalSettlement savedFinal = driverFinalSettlementRepository.save(newDriverFinalSettlement);

                return String.valueOf(savedFinal.getId());
            }
        } catch (Exception e) {
            logger.info(">>>>>>>>>>>>>>" + e.getMessage());
            return "0";
        }
    }

    public String saveFile(MultipartFile file, String subDir) throws IOException {
        String directoryPath = subDir;
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = directoryPath + "/" + System.currentTimeMillis() + "-" + file.getOriginalFilename();
        File destinationFile = new File(filePath);

        file.transferTo(destinationFile);
        return filePath;
    }

    public List<Map<String, ?>> getVehicleDetailsByDriverId(Long driverId) {
        return vehicleAssignmentRepository.findVehicleDetailsByDriverId(driverId);
    }

    public List<Map<String, ?>> getDriverLeavesByDriverId(Long driverId) {
        return vehicleAssignmentRepository.findDriverLeavesByDriverId(driverId);
    }

    public List<Map<String, ?>> getBasicDetailsWithDriver(String type, String driverId) {
        return driverDetailsRepository.getBasicDetailsWithDriver(Integer.parseInt(driverId));
    }

    public List<Map<String, ?>> getBasicStaffDetails(String staffId) {
        return staffDetailsRepository.getBasicStaffDetails(Integer.parseInt(staffId));
    }

    public Map<String, ?> updateDriver(String user, Map<String, String> driverData, String driverId) {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String branch = driverData.get("branch");
        String first_name = driverData.get("first_name");
        String last_name = driverData.get("last_name");
        String common_name = driverData.get("common_name");
        String employee_number = driverData.get("employee_number");
        String phone = driverData.get("phone");
        String date_employed = driverData.get("date_employed");
        String citizenship = driverData.get("citizenship");
        String passport_number = driverData.get("passport_number");
        String pdrp_expiry_date = driverData.get("pdrp_expiry_date");
        String license_type = driverData.get("license_type");
        String license_expiry_date = driverData.get("license_expiry_date");
        String change_date = driverData.get("change_date");
        String work_permit_number = driverData.get("work_permit_number");
        String work_permit_expiry = driverData.get("work_permit_expiry");
        String dob = driverData.get("dob");
        String emirates_id_no = driverData.get("emirates_id_no");
        String license_no = driverData.get("license_no");
        String visa_type = driverData.get("visa_type");
        String own_vehicle = driverData.get("own_vehicle");
        String home_contact = driverData.get("home_contact");
        String home_contact_no = driverData.get("home_contact_no");
        String res_address = driverData.get("res_address");
        String res_city = driverData.get("res_city");
        String ctc = driverData.get("ctc");
        String cpk = driverData.get("cpk");
        String notes = driverData.get("notes");

        if (first_name != "") {
            try {
                driverDetailsRepository.findById(Long.parseLong(driverId)).map(existingData -> {
                    existingData.setBranch(branch);
                    existingData.setFirstName(first_name);
                    existingData.setLastName(last_name);
                    existingData.setCommonName(common_name);
                    existingData.setEmployeeNumber(employee_number);
                    existingData.setPhone(phone);
                    existingData.setHomeContact(home_contact);
                    existingData.setHomeContactNo(home_contact_no);
                    if (date_employed == null || date_employed == "") {
                        existingData.setDateEmployed(null);
                    } else {
                        existingData.setDateEmployed(LocalDate.parse(date_employed));
                    }
                    existingData.setCitizenship(citizenship);
                    existingData.setPassportNumber(passport_number);
                    existingData.setChangeDate(change_date);
                    existingData.setLicenseType(license_type);
                    if (license_expiry_date == null || license_expiry_date == "") {
                        existingData.setLicenseExpiryDate(null);
                    } else {
                        existingData.setLicenseExpiryDate(LocalDate.parse(license_expiry_date));
                    }
                    if (pdrp_expiry_date == null || pdrp_expiry_date == "") {
                        existingData.setPdrpExpiryDate(null);
                    } else {
                        existingData.setPdrpExpiryDate(LocalDate.parse(pdrp_expiry_date));
                    }
                    existingData.setWorkPermitNumber(work_permit_number);
                    if (work_permit_expiry == null || work_permit_expiry == "") {
                        existingData.setWorkPermitExpiry(null);
                    } else {
                        existingData.setWorkPermitExpiry(LocalDate.parse(work_permit_expiry));
                    }
                    existingData.setCtc(ctc);
                    existingData.setCpk(cpk);
                    existingData.setResAddress(res_address);
                    existingData.setResCity(res_city);
                    existingData.setNotes(notes);
                    existingData.setVisaType(visa_type);
                    existingData.setOwnVehicle(own_vehicle);
                    if (dob == null || dob == "") {
                        existingData.setDob(null);
                    } else {
                        existingData.setDob(LocalDate.parse(dob));
                    }
                    existingData.setEmiratesIdNo(emirates_id_no);
                    existingData.setLicenseNo(license_no);
                    existingData.setStatus(1);
                    existingData.setUpdatedBy(Integer.parseInt(user));
                    existingData.setUpdatedDate(today);
                    return driverDetailsRepository.save(existingData);
                });

                Integer branchId = Integer.parseInt(branch);
                List<DriverBranchAssignment> driverBranchAssignments = driverBranchAssignmentRepository.getDriverBranchAssignmentsByBranchId(Integer.parseInt(driverId), branchId);

                if (driverBranchAssignments.isEmpty()) {
                    driverBranchAssignmentRepository.updateStatusToInactive();

                    DriverBranchAssignment driverBranchAssignment = new DriverBranchAssignment();
                    driverBranchAssignment.setDriverId(Integer.parseInt(driverId));
                    driverBranchAssignment.setBranchId(branchId);
                    driverBranchAssignment.setStatus(0);
                    driverBranchAssignment.setUpdatedBy(Integer.parseInt(user));
                    driverBranchAssignment.setUpdatedDate(today);

                    driverBranchAssignmentRepository.save(driverBranchAssignment);
                }

                List<SimAssignment> simAssignments = simAssignmentRepository.getSimAssingmentByBranchId(Integer.parseInt(driverId), phone);

                if (simAssignments.isEmpty()) {
                    simAssignmentRepository.updateStatusToInactive();

                    SimAssignment simAssignment = new SimAssignment();
                    simAssignment.setDriverId(Integer.parseInt(driverId));
                    simAssignment.setMobile(phone);
                    simAssignment.setStatus(0);
                    simAssignment.setUpdatedBy(Integer.parseInt(user));
                    simAssignment.setUpdatedDate(String.valueOf(today));

                    simAssignmentRepository.save(simAssignment);
                }

                return Map.of("msg", "Details Updated Successfully", "status", 1);
            } catch (Exception e) {
                logger.error("An error occurred: {}", e.getMessage(), e);
                return Map.of("msg", "Unable to Update. Pleae try again", "status", 1);
            }
        }

        return Map.of("msg", "Unable to Update. Pleae try again", "status", 1);
    }

    public Optional<DriverDetails> getDriverDetailsById(Long driverId) {
        return driverDetailsRepository.findById(driverId);
    }

    public Optional<DriverFinalSettlement> getDriverFinalSettlementByDriverId(Long driverId) {
        return driverFinalSettlementRepository.findByDriverId(driverId);
    }

    public Map<String, List<Map<String, Object>>> getDriverDetails(String driverId) {
        List<Map<String, Object>> vacationdetails = driverVacationRepository.getDataWithDriverVacaition(Integer.parseInt(driverId));
        List<Map<String, Object>> joindetails = driverDetailsRepository.getDataWithDriverDetails(Integer.parseInt(driverId));

        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        result.put("vacationdetails", vacationdetails);
        result.put("joindetails", joindetails);

        return result;
    }

    public List<Map<String, ?>> getStaffVacationWithStaffId(String staffId) {
        return staffVacationRepository.getStaffVacationWithStaffId(Integer.parseInt(staffId));
    }

    public List<Map<String, ?>> closeStaffLeave(String staffId, String rejoinDate, String vacationId, String user) {
        List<Map<String, ?>> result = new ArrayList<>();
        Map<String, Integer> result1 = new HashMap<>();
        logger.info(">>>>>>" + staffId + ">>>>>>" + rejoinDate + ">>>>>>>>" + vacationId + ">>>>>>" + user);

        try {
            staffVacationRepository.closeStaffLeave(Integer.parseInt(staffId), rejoinDate, Integer.parseInt(vacationId), Integer.parseInt(user));

            result1.put("result", 1);
            result.add(result1);

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, ?>> getDriverVacationWithDriverId(String driverId) {
        return driverVacationRepository.getDriverVacattionWithDriverId(Integer.parseInt(driverId));
    }

    public List<Map<String, ?>> closeDriverLeaves(String driverId, String rejoinDate, String vacationId, String user, String notes) {
        List<Map<String, ?>> result = new ArrayList<>();
        Map<String, Integer> result1 = new HashMap<>();

        try {
            driverVacationRepository.closeDriverLeaves(Integer.parseInt(driverId), rejoinDate, Integer.parseInt(vacationId), Integer.parseInt(user));

            result1.put("result", 1);
            result.add(result1);

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String, String>> getCountries() {
        return wpCountryRepository.getAllData();
    }
}
