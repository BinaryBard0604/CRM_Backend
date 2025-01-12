package com.example.springboot.repository;

import com.example.springboot.entity.DriverVacation;
import jakarta.transaction.Transactional;
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
import java.util.Optional;

@Repository
public interface DriverVacationRepository extends JpaRepository<DriverVacation, Long> {

    @Query("SELECT COUNT(v.driverId) FROM DriverVacation v " +
            "WHERE v.status = 1 " +
            "AND v.appovalStatus = 1 " +  // Corrected the typo here
            "AND v.leaveFrom <= :today " +
            "AND v.rejoinDate = '0' " +  // Ensure this is the correct type
            "AND v.driverId IN (SELECT d.id FROM DriverDetails d WHERE d.status = 1)")
    Long countNumber(@Param("today") LocalDate today);

    @Query("SELECT COUNT(v.driverId) FROM DriverVacation v " +
            "WHERE v.status = 1 " +
            "AND v.appovalStatus = 1 " +  // Corrected the typo here
            "AND v.leaveFrom <= :today " +
            "AND v.rejoinDate = '0' " +  // Ensure this is the correct type
            "AND v.driverId IN (SELECT d.id FROM DriverDetails d WHERE d.status = 1 AND branch = :branch)")
    Long countNumberByBranch(@Param("branch") String branch, @Param("today") LocalDate today);

    @Query("SELECT v.driverId, v.leaveFrom, v.leaveTo, d.firstName, d.lastName, v.rejoinDate " +
            "FROM DriverVacation v " +
            "LEFT JOIN DriverDetails d ON d.id = v.driverId " +
            "WHERE v.status = 1 AND v.appovalStatus = 1 AND v.rejoinDate = '0' " +
            "AND v.leaveFrom < :currentDate AND v.leaveTo < :currentDate " +
            "AND v.driverId IN (SELECT d.id FROM DriverDetails d WHERE d.status = 1)")
    List<Object[]> findLeaveOverdue(@Param("currentDate") LocalDate currentDate);

    @Query("SELECT v FROM DriverVacation v WHERE v.driverId IN :driverIds AND v.rejoinDate = '0' ORDER BY v.id DESC")
    List<DriverVacation> findLatestVacationsByDrivers(@Param("driverIds") List<Integer> driverIds);

    @Query("SELECT v FROM DriverVacation v WHERE v.driverId = :driverId AND v.status = 1 ORDER BY v.id DESC")
    List<DriverVacation> findByDriverIdAndStatusOrderByIdDesc(@Param("driverId") Long driverId);

    @Query("SELECT d FROM DriverVacation d WHERE d.driverId = :driverId AND d.status = 1")
    Optional<DriverVacation> findDriverIdWithStatus(@Param("driverId") Integer driverId);

    @Modifying
    @Transactional
    @Query("UPDATE DriverVacation dv SET dv.status = 0 WHERE dv.driverId = :driverId")
    void deactivateVacationByDriverId(@Param("driverId") Integer driverId);

    @Query(value = """
            SELECT DISTINCT driver_id as driverId, appoval_status as appovalStatus, id as id 
            FROM driver_vacation WHERE appoval_status = 0 AND status = 1 AND leave_to ORDER BY id DESC
            """, nativeQuery = true)
    List<Map<String, Object>> getDataWithStatus();

    @Query(value = """
            Select driver_vacation.id as id, driver_id as driver_id, leave_from as leave_from, leave_to as leave_to,
            leave_type as leave_type, leave_form as leave_form, appoval_status as appoval_status, rejoin_date as rejoin_date,
            settlement_form as settlement_form, driver_vacation.notes as notes, driver_vacation.status as status 
            FROM driver_vacation WHERE driver_id = :driverId AND status=1 ORDER BY driver_vacation.id DESC
            """, nativeQuery = true)
    List<Map<String, Object>> getDataWithDriverVacaition(@Param("driverId") Integer driverId);

    @Modifying
    @Transactional
    @Query("UPDATE DriverVacation dv SET dv.appovalStatus = :approvalStatus WHERE dv.id = :vacationId")
    Integer updateApprovalStatus(@Param("approvalStatus") Integer approvalStatus, @Param("vacationId") Integer vacationId);

    @Query(value = """
            SELECT v.driver_id as driver_id, v.id as id, v.applied_for as applied_for, v.notes as notes,
            v.approval_status as approval_status, d.date_employed as date_employed, d.status as status,
            c.branch as branch, CONCAT(d.first_name,' ',d.last_name) as driver_name, d.phone as phone
            FROM driver_visa_application v LEFT JOIN driver_details d on v.driver_id=d.id 
            LEFT JOIN customer_branch c on c.id=d.branch WHERE v.approval_status=0
            """, nativeQuery = true)
    List<Map<String, Object>> getNormalVisaApplications();

    @Modifying
    @Transactional
    @Query("UPDATE DriverVisaApplication dv SET dv.approvalStatus = :approvalStatus WHERE dv.driverId = :driver")
    void updateVisaApplication(@Param("driver") Integer driver, @Param("approvalStatus") Integer approvalStatus);

    @Query(value = """
            SELECT d.id,
                   CONCAT(d.first_name, ' ', d.last_name) AS driver_name,
                   d.phone,
                   d.passport_number,
                   d.date_employed,
                   d.visa_type,
                   c.branch,
                   v.notes,
                   v.applied_for,
                   v.approval_status 
            FROM driver_details d 
            LEFT JOIN customer_branch c ON d.branch = c.id 
            LEFT JOIN driver_visa_application v ON v.driver_id = d.id 
            WHERE d.id = :driverId 
            ORDER BY v.id DESC 
            LIMIT 1
            """, nativeQuery = true)
    Map<String, Object> getDriverVisaDetails(@Param("driverId") Integer driverId);

    @Query(value = """
            Select driver_vacation.id, driver_id, leave_from, leave_to, leave_type, leave_form, appoval_status, d.change_date,
            d.date_employed, rejoin_date, settlement_form, driver_vacation.notes, driver_vacation.status 
            FROM driver_vacation LEFT JOIN driver_details d ON d.id = driver_vacation.driver_id 
            WHERE driver_id = :driverId AND rejoin_date ='0' ORDER BY driver_vacation.id DESC
            """, nativeQuery = true)
    List<Map<String, ?>> getDriverVacattionWithDriverId(@Param("driverId") Integer driverId);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE driver_vacation SET rejoin_date = :rejoinDate, updated_by = :user 
            WHERE driver_id = :driverId AND id = :vacationId
            """, nativeQuery = true)
    void closeDriverLeaves  (@Param("driverId") Integer driverId, @Param("rejoinDate") String rejoinDate,
                         @Param("vacationId") Integer vacationId, @Param("user") Integer user);

    @Query(value = """
            SELECT DISTINCT driver_id FROM driver_vacation WHERE CAST(leave_from AS CHAR) LIKE %:yearMonth% and appoval_status = 1  
            AND DATEDIFF(leave_to, leave_from) > 5  AND status = 1
            """, nativeQuery = true)
    Integer[] getDataWithYearMonth(@Param("yearMonth") String yearMonth);

    @Query(value = """
        SELECT DISTINCT driver_id 
        FROM driver_vacation 
        WHERE CAST(leave_from AS CHAR) LIKE %:yearMonth%
        AND appoval_status = 1  
        AND DATEDIFF(leave_to, leave_from) > 5  
        AND status = 1
        AND driver_id IN (
            SELECT id 
            FROM driver_details 
            WHERE status = 1 
            AND branch = :branch
        )
        """, nativeQuery = true)
    Integer[] getDataWithYearMonthBranch(@Param("yearMonth") String yearMonth, @Param("branch") String branch);
}