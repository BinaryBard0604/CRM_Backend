package com.example.springboot.service;

import com.example.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class DashboardService {

    @Autowired
    private DriverDetailsRepository driverDetailsRepository;

    @Autowired
    private VehicleAssignmentRepository vehicleAssignmentRepository;

    @Autowired
    private VehicleDetailsRepository vehicleDetailsRepository;

    @Autowired
    private DriverVacationRepository driverVacationRepository;

    @Autowired
    private StaffDetailsRepository staffDetailsRepository;

    @Autowired
    private StaffVacationRepository staffVacationRepository;

    public long getDriverCount(int userRole, int userBranch) {
        if (userRole == 1 || userBranch == 0) {
            return driverDetailsRepository.countByStatusAndBranchIn();
        } else {
            String branch = String.valueOf(userBranch);
            return driverDetailsRepository.countByStatusAndBranch(branch);
        }
    }

    public long getVehicleCount(int userRole, int userBranch) {
        if (userRole == 1 || userBranch == 0) {
            return vehicleAssignmentRepository.countNumber();
        } else {
            String branch = String.valueOf(userBranch);
            return vehicleAssignmentRepository.countNumberByBranch(branch);
        }
    }

    public long getTotalVehicleCount() {
        return vehicleDetailsRepository.countDistinctByStatus();
    }

    public long getDriverLeavesCount(int userRole, int userBranch) {
        LocalDate today = LocalDate.now();

        if (userRole == 1 || userBranch == 0) {
            return driverVacationRepository.countNumber(today);
        } else {
            String branch = String.valueOf(userBranch);
            return driverVacationRepository.countNumberByBranch(branch, today);
        }
    }

    public long getfreeVehicleCount() {
        return vehicleDetailsRepository.countFreeVehicle();
    }

    public long getIdleDriverCount(int userRole, int userBranch) {

        if (userRole == 1 || userBranch == 0) {
            return driverDetailsRepository.countNumber();
        } else {
            String branch = String.valueOf(userBranch);
            return driverDetailsRepository.countNumberByBranch(branch);
        }
    }
}