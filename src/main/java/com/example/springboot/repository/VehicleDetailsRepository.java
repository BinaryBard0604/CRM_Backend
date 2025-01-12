package com.example.springboot.repository;

import com.example.springboot.entity.VehicleDetails;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, Long> {

    @Query("SELECT COUNT(DISTINCT (regNumber)) FROM VehicleDetails WHERE status = 1")
    long countDistinctByStatus();

    @Query("SELECT COUNT(v.id) FROM VehicleDetails v WHERE id NOT IN (SELECT a.vehicleId FROM VehicleAssignment a WHERE a.status=1) AND v.status=1")
    long countFreeVehicle();

    @Query("SELECT v.vehicleName, v.regNumber, v.purchaseTo FROM VehicleDetails v WHERE v.status = 1 AND v.purchaseTo BETWEEN :startDate AND :endDate")
    List<Object[]> findLeaseExpiringSoon(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT v FROM VehicleDetails v WHERE v.regNumber = :reg_number AND v.status = 1")
    List<VehicleDetails> getVehicleDetailsByReg1(@Param("reg_number") String reg_number);

    @Query("SELECT v FROM VehicleDetails v WHERE v.regNumber = :reg_number AND v.status = 0")
    List<VehicleDetails> getVehicleDetailsByReg0(@Param("reg_number") String reg_number);

    @Query("SELECT v.id AS id, v.vehicleType AS veh_type, v.vehicleGear AS vehicle_gear, v.vehicleName AS vehicle_name, v.plateNumber AS plate_number, " +
            "v.regNumber AS reg_number, v.purchaseCompany AS purchase_company, v.regEmirate AS reg_emirate, v.make AS make, v.model AS model, v.initialMileage AS initial_mileage, v.mileage AS mileage, v.purchaseType AS purchase_type, " +
            "v.purchaseFrom AS purchase_from, v.purchaseTo AS purchase_to, v.notes AS notes, v.status AS status, b.brand AS brand, v.plan AS plan " +
            "FROM VehicleDetails v LEFT JOIN VehicleBrand b ON b.id = CAST(v.make AS integer) WHERE v.id = :vehicle_id")
    List<Map<String, String>> getVehicleDetailsByVehicleId(@Param("vehicle_id") Integer vehicle_id);

    @Query(value = """
            Select v.id, CONCAT(m.brand, ' ', v.vehicle_name) as vehicle_name, v.reg_number, v.reg_emirate, v.make,
            v.plate_number, v.mileage, CONCAT(first_name, ' ', d.last_name) as drivername, d.branch as branch_id,
            b.branch, DATE_FORMAT(v.purchase_to, '%d-%m-%Y') AS purchase_to, v.status, a.driver_id, a.status, 
            a.branch_id as branchid, DATE_FORMAT(a.from_date,'%d-%m-%Y') AS from_date, a.from_time, a.to_date, a.to_time, m.brand 
            FROM vehicle_details v LEFT JOIN vehicle_assignment a ON v.id = a.vehicle_id AND a.status != 0 
            LEFT JOIN driver_details d ON d.id = a.driver_id LEFT JOIN customer_branch b ON d.branch = b.id  
            LEFT JOIN vehicle_brand m ON m.id = v.make WHERE v.status = 1
            """, nativeQuery = true)
    List<Map<String, ?>> findVehicleDetails();

    @Query(value = """
            Select v.id, CONCAT(m.brand, ' ', v.vehicle_name) as vehicle_name, v.reg_number, v.reg_emirate, v.make,
            v.plate_number, v.mileage, CONCAT(first_name, ' ', d.last_name) as drivername, d.branch as branch_id,
            b.branch, DATE_FORMAT(v.purchase_to, '%d-%m-%Y') AS purchase_to, v.status, a.driver_id, a.status, 
            a.branch_id as branchid, DATE_FORMAT(a.from_date,'%d-%m-%Y') AS from_date, a.from_time, a.to_date, a.to_time, m.brand 
            FROM vehicle_details v LEFT JOIN vehicle_assignment a ON v.id = a.vehicle_id AND a.status != 0 
            LEFT JOIN driver_details d ON d.id = a.driver_id LEFT JOIN customer_branch b ON d.branch = b.id  
            LEFT JOIN vehicle_brand m ON m.id = v.make WHERE v.status = 1 AND d.branch IN :branch
            """, nativeQuery = true)
    List<Map<String, ?>> findVehicleDetailsWithBranch(@Param("branch") String branch);

    @Query(value = """
            WITH MaxIds AS (
                SELECT 
                    MAX(d.id) AS max_id
                FROM 
                    VehicleDetails d
                WHERE 
                    d.status = 0
                    AND d.regNumber NOT IN (
                        SELECT d1.regNumber 
                        FROM VehicleDetails d1
                        WHERE d1.status = 1
                    )
                GROUP BY 
                    d.regNumber
            )
            SELECT vd
            FROM VehicleDetails vd
            JOIN MaxIds mi ON vd.id = mi.max_id
            ORDER BY vd.id DESC
            """)
    List<VehicleDetails> findOffVehicles();

    @Query(value = """
            Select v.id, v.vehicle_name, v.reg_number, v.reg_emirate, v.make, v.plate_number, v.initial_mileage, v.mileage,
            CONCAT(d.first_name,' ',d.last_name), d.branch, b.branch, v.purchase_to, a.driver_id, a.status , a.branch_id,
            a.from_date, a.from_time, a.to_date, a.to_time, m.brand, v.plan 
            FROM vehicle_details v LEFT JOIN vehicle_assignment a ON v.id=a.vehicle_id AND a.status != 0 
            LEFT JOIN driver_details d ON d.id = a.driver_id LEFT JOIN customer_branch b ON d.branch = b.id 
            LEFT JOIN vehicle_brand m ON m.id= v.make WHERE v.status = 1 and v.mileage > 185000 
            AND v.reg_number NOT IN(20467,21429)
            """, nativeQuery = true)
    List<Object[]> findFilteredVehicles();

    @Modifying
    @Transactional
    @Query("UPDATE VehicleDetails v SET v.status = 0, v.updatedBy = :user, v.updatedDate = :formattedToday WHERE v.id = :vehicleId")
    Integer updateVehicleStatus(@Param("vehicleId") Integer vehicleId, @Param("user") Integer user, @Param("formattedToday") String formattedToday);

    @Modifying
    @Transactional
    @Query("UPDATE VehicleDetails v SET v.status = 1, v.updatedBy = :user, v.updatedDate = :formattedToday WHERE v.id = :record")
    Integer restore(@Param("user") String user, @Param("record") Integer record, @Param("formattedToday") String formattedToday);

    @Query(value = """
            SELECT 
                d.reg_number AS reg_number, d.vehicle_type AS vehicleType, v.vehicle_type AS vehtype
            FROM 
                vehicle_details d
            LEFT JOIN 
                vehicle_type v ON CAST(d.vehicle_type AS UNSIGNED) = v.id
            WHERE 
                d.reg_number IN :vehiclePlateNumbers
            """, nativeQuery = true)
    List<Map<String, Object>> findAllVehiclesWithPlateNumbers(@Param("vehiclePlateNumbers") List<String> vehiclePlateNumbers);

    @Query(value = """
            SELECT a.driver_id as driverId, a.vehicle_id as vehicleId, a.status as status,
            v.reg_number as regNumber, a.from_date as fromDate, a.to_date as toDate
            FROM vehicle_assignment a LEFT JOIN vehicle_details v ON v.id = a.vehicle_id 
            WHERE a.from_date <= :endDate AND a.to_date >= :startDate AND v.status = 1 AND a.driver_id IN :driverIds
            """, nativeQuery = true)
    List<Map<String, Object>> getVehiclesLimit4ForAllDrivers(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("driverIds") List<Integer> driverIds);

    @Query(value = """
            Select a.driver_id as driverId, a.vehicle_id as vehicleId, a.status as status,
            v.reg_number as regNumber, a.from_date as fromDate, a.to_date as toDate
            FROM vehicle_assignment a LEFT JOIN vehicle_details v ON v.id = a.vehicle_id 
            WHERE a.from_date <= :endDate AND a.to_date >= :startDate AND a.driver_id = CAST(:driverId AS SIGNED) 
            AND v.status = 1 ORDER BY a.from_date DESC LIMIT 4
            """, nativeQuery = true)
    List<Map<String, Object>> getVehiclesLimit4(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("driverId") String driverId);

    @Query(value = """
            SELECT v.reg_number as regNumber, v.vehicle_type as vehicleType, d.month_allowance as monthAllowance  
            FROM vehicle_details v LEFT JOIN vehicle_type d ON v.vehicle_type = d.id 
            WHERE v.reg_number IN :vehicles
            """, nativeQuery = true)
    List<Map<String, Object>> getVehicleTypes(@Param("vehicles") List<String> vehicles);

    @Query(value = """
            SELECT v.vehicle_type as vehicleType, d.vehicle_type as vehicleType1, d.month_allowance as monthAllowance  
            FROM vehicle_details v LEFT JOIN vehicle_type d ON v.vehicle_type = d.id 
            WHERE v.reg_number = :vehicle
            """, nativeQuery = true)
    List<Map<String, Object>> getDataWithRegNumber(@Param("vehicle") String vehicle);

    @Query(value = """
            Select v.id as id, v.reg_number regNumber FROM vehicle_details v WHERE v.status = 1
            """, nativeQuery = true)
    List<Map<String, Object>> getVehicleDetailsByStatus();

    @Query(value = """
            SELECT 
                CAST(v.reg_number AS CHAR) AS reg_number, 
                CAST(v.plate_number AS CHAR) AS plate_number, 
                CAST(v.vehicle_name AS CHAR) AS vehicle_name 
            FROM vehicle_details v 
            WHERE v.status = 1 AND v.id = :vehicleId
            """, nativeQuery = true)
    List<Map<String, String>> getVehicleDetailsData(@Param("vehicleId") Integer vehicleId);

    @Query(value = """
            SELECT 
                CAST(a.driver_id AS CHAR) AS driver_id, 
                CAST(a.branch_id AS CHAR) AS branch_id, 
                CAST(CONCAT(a.from_date, 'T', a.from_time) AS CHAR) AS from_date, 
                CAST(CONCAT(a.to_date, 'T', a.to_time) AS CHAR) AS to_date,
                CAST(a.notes AS CHAR) AS notes, 
                CAST(CONCAT(d.first_name, ' ', d.last_name) AS CHAR) AS driver_name 
            FROM vehicle_details v 
            LEFT JOIN vehicle_assignment a ON a.vehicle_id = v.id 
            LEFT JOIN driver_details d ON d.id = a.driver_id  
            WHERE v.id = :vehicleId AND a.status = 1 
            ORDER BY a.id DESC 
            LIMIT 1
            """, nativeQuery = true)
    List<Map<String, String>> getAssignDetails(@Param("vehicleId") Integer vehicleId);
}