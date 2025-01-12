package com.example.springboot.repository;

import com.example.springboot.entity.CustomerBranch;
import com.example.springboot.entity.DriverDetails;
import com.example.springboot.entity.DriverVisaApplication;
import com.example.springboot.dto.DriverDetailsDTO;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public interface DriverDetailsRepository extends JpaRepository<DriverDetails, Long> {

    @Query("SELECT COUNT(d.id) FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.status = 1")
    Long countByStatusAndBranchIn();

    @Query("SELECT COUNT(d.id) FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.status = 1 AND c.id = :userBranch") // Use c.id for branch comparison
    Long countByStatusAndBranch(@Param("userBranch") String userBranch);

    @Query("SELECT COUNT(*) FROM (SELECT d.id AS driverId FROM DriverDetails d " +
            "LEFT JOIN VehicleAssignment a ON a.driverId = d.id " +
            "LEFT JOIN CustomerBranch c ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.id NOT IN (SELECT v.driverId FROM VehicleAssignment v WHERE v.status = 1) " +
            "AND d.status = 1 GROUP BY d.id) AS subquery")
    Long countNumber();

    @Query("SELECT COUNT(*) FROM (SELECT d.id AS driverId FROM DriverDetails d " +
            "LEFT JOIN VehicleAssignment a ON a.driverId = d.id " +
            "LEFT JOIN CustomerBranch c ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.id NOT IN (SELECT v.driverId FROM VehicleAssignment v WHERE v.status = 1) " +
            "AND d.status = 1 AND d.branch = :userBranch GROUP BY d.id) AS subquery")
    Long countNumberByBranch(@Param("userBranch") String userBranch); // Ensure the type matches

    @Query("SELECT d.firstName, d.lastName, d.licenseExpiryDate " +
            "FROM DriverDetails d " +
            "WHERE d.status = 1 AND d.licenseExpiryDate BETWEEN " +
            ":startDate AND :endDate")
    List<Object[]> findLicenseExpiringSoon(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT d.firstName, d.lastName, d.licenseExpiryDate " +
            "FROM DriverDetails d " +
            "WHERE d.status = 1 AND d.branch IN(:branch) AND d.licenseExpiryDate BETWEEN " +
            ":startDate AND :endDate")
    List<Object[]> findLicenseExpiringSoonWithBranch(@Param("branch") String branch, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT d.firstName, d.lastName, d.licenseExpiryDate FROM DriverDetails d WHERE d.status = 1 AND d.licenseExpiryDate < :currentDate")
    List<Object[]> findLicenseExpired(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT d.firstName, d.lastName, d.licenseExpiryDate FROM DriverDetails d WHERE d.status = 1 AND d.branch IN(:branch) AND d.licenseExpiryDate < :currentDate")
    List<Object[]> findLicenseExpiredWithBranch(@Param("branch") String branch, @Param("currentDate") LocalDate currentDate);

    @Query("SELECT d.firstName, d.lastName, d.workPermitExpiry FROM DriverDetails d " +
            "WHERE d.status = 1 " +
            "AND d.workPermitExpiry BETWEEN :startDate AND :endDate")
    List<Object[]> findVisaExpiry(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT d.firstName, d.lastName, d.workPermitExpiry FROM DriverDetails d " +
            "WHERE d.status = 1 AND d.branch IN(:branch) " +
            "AND d.workPermitExpiry BETWEEN :startDate AND :endDate")
    List<Object[]> findVisaExpiryWithBranch(@Param("branch") String branch, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT d.firstName, d.lastName, d.pdrpExpiryDate FROM DriverDetails d WHERE d.status = 1 AND d.pdrpExpiryDate BETWEEN :startDate AND :endDate")
    List<Object[]> findPassportExpiry(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT d.firstName, d.lastName, d.pdrpExpiryDate FROM DriverDetails d WHERE d.status = 1 AND d.branch IN(:branch) AND d.pdrpExpiryDate BETWEEN :startDate AND :endDate")
    List<Object[]> findPassportExpiryWithBranch(@Param("branch") String branch, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT d.firstName, d.lastName, d.citizenship, d.dateEmployed FROM DriverDetails d WHERE d.status = 1 AND d.dateEmployed BETWEEN :monthStart AND :currentDate")
    List<Object[]> findNewJoiness(@Param("monthStart") LocalDate monthStart, @Param("currentDate") LocalDate currentDate);

    @Query("SELECT new com.example.springboot.dto.DriverDetailsDTO(d, c.branch) " +
            "FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c " +
            "ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.status = 1")
    List<DriverDetailsDTO> findByStatusAndBranch();

    @Query("SELECT new com.example.springboot.dto.DriverDetailsDTO(d, c.branch) " +
            "FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c " +
            "ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.status = 1 AND d.dateEmployed >= :startDate AND d.dateEmployed <= :endDate")
    List<DriverDetailsDTO> findByStatusAndBranchWithDay(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT new com.example.springboot.dto.DriverDetailsDTO(d, c.branch) " +
            "FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c " +
            "ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.status = 0 AND d.passportNumber NOT IN (SELECT dr.passportNumber FROM DriverDetails dr WHERE dr.status=1)")
    List<DriverDetailsDTO> findInactiveDrivers();

    @Query("SELECT new com.example.springboot.dto.DriverDetailsDTO(d, c.branch) " +
            "FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c " +
            "ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.status = 0 AND d.passportNumber NOT IN (SELECT dr.passportNumber FROM DriverDetails dr WHERE dr.status=1) " +
            "AND d.dateEmployed >= :startDate AND d.dateEmployed <= :endDate")
    List<DriverDetailsDTO> findInactiveDriversWithDay(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT new com.example.springboot.dto.DriverDetailsDTO(d, c.branch) " +
            "FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c " +
            "ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.status = 1 AND d.branch IN (:userBranch)")
    List<DriverDetailsDTO> findByStatusAndBranchWithBranch(@Param("userBranch") String userBranch);

    @Query("SELECT new com.example.springboot.dto.DriverDetailsDTO(d, c.branch) " +
            "FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c " +
            "ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.status = 1 AND d.branch IN (:userBranch) " +
            "AND d.dateEmployed >= :startDate AND d.dateEmployed <= :endDate")
    List<DriverDetailsDTO> findByStatusAndBranchWithBranchWithDay(@Param("userBranch") String userBranch, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT new com.example.springboot.dto.DriverDetailsDTO(d, c.branch) " +
            "FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c " +
            "ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.status = 0 AND d.passportNumber NOT IN (SELECT dr.passportNumber FROM DriverDetails dr WHERE dr.status=1) " +
            "AND d.branch IN (:userBranch)")
    List<DriverDetailsDTO> findInactiveDriversWithBranch(@Param("userBranch") String userBranch);

    @Query("SELECT new com.example.springboot.dto.DriverDetailsDTO(d, c.branch) " +
            "FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c " +
            "ON CAST(d.branch AS integer) = c.id " +
            "WHERE d.status = 0 AND d.passportNumber NOT IN (SELECT dr.passportNumber FROM DriverDetails dr WHERE dr.status=1) " +
            "AND d.branch IN (:userBranch) AND d.dateEmployed >= :startDate AND d.dateEmployed <= :endDate")
    List<DriverDetailsDTO> findInactiveDriversWithBranchWithDay(@Param("userBranch") String userBranch, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT d FROM DriverDetails d " +
        "LEFT JOIN CustomerBranch c ON CAST(d.branch AS integer) = c.id " +
        "LEFT JOIN DriverVisaApplication v ON v.driverId = d.id " +
        "WHERE d.id = :driverId " +
        "ORDER BY d.id DESC LIMIT 1")
    DriverDetails findDriverDetailsById(@Param("driverId") Integer driverId);

    @Query("SELECT c FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c ON CAST(d.branch AS integer) = c.id " +
            "LEFT JOIN DriverVisaApplication v ON v.driverId = d.id " +
            "WHERE d.id = :driverId " +
            "ORDER BY d.id DESC LIMIT 1")
    CustomerBranch findCustomerBranchById(@Param("driverId") Integer driverId);

    @Query("SELECT v FROM DriverDetails d " +
            "LEFT JOIN CustomerBranch c ON CAST(d.branch AS integer) = c.id " +
            "LEFT JOIN DriverVisaApplication v ON v.driverId = d.id " +
            "WHERE d.id = :driverId " +
            "ORDER BY v.id DESC LIMIT 1")
    DriverVisaApplication findDriverVisaApplicationById(@Param("driverId") Integer driverId);

    @Query("SELECT d.id AS id, d.branch AS branch, d.firstName AS first_name, d.lastName AS last_name, d.licenseType AS license_type, d.commonName AS common_name, " +
            "d.employeeNumber AS employee_number, d.phone AS phone, d.dateEmployed AS date_employed, d.citizenship AS citizenship, d.passportNumber AS passport_number, " +
            "d.changeDate AS change_date, d.licenseExpiryDate AS license_expiry_date, d.pdrpExpiryDate AS pdrp_expiry_date, d.workPermitNumber AS work_permit_number, " +
            "d.workPermitExpiry AS work_permit_expiry, d.ctc AS ctc, d.cpk AS cpk, d.resAddress AS res_address, d.resCity AS res_city, d.notes AS notes, d.passport AS passport, d.visaType AS visa_type, " +
            "d.ownVehicle AS own_vehicle, d.visa AS visa, d.drivingLicense AS driving_license, d.emiratesId AS emirates_id, d.photo AS photo, d.labourContract AS labour_contract, " +
            "d.status AS status, d.homeContact AS home_contact, d.homeContactNo AS home_contact_no, d.emiratesIdNo AS emirates_id_no, d.licenseNo AS license_no, " +
            "d.otherDoc AS other_doc, d.dob AS dob FROM DriverDetails d WHERE d.id = :driverId")
    List<Map<String, ?>> getBasicDetailsWithDriver(@Param("driverId") Integer driverId);

    @Query(value = """
            SELECT d.id as id, CONCAT(d.first_name,' ',d.last_name) as driver_name, d.status as driverstatus, b.branch as branch 
            FROM driver_details d LEFT JOIN customer_branch b 
            ON b.id = CAST(d.branch AS SIGNED) WHERE d.status = 1 AND d.branch != '3' 
            """, nativeQuery = true)
    List<Map<String, Object>> getDriverDetailsWithBranchStatus();

    @Query(value = """
            SELECT CONCAT(first_name, ' ', last_name) as driver_name 
            FROM driver_details 
            WHERE REPLACE(phone, ' ', '') LIKE CONCAT('%', :phone, '%')
            """, nativeQuery = true)
    Map<String, Object> getDataWithPhone(@Param("phone") String phone);

    @Query(value = """
            SELECT  CONCAT(first_name,' ', last_name) as full_name, common_name as common_name, license_type as license_type,
            phone as phone, cpk as cpk, employee_number as employee_number, work_permit_expiry as work_permit_expiry, 
            date_employed as date_employed, visa_type as visa_type, pdrp_expiry_date as pdrp_expiry_date, passport_number as passport_number,
            license_expiry_date as license_expiry_date, c.branch as branch, driver_details.status as status
            FROM driver_details LEFT JOIN customer_branch c ON CAST(driver_details.branch AS SIGNED) = c.id 
            WHERE driver_details.status = 1 AND driver_details.id = :driverId
            """, nativeQuery = true)
    Map<String, Object> getDataWithStatus(@Param("driverId") Integer driverId);

    @Query(value = """
            Select id as id, CONCAT(first_name,' ',last_name) AS driver_name, passport_number as passport_number,
            change_date as change_date, date_employed as date_employed FROM driver_details  WHERE id = :driverId
            """, nativeQuery = true)
    List<Map<String, Object>> getDataWithDriverDetails(@Param("driverId") Integer driverId);

    @Query(value = """
        SELECT 
            CAST(id AS CHAR) AS id, 
            CAST(CONCAT(first_name, ' ', last_name) AS CHAR) AS driver_name 
        FROM driver_details 
        WHERE status = 1
        """, nativeQuery = true)
    List<Map<String, String>> getDrivers();

    @Query(value = """
        SELECT 
            CAST(branch AS CHAR) AS branch
        FROM driver_details 
        WHERE id = :driverId AND status = 1 ORDER BY id DESC LIMIT 1
        """, nativeQuery = true)
    List<Map<String, String>> getBranchDetails(@Param("driverId") Integer driverId);

    @Query(value = """
            SELECT id, date_employed
            FROM driver_details
            WHERE CAST(date_employed AS CHAR) LIKE CONCAT('%', :year, '%')
            GROUP BY passport_number, id
            """, nativeQuery = true)
    List<Map<String, ?>> getCouryierStat(@Param("year") String year);
}
