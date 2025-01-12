package com.example.springboot.repository;

import com.example.springboot.entity.VehicleAssignment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface VehicleAssignmentRepository extends JpaRepository<VehicleAssignment, Long> {

    @Query("SELECT COUNT(DISTINCT v.regNumber) FROM VehicleAssignment va " +
            "JOIN VehicleDetails v ON v.id = va.vehicleId " +
            "JOIN DriverDetails d ON d.id = va.driverId " +
            "JOIN CustomerBranch c ON CAST(d.branch AS integer) = c.id " +
            "JOIN VehicleBrand m ON m.id = CAST(v.make AS integer) " +
            "WHERE v.status = 1 AND va.status = 1")
    long countNumber();

    @Query("SELECT COUNT(DISTINCT v.regNumber) FROM VehicleAssignment va " +
            "JOIN VehicleDetails v ON v.id = va.vehicleId " +
            "JOIN DriverDetails d ON d.id = va.driverId " +
            "JOIN CustomerBranch c ON CAST(d.branch AS integer) = c.id " +
            "JOIN VehicleBrand m ON m.id = CAST(v.make AS integer) " +
            "WHERE v.status = 1 AND va.status = 1 AND d.branch = :userBranch")
    long countNumberByBranch(@Param("userBranch") String userBranch);

    @Query("SELECT v FROM VehicleAssignment v WHERE v.driverId IN :driverIds AND v.status = 1 ORDER BY v.id DESC")
    List<VehicleAssignment> findVehicleAssignmentsByDrivers(@Param("driverIds") List<Integer> driverIds);

    @Query("SELECT va.vehicleId AS vehicle_id, cb.branch AS branch, CONCAT(dd.firstName, ' ', dd.lastName) AS driver_name, " +
            "vd.plateNumber AS plate_number, vd.regNumber AS reg_number, CONCAT(va.fromDate, ' ', va.fromTime) AS from_date, " +
            "CONCAT(va.toDate, ' ', va.toTime) AS to_date, CASE WHEN va.status = 0 THEN 'Previous' ELSE 'Current' END AS status " +
            "FROM VehicleAssignment va " +
            "LEFT JOIN VehicleDetails vd ON vd.id = va.vehicleId " +
            "LEFT JOIN DriverDetails dd ON dd.id = va.driverId " +
            "LEFT JOIN CustomerBranch cb ON cb.id = va.branchId " +
            "WHERE va.driverId = :driverId ORDER BY va.id DESC")
    List<Map<String, ?>> findVehicleDetailsByDriverId(@Param("driverId") Long driverId);

    @Query("SELECT dv.leaveType AS leave_type, dv.leaveFrom AS leave_from, dv.leaveTo AS leave_to, " +
            "dv.notes AS notes, CONCAT(d.firstName, ' ', d.lastName) AS driver_name, dv.rejoinDate AS rejoin_date, " +
            "CASE WHEN dv.appovalStatus = 1 THEN 'Approved' " +
            "WHEN dv.appovalStatus = 2 THEN 'Rejected' " +
            "ELSE 'Pending' END AS appoval_status " +
            "FROM DriverVacation dv " +
            "LEFT JOIN DriverDetails d ON d.id = dv.driverId " +
            "WHERE dv.driverId = :driverId")
    List<Map<String, ?>> findDriverLeavesByDriverId(@Param("driverId") Long driverId);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN TRUE ELSE FALSE END " +
            "FROM VehicleAssignment v " +
            "WHERE v.vehicleId = :vehicleId AND v.status = :status")
    boolean existsByVehicleIdAndStatus(@Param("vehicleId") Integer vehicleId, @Param("status") Integer status);

    @Query("SELECT v FROM VehicleAssignment v WHERE v.vehicleId IN :vehicleIds AND v.status = :status")
    List<VehicleAssignment> findAllByVehicleIdsAndStatus(@Param("vehicleIds") List<Integer> vehicleIds, @Param("status") Integer status);

    @Query("SELECT a.vehicleId AS vehicleId, CONCAT(d.firstName, ' ', d.lastName) AS driver, " +
            "a.notes AS notes, c.branch AS branch, a.fromDate AS fromDate, " +
            "a.fromTime AS fromTime, a.toDate AS toDate, a.toTime AS toTime, a.status AS status " +
            "FROM VehicleAssignment a " +
            "LEFT JOIN DriverDetails d ON d.id = a.driverId " +
            "LEFT JOIN CustomerBranch c ON c.id = a.branchId " +
            "WHERE a.vehicleId IN :vehicleIds ORDER BY a.vehicleId ASC, a.id DESC")
    List<Map<String, Object>> findAssignmentsByVehicleIds(@Param("vehicleIds") List<Integer> vehicleIds);

    @Query(value = """
            SELECT 
                v.driver_id AS driverId, 
                v.vehicle_id AS vehicleId, 
                v.from_date AS fromDate, 
                v.to_date AS toDate, 
                CONCAT(d.first_name, ' ', d.last_name) AS driverName, 
                b.branch AS branch, 
                v.status AS status,
                v1.reg_number AS vehiclePlateNumber
            FROM 
                vehicle_assignment v
            LEFT JOIN 
                driver_details d ON d.id = v.driver_id
            LEFT JOIN 
                customer_branch b ON b.id = CAST(d.branch AS UNSIGNED)
            INNER JOIN 
                vehicle_details v1 ON v.vehicle_id = v1.id
            WHERE 
                v.from_date <= :endDate
                AND v1.reg_number IN :vehiclePlateNumbers
            ORDER BY 
                v.id DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findAllAssignments(
            @Param("endDate") LocalDate endDate,
            @Param("vehiclePlateNumbers") List<String> vehiclePlateNumbers
    );

    @Query(value = """
            SELECT 
                v.driver_id AS driverId, 
                v.vehicle_id AS vehicleId, 
                v.from_date AS fromDate, 
                v.to_date AS toDate, 
                CONCAT(d.first_name, ' ', d.last_name) AS driverName, 
                b.branch AS branch, 
                v.status AS status,
                v1.reg_number AS vehiclePlateNumber
            FROM 
                vehicle_assignment v
            LEFT JOIN 
                driver_details d ON d.id = v.driver_id
            LEFT JOIN 
                customer_branch b ON b.id = CAST(d.branch AS UNSIGNED)
            INNER JOIN 
                vehicle_details v1 ON v.vehicle_id = v1.id
            WHERE 
                v.from_date <= :endDate
                AND v1.reg_number = (SELECT vd.id FROM vehicle_details vd WHERE vd.reg_number = :vehiclePlateNumber ORDER BY vd.id ASC LIMIT 1)
            ORDER BY 
                v.id DESC LIMIT 2
            """, nativeQuery = true)
    List<Map<String, Object>> findAllAssignment(
            @Param("endDate") LocalDate endDate,
            @Param("vehiclePlateNumber") String vehiclePlateNumber
    );

    @Query(value = """
            SELECT 
                v.driver_id AS driverId, 
                v.vehicle_id AS vehicleId, 
                v.reg_number AS regNumber,
                v.from_date AS fromDate, 
                v.to_date AS toDate, 
                v.status AS status,
            FROM 
                vehicle_assignment v
            INNER JOIN 
                vehicle_details v1 ON v.vehicle_id = v1.id
            WHERE 
                v.from_date <= :endDate
                AND v.to_date <= :startDate
                AND v1.reg_number IN :vehiclePlateNumbers
            ORDER BY 
                v.id DESC
            """, nativeQuery = true)
    List<Map<String, Object>> findAllAssignments1(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("vehiclePlateNumbers") List<String> vehiclePlateNumbers
    );

    @Modifying
    @Transactional
    @Query("UPDATE VehicleAssignment v SET v.status = 0, v.closedBy = :user, v.closedDate = :formattedToday WHERE v.vehicleId = :record AND v.status = 1")
    Integer restoreVehicleAssignment(@Param("user") String user, @Param("record") Integer record, @Param("formattedToday") LocalDate formattedToday);

    @Query(value = """
            SELECT v.driver_id AS driverId, 
                   v.vehicle_id AS vehicleId, 
                   v.from_date AS fromDate,
                   v.to_date AS toDate, 
                   CONCAT(d.first_name, ' ', d.last_name) AS driverName, 
                   b.branch AS branch, 
                   v.status AS status, 
                   vd.reg_number AS regNumber 
            FROM vehicle_assignment v 
            LEFT JOIN driver_details d ON d.id = v.driver_id 
            LEFT JOIN customer_branch b ON b.id = CAST(d.branch AS UNSIGNED) 
            LEFT JOIN vehicle_details vd ON vd.id = v.vehicle_id
            WHERE v.from_date <= :endDate 
              AND v.to_date >= :startDate  
              AND vd.reg_number IN :regNumbers
            ORDER BY v.id DESC
            """, nativeQuery = true)
    List<Map<String, Object>> getDataWithDates(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("regNumbers") List<String> regNumbers);

    @Query(value = """
            SELECT v.driver_id AS driverId, 
                   v.vehicle_id AS vehicleId, 
                   v.from_date AS fromDate,
                   v.to_date AS toDate, 
                   CONCAT(d.first_name, ' ', d.last_name) AS driverName, 
                   b.branch AS branch, 
                   v.status AS status, 
                   vd.reg_number AS regNumber 
            FROM vehicle_assignment v 
            LEFT JOIN driver_details d ON d.id = v.driver_id 
            LEFT JOIN customer_branch b ON b.id = CAST(d.branch AS UNSIGNED) 
            LEFT JOIN vehicle_details vd ON vd.id = v.vehicle_id
            WHERE v.from_date <= :endDate 
              AND vd.reg_number IN :regNumbers
            ORDER BY v.id DESC
            """, nativeQuery = true)
    List<Map<String, Object>> getDataWithDates2(
            @Param("endDate") LocalDate endDate,
            @Param("regNumbers") List<String> regNumbers);

    @Query(value = """
        SELECT 
            a.driver_id AS driverId,
            b.branch AS branch,
            a.notes AS notes,
            CONCAT(d.first_name, ' ', d.last_name) AS driverName,
            a.vehicle_id AS vehicleId,
            a.from_date AS fromDate,
            a.to_date AS toDate,
            a.from_time AS fromTime,
            a.to_time AS toTime,
            v.plate_number AS plateNumber,
            a.status AS status,
            a.created_at AS createdAt,
            v.notes AS vehicleNotes 
        FROM vehicle_assignment a 
        LEFT JOIN vehicle_details v ON v.id = a.vehicle_id 
        LEFT JOIN driver_details d ON d.id = a.driver_id 
        LEFT JOIN customer_branch b ON b.id = a.branch_id 
        WHERE (v.reg_number LIKE CONCAT('%', :searchData, '%') 
        OR v.plate_number LIKE CONCAT('%', :searchData, '%')) 
        AND a.driver_id != 0 
        ORDER BY a.id DESC
        """, nativeQuery = true)
    List<Map<String, Object>> searchVehicle(@Param("searchData") String searchData);

    @Query(value = """
            SELECT driver_id FROM vehicle_assignment WHERE driver_id = :driverId AND vehicle_id = :vehicleId AND status = 1
            """, nativeQuery = true)
    List<Map<String, ?>> getDriverId(@Param("driverId") Integer driverId, @Param("vehicleId") Integer vehicleId);

    @Query(value = """
            SELECT a.driver_id, CONCAT(first_name,' ',last_name) as drivername 
            FROM vehicle_assignment a LEFT JOIN driver_details d ON d.id = a.driver_id 
            WHERE a.driver_id = :driverId AND a.vehicle_id != :vehicleId AND a.status = 1
            """, nativeQuery = true)
    List<Map<String, ?>> getData(@Param("driverId") Integer driverId, @Param("vehicleId") Integer vehicleId);
}