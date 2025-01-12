package com.example.springboot.service;

import com.example.springboot.entity.DriverDetails;
import com.example.springboot.entity.DriverVacation;
import com.example.springboot.entity.VehicleDetails;
import com.example.springboot.repository.DriverDetailsRepository;
import com.example.springboot.repository.DriverVacationRepository;
import com.example.springboot.repository.VehicleDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class DashboardNotificationService {

    @Autowired
    private DriverDetailsRepository driverDetailsRepository;

    @Autowired
    private VehicleDetailsRepository vehicleDetailsRepository;

    @Autowired
    private DriverVacationRepository driverVacationRepository;

    public List<Object[]> getLicenseExpiringSoon(Integer userBranch) {

        LocalDate startDate = LocalDate.now().withDayOfMonth(1); // First day of the current month
        LocalDate endDate = LocalDate.now().plusDays(15).withDayOfMonth(LocalDate.now().plusDays(15).lengthOfMonth()); // Last day of the next month
        if (userBranch == 0) {
            return driverDetailsRepository.findLicenseExpiringSoon(startDate, endDate);
        } else {
            String branch = String.valueOf(userBranch);
            return driverDetailsRepository.findLicenseExpiringSoonWithBranch(branch, startDate, endDate);
        }
    }

    public List<Object[]> getLicenseExpired(Integer userBranch) {

        LocalDate currentDate = LocalDate.now();
        if (userBranch == 0) {
            return driverDetailsRepository.findLicenseExpired(currentDate);
        } else {
            String branch = String.valueOf(userBranch);
            return driverDetailsRepository.findLicenseExpiredWithBranch(branch, currentDate);
        }
    }

    public List<Object[]> getVisaExpiry(Integer userBranch) {

        LocalDate startDate = LocalDate.now().withDayOfMonth(1); // First day of the current month
        LocalDate endDate = LocalDate.now().plusDays(15).withDayOfMonth(LocalDate.now().plusDays(15).lengthOfMonth()); // Last day of the next month
        if (userBranch == 0) {
            return driverDetailsRepository.findVisaExpiry(startDate, endDate);
        } else {
            String branch = String.valueOf(userBranch);
            return driverDetailsRepository.findVisaExpiryWithBranch(branch, startDate, endDate);
        }
    }

    public List<Object[]> getPassportExpiry(Integer userBranch) {

        LocalDate startDate = LocalDate.now().withDayOfMonth(1); // First day of the current month
        LocalDate endDate = LocalDate.now().plusDays(15).withDayOfMonth(LocalDate.now().plusDays(15).lengthOfMonth()); // Last day of the next month
        if (userBranch == 0) {
            return driverDetailsRepository.findPassportExpiry(startDate, endDate);
        } else {
            String branch = String.valueOf(userBranch);
            return driverDetailsRepository.findPassportExpiryWithBranch(branch, startDate, endDate);
        }
    }

    public List<Object[]> getLeaseExpiringSoon() {

        LocalDate startDate = LocalDate.now().withDayOfMonth(1); // First day of the current month
        LocalDate endDate = LocalDate.now().plusDays(15).withDayOfMonth(LocalDate.now().plusDays(15).lengthOfMonth()); // Last day of the next month
        return vehicleDetailsRepository.findLeaseExpiringSoon(startDate, endDate);
    }

    public List<Object[]> getLeaveOverdue() {

        LocalDate currentDate = LocalDate.now();
        return driverVacationRepository.findLeaveOverdue(currentDate);
    }

    public List<Object[]> getNewJoiness() {

        LocalDate monthStart = LocalDate.now().withDayOfMonth(1); // First day of the current month
        LocalDate currentDate = LocalDate.now();
        return driverDetailsRepository.findNewJoiness(monthStart, currentDate);
    }
}
