package com.example.springboot.service;

import com.example.springboot.repository.DriverVacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VisaApplicationService {

    @Autowired
    DriverVacationRepository driverVacationRepository;

    public List<Map<String, Object>> getNormalVisaApplications() {

        return driverVacationRepository.getNormalVisaApplications();
    }

    public List<Map<String, Object>> updateVisaApplication(String driver, String approvalStatus) {
        List<Map<String, Object>> result = new ArrayList<>();

        try {
            driverVacationRepository.updateVisaApplication(Integer.parseInt(driver), Integer.parseInt(approvalStatus));

            Map<String, Object> temp = new HashMap<>();

            temp.put("msg", "Updated Successfully");
            temp.put("status", 1);

            result.add(temp);

            return  result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getDriverVisaDetail(String driverId) {

        Map<String, String> result = new HashMap<>();
        Map<String, Object> driverVisaDetails = driverVacationRepository.getDriverVisaDetails(Integer.parseInt(driverId));

        if (driverVisaDetails != null) {
            if (driverVisaDetails.get("visa_type") != null && driverVisaDetails.get("visa_type") != "") {
                String visaType = switch (Integer.parseInt(String.valueOf(driverVisaDetails.get("visa_type")))) {
                    case 1 -> "Time Express Visa";
                    case 2 -> "Visit Visa";
                    case 3 -> "Freelance Visa";
                    case 4 -> "Cancelled";
                    case 5 -> "Other";
                    default -> "Unknown";
                };
                result.put("visa_type", visaType);
            } else {
                result.put("visa_type", "");
            }

            result.put("id", String.valueOf(driverVisaDetails.get("id")));
            result.put("driver_name", String.valueOf(driverVisaDetails.get("driver_name")));
            result.put("phone", String.valueOf(driverVisaDetails.get("phone")));
            result.put("passport_number", String.valueOf(driverVisaDetails.get("passport_number")));
            result.put("date_employed", String.valueOf(driverVisaDetails.get("date_employed")));
            result.put("branch", String.valueOf(driverVisaDetails.get("branch")));
            if (String.valueOf(driverVisaDetails.get("notes")) == "null") {
                result.put("notes", "");
            } else {
                result.put("notes", String.valueOf(driverVisaDetails.get("notes")));
            }
            result.put("applied_for", String.valueOf(driverVisaDetails.get("applied_for")));
            result.put("approval_status", String.valueOf(driverVisaDetails.get("approval_status")));
        }

        return result;
    }
}
