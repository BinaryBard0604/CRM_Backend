package com.example.springboot.service;

import com.example.springboot.entity.StaffDetails;
import com.example.springboot.entity.StaffVacation;
import com.example.springboot.repository.StaffBranchRepository;
import com.example.springboot.repository.StaffDetailsRepository;
import com.example.springboot.repository.StaffVacationRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StaffService {

    @Autowired
    StaffDetailsRepository staffDetailsRepository;

    @Autowired
    StaffVacationRepository staffVacationRepository;

    @Autowired
    StaffBranchRepository staffBranchRepository;

    public Map<String, List<Map<String, Object>>> getBasicStaffDetails() {
        LocalDate today = LocalDate.now();
        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        List<Map<String, Object>> staffcount = staffDetailsRepository.getStaffCount();
        List<Map<String, Object>> staffleavescount = staffVacationRepository.getStaffLeaveCount(today);

        result.put("staffcount", staffcount);
        result.put("staffleavescount", staffleavescount);

        return  result;
    }

    public Map<String, List<Map<String, Object>>> getStaffDashboard() {
        Map<String, List<Map<String, Object>>> result = new HashMap<>();

        LocalDate today = LocalDate.now();

        // Get the first day of the current month
        LocalDate monthStart = today.withDayOfMonth(1);

        List<Map<String, Object>> passportExpiry = staffDetailsRepository.getPassportExpiry();
        List<Map<String, Object>> visaExpiry = staffDetailsRepository.getVisaExpiry();
        List<Map<String, Object>> visaExpired = staffDetailsRepository.getVisaExpired();
        List<Map<String, Object>> leaveOverdue = staffVacationRepository.getLeaveOverdue(today);
        List<Map<String, Object>> newJoinees = staffDetailsRepository.getNewJoiness(monthStart);

        result.put("passportExpiry", passportExpiry);
        result.put("visaExpiry", visaExpiry);
        result.put("visaExpired", visaExpired);
        result.put("leaveOverdue", leaveOverdue);
        result.put("newJoinees", newJoinees);

        return result;
    }

    public Map<String, List<Map<String, String>>> getLeavelist() {
        Map<String, List<Map<String, String>>> result1 = new HashMap<>();
        List<Map<String, String>> result = new ArrayList<>();
        List<Map<String, Object>> staffList = staffDetailsRepository.getStaff();
        LocalDate today = LocalDate.now();

        if (!staffList.isEmpty() && (staffList != null)) {
            for (Map<String, Object> staff : staffList) {
                Map<String, String> temp = new HashMap<>();

                temp.put("id", String.valueOf(staff.get("id")));
                temp.put("full_name", String.valueOf(staff.get("full_name")));
                if (staff.get("assignment_sts") == null) {
                    temp.put("assignment_sts", "");
                } else {
                    temp.put("assignment_sts", String.valueOf(staff.get("assignment_sts")));
                }
                temp.put("branch", String.valueOf(staff.get("branch")));
                temp.put("phone", String.valueOf(staff.get("phone")));
                temp.put("date_employed", String.valueOf(staff.get("date_employed")));

                if (today.isAfter(LocalDate.parse(String.valueOf(staff.get("license_expiry_date"))))) {
                    temp.put("license_expiry_date", "YES");
                } else {
                    temp.put("license_expiry_date", "NO");
                }

                String staffId = String.valueOf(staff.get("id"));
                Map<String, Object> checkLeave = staffVacationRepository.getCheckLeave(Integer.parseInt(staffId));

                if (!checkLeave.isEmpty() && (checkLeave != null)) {
                    if (today.isBefore(LocalDate.parse(String.valueOf(checkLeave.get("leave_from")))) && today.isAfter(LocalDate.parse(String.valueOf(checkLeave.get("leave_to")))) && Integer.parseInt(String.valueOf(checkLeave.get("appoval_status"))) == 1) {
                        temp.put("job_sts", "Leave");
                    } else {
                        temp.put("job_sts", "Active");
                    }

                    if (Integer.parseInt(String.valueOf(checkLeave.get("appoval_status"))) == 1 && Integer.parseInt(String.valueOf(checkLeave.get("status"))) == 1) {
                        temp.put("leave_approval", "Approved");
                    } else if (Integer.parseInt(String.valueOf(checkLeave.get("appoval_status"))) == 0 && Integer.parseInt(String.valueOf(checkLeave.get("status"))) == 1) {
                        temp.put("leave_approval", "Pending");
                    } else if (Integer.parseInt(String.valueOf(checkLeave.get("appoval_status"))) == 2 && Integer.parseInt(String.valueOf(checkLeave.get("status"))) == 1) {
                        temp.put("leave_approval", "Rejected");
                    } else {
                        temp.put("leave_approval", "");
                    }
                } else {
                    temp.put("job_sts", "Active");
                    temp.put("leave_approval", "");
                }

                result.add(temp);
            }
        }

        result1.put("result", result);

        return result1;
    }

    public Map<String, List<Map<String, String>>> getSearch(String staffId) {
        List<Map<String, String>> vacationDetails = staffVacationRepository.getVacationDetails(Integer.parseInt(staffId));
        List<Map<String, String>> joinDetails = staffDetailsRepository.getJoinDetails(Integer.parseInt(staffId));

        Map<String, List<Map<String, String>>> result = new HashMap<>();
        result.put("vacationdetails", vacationDetails);
        result.put("joindetails", joinDetails);

        return result;
    }

    public Map<String, List<Map<String, String>>> getStaffList() {
        Map<String, List<Map<String, String>>> result = new HashMap<>();
        List<Map<String, String>> result1 = new ArrayList<>();

        List<Map<String, Object>> staffList = staffDetailsRepository.getStaffList();
        LocalDate today = LocalDate.now();

        if (!staffList.isEmpty() && (staffList != null)) {
            for (Map<String, Object> staff : staffList) {
                Map<String, String> temp = new HashMap<>();

                if (staff.get("license_expiry_date") == null) {
                    temp.put("license_expiry_date", "NO");
                } else {
                    if (today.isAfter(LocalDate.parse(String.valueOf(staff.get("license_expiry_date"))))) {
                        temp.put("license_expiry_date", "YES");
                    } else {
                        temp.put("license_expiry_date", "NO");
                    }
                }

                String staffId = String.valueOf(staff.get("id"));
                Map<String, Object> checkLeave = staffVacationRepository.getCheckLeave(Integer.parseInt(staffId));

                if (!checkLeave.isEmpty() && (checkLeave != null)) {
                    if (today.isBefore(LocalDate.parse(String.valueOf(checkLeave.get("leave_from")))) && today.isAfter(LocalDate.parse(String.valueOf(checkLeave.get("leave_to")))) && Integer.parseInt(String.valueOf(checkLeave.get("appoval_status"))) == 1) {
                        temp.put("job_sts", "Leave");
                    } else {
                        temp.put("job_sts", "Active");
                    }

                    if (Integer.parseInt(String.valueOf(checkLeave.get("appoval_status"))) == 1 && Integer.parseInt(String.valueOf(checkLeave.get("status"))) == 1) {
                        temp.put("leave_approval", "Approved");
                    } else if (Integer.parseInt(String.valueOf(checkLeave.get("appoval_status"))) == 0 && Integer.parseInt(String.valueOf(checkLeave.get("status"))) == 1) {
                        temp.put("leave_approval", "Pending");
                    } else if (Integer.parseInt(String.valueOf(checkLeave.get("appoval_status"))) == 2 && Integer.parseInt(String.valueOf(checkLeave.get("status"))) == 1) {
                        temp.put("leave_approval", "Rejected");
                    } else {
                        temp.put("leave_approval", "");
                    }
                } else {
                    temp.put("job_sts", "Active");
                    temp.put("leave_approval", "");
                }

                temp.put("branch", String.valueOf(staff.get("branch")));
                temp.put("common_name", String.valueOf(staff.get("common_name")));
                temp.put("date_employed", String.valueOf(staff.get("date_employed")));
                temp.put("email", String.valueOf(staff.get("email")));
                temp.put("employee_number", String.valueOf(staff.get("employee_number")));
                temp.put("full_name", String.valueOf(staff.get("full_name")));
                temp.put("id", String.valueOf(staff.get("id")));
                temp.put("license_type", String.valueOf(staff.get("license_type")));
                temp.put("passport_number", String.valueOf(staff.get("passport_number")));
                temp.put("pdrp_expiry_date", String.valueOf(staff.get("pdrp_expiry_date")));
                temp.put("phone", String.valueOf(staff.get("phone")));
                temp.put("status", String.valueOf(staff.get("status")));
                temp.put("work_permit_expiry", String.valueOf(staff.get("work_permit_expiry")));

                result1.add(temp);
            }
        }

        result.put("result", result1);

        return result;
    }

    public Map<String, List<Map<String, String>>> deleteStaff(String staffId) {
        try {
            staffDetailsRepository.deleteStaff(Integer.parseInt(staffId));

            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer updateLeave(String user, String leave_from, String leave_to, String notes, String leave_type,
                                          String staff_id, String leave_form, String settlement_form, String id,
                                          String staff_name, String change_date, String date_employed) {
        try {
            List<Map<String, Object>> check = staffVacationRepository.getDataWithStatus(Integer.parseInt(id));

            if (!check.isEmpty() && (check != null)) {
                staffVacationRepository.updateDataWithStaffId(Integer.parseInt(id));
            }

            LocalDate today = LocalDate.now();

            StaffVacation staffVacation = new StaffVacation();

            staffVacation.setLeave_type(leave_type);
            staffVacation.setLeave_from(LocalDate.parse(leave_from));
            staffVacation.setLeave_to(LocalDate.parse(leave_to));
            staffVacation.setLeave_form("");
            staffVacation.setAppoval_status(0);
            staffVacation.setSettlement_form("");
            staffVacation.setStatus(1);
            staffVacation.setNotes(notes);
            staffVacation.setUpdated_by(Integer.parseInt(user));
            staffVacation.setUpdated_date(today);
            staffVacation.setRejoin_date(date_employed);
            staffVacation.setStaff_id(Integer.parseInt(staff_id));
            staffVacation.setLeave_form(leave_form);
            staffVacation.setSettlement_form(settlement_form);

            StaffVacation staffVacation1 = staffVacationRepository.save(staffVacation);

            return staffVacation1.getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer addStaff(String user, String branch, String designation, String first_name, String last_name, String email, String employee_number,
                            String mobile_number, String emp_date, String citizenship, String passport_number, String passport_expiry,
                            String license_type, String license_expiry, String change_date, String work_permit_number, String work_permit_expiry,
                            String dob, String eid_number, String license_number, String visa_type, String own_vehicle, String home_contact,
                            String home_contact_no, String res_address, String res_city, String notes, String passport_doc) {
        try {
            if (first_name != "" && first_name != null) {
                StaffDetails staffDetails = new StaffDetails();
                LocalDate today = LocalDate.now();

                staffDetails.setCommon_name("");
                staffDetails.setBranch(branch);
                staffDetails.setFirstName(first_name);
                staffDetails.setLastName(last_name);
                staffDetails.setEmployee_number(employee_number);
                staffDetails.setPhone(mobile_number);
                staffDetails.setHome_contact(home_contact);
                staffDetails.setHome_contact_no(home_contact_no);
                staffDetails.setDate_employed(LocalDate.parse(emp_date));
                staffDetails.setCitizenship(citizenship);
                staffDetails.setPassport_number(passport_number);
                staffDetails.setChange_date(change_date);
                staffDetails.setLicense_type(license_type);
                if (license_expiry != "" && license_expiry != null) {
                    staffDetails.setLicense_expiry_date(LocalDate.parse(license_expiry));
                } else {
                    staffDetails.setLicense_expiry_date(null);
                }
                if (passport_expiry != "" && passport_expiry != null) {
                    staffDetails.setPdrp_expiry_date(LocalDate.parse(passport_expiry));
                } else {
                    staffDetails.setPdrp_expiry_date(null);
                }
                staffDetails.setWork_permit_number(work_permit_number);
                if (work_permit_expiry != "" && work_permit_expiry != null) {
                    staffDetails.setWork_permit_expiry(LocalDate.parse(work_permit_expiry));
                } else {
                    staffDetails.setWork_permit_expiry(null);
                }
                staffDetails.setDesignation(designation);
                staffDetails.setEmail(email);
                staffDetails.setRes_address(res_address);
                staffDetails.setRes_city(res_city);
                staffDetails.setNotes(notes);
                staffDetails.setPassport("");
                staffDetails.setVisa_type(visa_type);
                staffDetails.setOwn_vehicle(own_vehicle);
                staffDetails.setVisa("");
                staffDetails.setDriving_license("");
                staffDetails.setEmirates_id("");
                staffDetails.setPhoto("");
                staffDetails.setLabour_contract("");
                staffDetails.setOther_doc("");
                if (dob != "" && dob != null) {
                    staffDetails.setDob(LocalDate.parse(dob));
                } else {
                    staffDetails.setDob(null);
                }
                staffDetails.setEmirates_id_no(eid_number);
                staffDetails.setLicense_no(license_number);
                staffDetails.setStatus(1);
                staffDetails.setUpdated_by(Integer.parseInt(user));
                staffDetails.setUpdated_date(String.valueOf(today));
                staffDetails.setCtc("");

                StaffDetails staffDetails1 = staffDetailsRepository.save(staffDetails);

                return staffDetails1.getId();
            }
            else return 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, List<Map<String, String>>> getBranch() {
        Map<String, List<Map<String, String>>> result = new HashMap<>();
        List<Map<String, String>> result1 = staffBranchRepository.getBranch();

        result.put("result", result1);
        return result;
    }

    public Map<String, String> updateStaff(String user, String branch, String first_name, String last_name, String common_name, String employee_number,
                               String mobile_number, String emp_date, String citizenship, String passport_number, String passport_expiry,
                               String license_type, String license_expiry, String change_date, String work_permit_number, String work_permit_expiry,
                               String dob, String eid_number, String license_number, String visa_type, String own_vehicle, String home_contact,
                               String home_contact_no, String res_address, String res_city, String notes, String ctc, String designation,
                               String email, String staffid) {
        try {
            LocalDate today = LocalDate.now();
            Map<String, String> result = new HashMap<>();

            if (first_name != "") {
                staffDetailsRepository.findById(Long.parseLong(staffid)).map(existingData -> {
                    existingData.setBranch(branch);
                    existingData.setFirstName(first_name);
                    existingData.setDesignation(designation);
                    existingData.setEmail(email);
                    existingData.setLastName(last_name);
                    existingData.setCommon_name(common_name);
                    existingData.setEmployee_number(employee_number);
                    existingData.setPhone(mobile_number);
                    existingData.setHome_contact(home_contact);
                    existingData.setHome_contact_no(home_contact_no);
                    if (emp_date == null || emp_date == "") {
                        existingData.setDate_employed(null);
                    } else {
                        existingData.setDate_employed(LocalDate.parse(emp_date));
                    }
                    existingData.setCitizenship(citizenship);
                    existingData.setPassport_number(passport_number);
                    existingData.setChange_date(change_date);
                    existingData.setLicense_type(license_type);
                    if (license_expiry == null || license_expiry == "") {
                        existingData.setLicense_expiry_date(null);
                    } else {
                        existingData.setLicense_expiry_date(LocalDate.parse(license_expiry));
                    }
                    if (passport_expiry == null || passport_expiry == "") {
                        existingData.setPdrp_expiry_date(null);
                    } else {
                        existingData.setPdrp_expiry_date(LocalDate.parse(passport_expiry));
                    }
                    existingData.setWork_permit_number(work_permit_number);
                    if (work_permit_expiry == null || work_permit_expiry == "") {
                        existingData.setWork_permit_expiry(null);
                    } else {
                        existingData.setWork_permit_expiry(LocalDate.parse(work_permit_expiry));
                    }
                    existingData.setCtc(ctc);
                    existingData.setRes_address(res_address);
                    existingData.setRes_city(res_city);
                    existingData.setNotes(notes);
                    existingData.setVisa_type(visa_type);
                    existingData.setOwn_vehicle(own_vehicle);
                    if (dob == null || dob == "") {
                        existingData.setDob(null);
                    } else {
                        existingData.setDob(LocalDate.parse(dob));
                    }
                    existingData.setEmirates_id_no(eid_number);
                    existingData.setLicense_no(license_number);
                    existingData.setStatus(1);
                    existingData.setUpdated_by(Integer.parseInt(user));
                    existingData.setUpdated_date(String.valueOf(today));

                    return staffDetailsRepository.save(existingData);
                });

                result.put("msg", "Details Updated Successfully");
                result.put("status", "1");
            } else {
                result.put("msg", "Unable to Update. Pleae try again");
                result.put("status", "1");
            }

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
