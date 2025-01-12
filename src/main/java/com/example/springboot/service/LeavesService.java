package com.example.springboot.service;

import com.example.springboot.controller.DriverController;
import com.example.springboot.repository.DriverDetailsRepository;
import com.example.springboot.repository.DriverVacationRepository;
import com.example.springboot.repository.StaffVacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeavesService {

     @Autowired
     DriverVacationRepository driverVacationRepository;

     @Autowired
     DriverDetailsRepository driverDetailsRepository;

     @Autowired
    StaffVacationRepository staffVacationRepository;

    private static final Logger logger = LoggerFactory.getLogger(LeavesService.class);

     public List<Map<String, String>> getNormalLeaves() {

         List<Map<String, Object>> driverVacationData = driverVacationRepository.getDataWithStatus();
         List<Map<String, String>> result = new ArrayList<>();

         for (Map<String, Object> row : driverVacationData) {
             Map<String, String> temp = new HashMap<>();

             temp.put("id", String.valueOf(row.get("id")));
             temp.put("driver_id", String.valueOf(row.get("driverId")));
             temp.put("appoval_status", String.valueOf(row.get("appovalStatus")));

             String driverId = String.valueOf(row.get("driverId"));
             Map<String, Object> check = driverDetailsRepository.getDataWithStatus(Integer.parseInt(driverId));

             if (check != null && !check.isEmpty()) {
                 temp.put("driver_name", String.valueOf(check.get("full_name")));
                 temp.put("phone", String.valueOf(check.get("phone")));
                 temp.put("date_employed", String.valueOf(check.get("date_employed")));
                 temp.put("branch", String.valueOf(check.get("branch")));

                 if(Integer.parseInt(String.valueOf(check.get("status"))) == 1){
                     temp.put("status", "Active");
                 }else{
                     temp.put("status", "Not Active");
                 }
             }

             result.add(temp);
         }

         return result;
     }

     public List<Map<String, String>> updateLeaves(String approvalStatus, String vacationId) {
         List<Map<String, String>> result = new ArrayList<>();
         Map<String, String> temp = new HashMap<>();

         try {
             Integer i = driverVacationRepository.updateApprovalStatus(Integer.parseInt(approvalStatus), Integer.parseInt(vacationId));

             temp.put("result", vacationId);
         } catch (Exception e) {
             temp.put("result", "0");
             throw new RuntimeException(e);
         }

         result.add(temp);
         return result;
     }

    public List<Map<String, String>> updateStaffLeave(String approvalStatus, String vacationId, String notes) {
        List<Map<String, String>> result = new ArrayList<>();
        Map<String, String> temp = new HashMap<>();

        try {
            Integer i = staffVacationRepository.updateApprovalStatus(Integer.parseInt(approvalStatus), Integer.parseInt(vacationId), notes);

            temp.put("result", vacationId);
        } catch (Exception e) {
            temp.put("result", "0");
            throw new RuntimeException(e);
        }

        result.add(temp);
        return result;
    }
}
