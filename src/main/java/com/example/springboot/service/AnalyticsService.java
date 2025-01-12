package com.example.springboot.service;

import com.example.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnalyticsService {

    private static final Logger logger = LoggerFactory.getLogger(AnalyticsService.class);

    @Autowired
    KmDetailsRepository kmDetailsRepository;

    @Autowired
    FuelData2021Repository fuelData2021Repository;

    @Autowired
    FuelData2022Repository fuelData2022Repository;

    @Autowired
    FuelData2023Repository fuelData2023Repository;

    @Autowired
    FuelData2024Repository fuelData2024Repository;

    @Autowired
    FuelData2025Repository fuelData2025Repository;

    @Autowired
    FuelData2026Repository fuelData2026Repository;

    @Autowired
    DriverFinalSettlementRepository driverFinalSettlementRepository;

    @Autowired
    DriverDetailsRepository driverDetailsRepository;

    @Autowired
    StaffVacationRepository staffVacationRepository;

    @Autowired
    DriverVacationRepository driverVacationRepository;

    @Autowired
    AnalyticsRepository analyticsRepository;

    public Map<String, String> getDriverVehicle(String year, String branch) {
        Map<String, String> result = new HashMap<>();

        String yearMonth1 = year + "-01";
        Map<String, ?> check1 = analyticsRepository.getDataWithYearMonth(yearMonth1, branch);
        String driver1 = String.valueOf(check1.get("total_driver"));
        String vehicle1 = String.valueOf(check1.get("total_vehicle"));

        String yearMonth2 = year + "-02";
        Map<String, ?> check2 = analyticsRepository.getDataWithYearMonth(yearMonth2, branch);
        String driver2 = String.valueOf(check2.get("total_driver"));
        String vehicle2 = String.valueOf(check2.get("total_vehicle"));

        String yearMonth3 = year + "-03";
        Map<String, ?> check3 = analyticsRepository.getDataWithYearMonth(yearMonth3, branch);
        String driver3 = String.valueOf(check3.get("total_driver"));
        String vehicle3 = String.valueOf(check3.get("total_vehicle"));

        String yearMonth4 = year + "-04";
        Map<String, ?> check4 = analyticsRepository.getDataWithYearMonth(yearMonth4, branch);
        String driver4 = String.valueOf(check4.get("total_driver"));
        String vehicle4 = String.valueOf(check4.get("total_vehicle"));

        String yearMonth5 = year + "-05";
        Map<String, ?> check5 = analyticsRepository.getDataWithYearMonth(yearMonth5, branch);
        String driver5 = String.valueOf(check5.get("total_driver"));
        String vehicle5 = String.valueOf(check5.get("total_vehicle"));

        String yearMonth6 = year + "-06";
        Map<String, ?> check6 = analyticsRepository.getDataWithYearMonth(yearMonth6, branch);
        String driver6 = String.valueOf(check6.get("total_driver"));
        String vehicle6 = String.valueOf(check6.get("total_vehicle"));

        String yearMonth7 = year + "-07";
        Map<String, ?> check7 = analyticsRepository.getDataWithYearMonth(yearMonth7, branch);
        String driver7 = String.valueOf(check7.get("total_driver"));
        String vehicle7 = String.valueOf(check7.get("total_vehicle"));

        String yearMonth8 = year + "-08";
        Map<String, ?> check8 = analyticsRepository.getDataWithYearMonth(yearMonth8, branch);
        String driver8 = String.valueOf(check8.get("total_driver"));
        String vehicle8 = String.valueOf(check8.get("total_vehicle"));

        String yearMonth9 = year + "-09";
        Map<String, ?> check9 = analyticsRepository.getDataWithYearMonth(yearMonth9, branch);
        String driver9 = String.valueOf(check9.get("total_driver"));
        String vehicle9 = String.valueOf(check9.get("total_vehicle"));

        String yearMonth10 = year + "-10";
        Map<String, ?> check10 = analyticsRepository.getDataWithYearMonth(yearMonth10, branch);
        String driver10 = String.valueOf(check10.get("total_driver"));
        String vehicle10 = String.valueOf(check10.get("total_vehicle"));

        String yearMonth11 = year + "-11";
        Map<String, ?> check11 = analyticsRepository.getDataWithYearMonth(yearMonth11, branch);
        String driver11 = String.valueOf(check11.get("total_driver"));
        String vehicle11 = String.valueOf(check11.get("total_vehicle"));

        String yearMonth12 = year + "-12";
        Map<String, ?> check12 = analyticsRepository.getDataWithYearMonth(yearMonth12, branch);
        String driver12 = String.valueOf(check12.get("total_driver"));
        String vehicle12 = String.valueOf(check12.get("total_vehicle"));

        if (driver1 == "null") driver1 = "0";
        if (driver2 == "null") driver2 = "0";
        if (driver3 == "null") driver3 = "0";
        if (driver4 == "null") driver4 = "0";
        if (driver5 == "null") driver5 = "0";
        if (driver6 == "null") driver6 = "0";
        if (driver7 == "null") driver7 = "0";
        if (driver8 == "null") driver8 = "0";
        if (driver9 == "null") driver9 = "0";
        if (driver10 == "null") driver10 = "0";
        if (driver11 == "null") driver11 = "0";
        if (driver12 == "null") driver12 = "0";

        if (vehicle1 == "null") vehicle1 = "0";
        if (vehicle2 == "null") vehicle2 = "0";
        if (vehicle3 == "null") vehicle3 = "0";
        if (vehicle4 == "null") vehicle4 = "0";
        if (vehicle5 == "null") vehicle5 = "0";
        if (vehicle6 == "null") vehicle6 = "0";
        if (vehicle7 == "null") vehicle7 = "0";
        if (vehicle8 == "null") vehicle8 = "0";
        if (vehicle9 == "null") vehicle9 = "0";
        if (vehicle10 == "null") vehicle10 = "0";
        if (vehicle11 == "null") vehicle11 = "0";
        if (vehicle12 == "null") vehicle12 = "0";

        String driver = driver1 + "*" + driver2 + "*" + driver3 + "*" +driver4 + "*" +driver5 + "*" +driver6 + "*" +driver7 + "*" +driver8 + "*" +
                driver9 + "*" +driver10 + "*" +driver11 + "*" +driver12;
        String vehicle = vehicle1 + "*" + vehicle2 + "*" + vehicle3 + "*" +vehicle4 + "*" +vehicle5 + "*" +vehicle6 + "*" +vehicle7 + "*" +vehicle8 + "*" +
                vehicle9 + "*" +vehicle10 + "*" +vehicle11 + "*" +vehicle12;

        result.put("drivers", driver);
        result.put("vehicles", vehicle);

        return result;
    }

    public Map<String, String> getLeaveApplication(String year, String branch) {
        Map<String, String> result = new HashMap<>();

        String yearMonth1 = year + "-01";
        String leave1 = "";
        if (Integer.parseInt(branch) == 0) {
            leave1 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth1).length);
        } else {
            leave1 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth1, branch).length);
        }

        String yearMonth2 = year + "-02";
        String leave2 = "";
        if (Integer.parseInt(branch) == 0) {
            leave2 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth2).length);
        } else {
            leave2 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth2, branch).length);
        }

        String yearMonth3 = year + "-03";
        String leave3 = "";
        if (Integer.parseInt(branch) == 0) {
            leave3 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth3).length);
        } else {
            leave3 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth3, branch).length);
        }

        String yearMonth4 = year + "-04";
        String leave4 = "";
        if (Integer.parseInt(branch) == 0) {
            leave4 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth4).length);
        } else {
            leave4 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth4, branch).length);
        }

        String yearMonth5 = year + "-05";
        String leave5 = "";
        if (Integer.parseInt(branch) == 0) {
            leave5 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth5).length);
        } else {
            leave5 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth5, branch).length);
        }

        String yearMonth6 = year + "-06";
        String leave6 = "";
        if (Integer.parseInt(branch) == 0) {
            leave6 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth6).length);
        } else {
            leave6 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth6, branch).length);
        }

        String yearMonth7 = year + "-07";
        String leave7 = "";
        if (Integer.parseInt(branch) == 0) {
            leave7 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth7).length);
        } else {
            leave7 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth7, branch).length);
        }

        String yearMonth8 = year + "-08";
        String leave8 = "";
        if (Integer.parseInt(branch) == 0) {
            leave8 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth8).length);
        } else {
            leave8 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth8, branch).length);
        }

        String yearMonth9 = year + "-09";
        String leave9 = "";
        if (Integer.parseInt(branch) == 0) {
            leave9 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth9).length);
        } else {
            leave9 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth9, branch).length);
        }

        String yearMonth10 = year + "-10";
        String leave10 = "";
        if (Integer.parseInt(branch) == 0) {
            leave10 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth10).length);
        } else {
            leave10 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth10, branch).length);
        }

        String yearMonth11 = year + "-11";
        String leave11 = "";
        if (Integer.parseInt(branch) == 0) {
            leave11 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth11).length);
        } else {
            leave11 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth11, branch).length);
        }

        String yearMonth12 = year + "-12";
        String leave12 = "";
        if (Integer.parseInt(branch) == 0) {
            leave12 = String.valueOf(driverVacationRepository.getDataWithYearMonth(yearMonth12).length);
        } else {
            leave12 = String.valueOf(driverVacationRepository.getDataWithYearMonthBranch(yearMonth12, branch).length);
        }

        if (leave1 == "null") leave1 = "0";
        if (leave2 == "null") leave2 = "0";
        if (leave3 == "null") leave3 = "0";
        if (leave4 == "null") leave4 = "0";
        if (leave5 == "null") leave5 = "0";
        if (leave6 == "null") leave6 = "0";
        if (leave7 == "null") leave7 = "0";
        if (leave8 == "null") leave8 = "0";
        if (leave9 == "null") leave9 = "0";
        if (leave10 == "null") leave10 = "0";
        if (leave11 == "null") leave11 = "0";
        if (leave12 == "null") leave12 = "0";

        String leaves = leave1 + "*" + leave2 + "*" + leave3 + "*" +leave4 + "*" +leave5 + "*" +leave6 + "*" +leave7 + "*" +leave8 + "*" +
                leave9 + "*" +leave10 + "*" +leave11 + "*" +leave12;

        String staffLeaves1 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth1).length);
        String staffLeaves2 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth2).length);
        String staffLeaves3 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth3).length);
        String staffLeaves4 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth4).length);
        String staffLeaves5 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth5).length);
        String staffLeaves6 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth6).length);
        String staffLeaves7 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth7).length);
        String staffLeaves8 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth8).length);
        String staffLeaves9 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth9).length);
        String staffLeaves10 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth10).length);
        String staffLeaves11 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth11).length);
        String staffLeaves12 = String.valueOf(staffVacationRepository.getApprovedStaffLeaves(yearMonth12).length);

        if (staffLeaves1 == "null") staffLeaves1 = "0";
        if (staffLeaves2 == "null") staffLeaves2 = "0";
        if (staffLeaves3 == "null") staffLeaves3 = "0";
        if (staffLeaves4 == "null") staffLeaves4 = "0";
        if (staffLeaves5 == "null") staffLeaves5 = "0";
        if (staffLeaves6 == "null") staffLeaves6 = "0";
        if (staffLeaves7 == "null") staffLeaves7 = "0";
        if (staffLeaves8 == "null") staffLeaves8 = "0";
        if (staffLeaves9 == "null") staffLeaves9 = "0";
        if (staffLeaves10 == "null") staffLeaves10 = "0";
        if (staffLeaves11 == "null") staffLeaves11 = "0";
        if (staffLeaves12 == "null") staffLeaves12 = "0";

        String staff_leaves = staffLeaves1 + "*" + staffLeaves2 + "*" + staffLeaves3 + "*" +staffLeaves4 + "*" +staffLeaves5 + "*" +staffLeaves6 + "*"
                +staffLeaves7 + "*" +staffLeaves8 + "*" + staffLeaves9 + "*" +staffLeaves10 + "*" +staffLeaves11 + "*" +staffLeaves12;

        result.put("driver_leave", leaves);
        result.put("staff_leave", staff_leaves);

        return result;
    }

    public Map<String, String> getCouryierStat(String year, String branch) {
        Map<String, String> result = new HashMap<>();

        String yearMonth1 = year + "-01";
        String join1 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth1).size());

        String yearMonth2 = year + "-02";
        String join2 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth2).size());

        String yearMonth3 = year + "-03";
        String join3 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth3).size());

        String yearMonth4 = year + "-04";
        String join4 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth4).size());

        String yearMonth5 = year + "-05";
        String join5 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth5).size());

        String yearMonth6 = year + "-06";
        String join6 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth6).size());

        String yearMonth7 = year + "-07";
        String join7 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth7).size());

        String yearMonth8 = year + "-08";
        String join8 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth8).size());

        String yearMonth9 = year + "-09";
        String join9 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth9).size());

        String yearMonth10 = year + "-10";
        String join10 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth10).size());

        String yearMonth11 = year + "-11";
        String join11 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth11).size());

        String yearMonth12 = year + "-12";
        String join12 = String.valueOf(driverDetailsRepository.getCouryierStat(yearMonth12).size());

        if (join1 == "null") join1 = "0";
        if (join2 == "null") join2 = "0";
        if (join3 == "null") join3 = "0";
        if (join4 == "null") join4 = "0";
        if (join5 == "null") join5 = "0";
        if (join6 == "null") join6 = "0";
        if (join7 == "null") join7 = "0";
        if (join8 == "null") join8 = "0";
        if (join9 == "null") join9 = "0";
        if (join10 == "null") join10 = "0";
        if (join11 == "null") join11 = "0";
        if (join12 == "null") join12 = "0";

        String joiness = join1 + "*" + join2 + "*" + join3 + "*" + join4 + "*" + join5 + "*" + join6 + "*"
                + join7 + "*" + join8 + "*" + join9 + "*" + join10 + "*" + join11 + "*" + join12;

        String resigned1 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth1).length);
        String resigned2 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth2).length);
        String resigned3 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth3).length);
        String resigned4 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth4).length);
        String resigned5 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth5).length);
        String resigned6 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth6).length);
        String resigned7 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth7).length);
        String resigned8 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth8).length);
        String resigned9 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth9).length);
        String resigned10 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth10).length);
        String resigned11 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth11).length);
        String resigned12 = String.valueOf(driverFinalSettlementRepository.getCouryierStatResigned(yearMonth12).length);

        if (resigned1 == "null") resigned1 = "0";
        if (resigned2 == "null") resigned2 = "0";
        if (resigned3 == "null") resigned3 = "0";
        if (resigned4 == "null") resigned4 = "0";
        if (resigned5 == "null") resigned5 = "0";
        if (resigned6 == "null") resigned6 = "0";
        if (resigned7 == "null") resigned7 = "0";
        if (resigned8 == "null") resigned8 = "0";
        if (resigned9 == "null") resigned9 = "0";
        if (resigned10 == "null") resigned10 = "0";
        if (resigned11 == "null") resigned11 = "0";
        if (resigned12 == "null") resigned12 = "0";

        String courier_resigned = resigned1 + "*" + resigned2 + "*" + resigned3 + "*" + resigned4 + "*" + resigned5 + "*" + resigned6 + "*"
                + resigned7 + "*" + resigned8 + "*" + resigned9 + "*" + resigned10 + "*" + resigned11 + "*" + resigned12;

        result.put("joinees", joiness);
        result.put("courier_resigned", courier_resigned);

        return result;
    }

    public Map<String, String> getFuelKm(String year, String branch) {
        Map<String, String> result = new HashMap<>();
        logger.info("--------", year, branch);

        String monthYear1 = "01-" + year;
        String join1 = getString(year, monthYear1, branch);

        String monthYear2 = "02-" + year;
        String join2 = getString(year, monthYear2, branch);

        String monthYear3 = "03-" + year;
        String join3 = getString(year, monthYear3, branch);

        String monthYear4 = "04-" + year;
        String join4 = getString(year, monthYear4, branch);

        String monthYear5 = "05-" + year;
        String join5 = getString(year, monthYear5, branch);

        String monthYear6 = "06-" + year;
        String join6 = getString(year, monthYear6, branch);

        String monthYear7 = "07-" + year;
        String join7 = getString(year, monthYear7, branch);

        String monthYear8 = "08-" + year;
        String join8 = getString(year, monthYear8, branch);

        String monthYear9 = "09-" + year;
        String join9 = getString(year, monthYear9, branch);

        String monthYear10 = "10-" + year;
        String join10 = getString(year, monthYear10, branch);

        String monthYear11 = "11-" + year;
        String join11 = getString(year, monthYear11, branch);

        String monthYear12 = "12-" + year;
        String join12 = getString(year, monthYear12, branch);

        if (join1 == "null") join1 = "0";
        if (join2 == "null") join2 = "0";
        if (join3 == "null") join3 = "0";
        if (join4 == "null") join4 = "0";
        if (join5 == "null") join5 = "0";
        if (join6 == "null") join6 = "0";
        if (join7 == "null") join7 = "0";
        if (join8 == "null") join8 = "0";
        if (join9 == "null") join9 = "0";
        if (join10 == "null") join10 = "0";
        if (join11 == "null") join11 = "0";
        if (join12 == "null") join12 = "0";

        String cost = join1 + "*" + join2 + "*" + join3 + "*" + join4 + "*" + join5 + "*" + join6 + "*"
                + join7 + "*" + join8 + "*" + join9 + "*" + join10 + "*" + join11 + "*" + join12;

        String yearMonth1 = year + "-01";
        String km1 = getKmString(yearMonth1, branch);

        String yearMonth2 = year + "-02";
        String km2 = getKmString(yearMonth2, branch);

        String yearMonth3 = year + "-03";
        String km3 = getKmString(yearMonth3, branch);

        String yearMonth4 = year + "-04";
        String km4 = getKmString(yearMonth4, branch);

        String yearMonth5 = year + "-05";
        String km5 = getKmString(yearMonth5, branch);

        String yearMonth6 = year + "-06";
        String km6 = getKmString(yearMonth6, branch);

        String yearMonth7 = year + "-07";
        String km7 = getKmString(yearMonth7, branch);

        String yearMonth8 = year + "-08";
        String km8 = getKmString(yearMonth8, branch);

        String yearMonth9 = year + "-09";
        String km9 = getKmString(yearMonth9, branch);

        String yearMonth10 = year + "-10";
        String km10 = getKmString(yearMonth10, branch);

        String yearMonth11 = year + "-11";
        String km11 = getKmString(yearMonth11, branch);

        String yearMonth12 = year + "-12";
        String km12 = getKmString(yearMonth12, branch);

        if (km1 == "null") km1 = "0";
        if (km2 == "null") km2 = "0";
        if (km3 == "null") km3 = "0";
        if (km4 == "null") km4 = "0";
        if (km5 == "null") km5 = "0";
        if (km6 == "null") km6 = "0";
        if (km7 == "null") km7 = "0";
        if (km8 == "null") km8 = "0";
        if (km9 == "null") km9 = "0";
        if (km10 == "null") km10 = "0";
        if (km11 == "null") km11 = "0";
        if (km12 == "null") km12 = "0";

        String kms = km1 + "*" + km2 + "*" + km3 + "*" + km4 + "*" + km5 + "*" + km6 + "*"
                + km7 + "*" + km8 + "*" + km9 + "*" + km10 + "*" + km11 + "*" + km12;

        result.put("cost", cost);
        result.put("kms", kms);

        return result;
    }

    private String getString(String year, String monthYear, String branch) {

        if (Integer.parseInt(year) == 2021) {
            if (Integer.parseInt(branch) > 0) {
                return String.valueOf(fuelData2021Repository.getTotalAmountWithBranch(monthYear, branch).get("total_cost"));
            } else {
                return String.valueOf(fuelData2021Repository.getTotalAmountWithBranch1(monthYear).get("total_cost"));
            }
        } else if (Integer.parseInt(year) == 2022) {
            if (Integer.parseInt(branch) > 0) {
                return String.valueOf(fuelData2022Repository.getTotalAmountWithBranch(monthYear, branch).get("total_cost"));
            } else {
                return String.valueOf(fuelData2022Repository.getTotalAmountWithBranch1(monthYear).get("total_cost"));
            }
        } else if (Integer.parseInt(year) == 2023) {
            if (Integer.parseInt(branch) > 0) {
                return String.valueOf(fuelData2023Repository.getTotalAmountWithBranch(monthYear, branch).get("total_cost"));
            } else {
                return String.valueOf(fuelData2023Repository.getTotalAmountWithBranch1(monthYear).get("total_cost"));
            }
        } else if (Integer.parseInt(year) == 2024) {
            if (Integer.parseInt(branch) > 0) {
                return String.valueOf(fuelData2024Repository.getTotalAmountWithBranch(monthYear, branch).get("total_cost"));
            } else {
                return String.valueOf(fuelData2024Repository.getTotalAmountWithBranch1(monthYear).get("total_cost"));
            }
        } else if (Integer.parseInt(year) == 2025) {
            if (Integer.parseInt(branch) > 0) {
                return String.valueOf(fuelData2025Repository.getTotalAmountWithBranch(monthYear, branch).get("total_cost"));
            } else {
                return String.valueOf(fuelData2025Repository.getTotalAmountWithBranch1(monthYear).get("total_cost"));
            }
        } else if (Integer.parseInt(year) == 2026) {
            if (Integer.parseInt(branch) > 0) {
                return String.valueOf(fuelData2026Repository.getTotalAmountWithBranch(monthYear, branch).get("total_cost"));
            } else {
                return String.valueOf(fuelData2026Repository.getTotalAmountWithBranch1(monthYear).get("total_cost"));
            }
        } else {
            return null;
        }
    }

    private String getKmString(String yearMonth, String branch) {
        if (Integer.parseInt(branch) > 0) {
            return String.valueOf(kmDetailsRepository.getTotalKmWithBranch(yearMonth, Integer.parseInt(branch)).get("total_km"));
        } else {
            return String.valueOf(kmDetailsRepository.getTotalKm(yearMonth).get("total_km"));
        }
    }
}
