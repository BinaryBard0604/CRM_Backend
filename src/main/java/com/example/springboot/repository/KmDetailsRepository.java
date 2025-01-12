package com.example.springboot.repository;

import com.example.springboot.entity.KmDetails;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface KmDetailsRepository extends JpaRepository<KmDetails, Long> {

    @Query("SELECT k FROM KmDetails k WHERE k.vehicleReg = :reg_number AND km = :mileage")
    List<KmDetails> getKmDetailsByKm(@Param("mileage") String mileage, @Param("reg_number") String reg_number);

    @Query(value = "SELECT k FROM KmDetails k WHERE k.vehicleReg = :vehicleReg ORDER BY k.id DESC LIMIT 1")
    Optional<KmDetails> findLatestByVehicleReg(@Param("vehicleReg") String vehicleReg);

    @Query(value = """
        SELECT * FROM km_details k 
        WHERE k.id IN (
            SELECT MAX(k2.id) 
            FROM km_details k2 
            WHERE k2.vehicle_reg IN :vehicleRegs 
            GROUP BY k2.vehicle_reg
        )
        """, nativeQuery = true)
    List<KmDetails> findLatestByVehicleRegs(@Param("vehicleRegs") List<String> vehicleRegs);

    @Query(value = """
            SELECT k.km as km, k.updated_date as updatedDate , k.vehicle_reg as vehicle_reg
            FROM km_details k 
            WHERE k.updated_date <= :endDate AND k.updated_date >= :startDate AND k.vehicle_reg IN :vehicles
            """, nativeQuery = true)
    List<Map<String, Object>> getDataWithVehicles(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("vehicles") List<String> vehicles);

    @Query(value = """
            SELECT k.vehicle_reg as vehicleReg, SUM(k.km) as km
            FROM km_details k 
            WHERE k.updated_date >= :startDate
              AND k.updated_date <= :endDate
              AND k.vehicle_reg IN :regNumber
            GROUP BY k.vehicle_reg
            """, nativeQuery = true)
    List<Map<String, Object>>   getKmsForVehicles(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("regNumber") List<String> regNumber);

    @Query(value = """
            SELECT SUM(CAST(km AS UNSIGNED)) as total_km 
            FROM km_details WHERE vehicle_reg IN (SELECT v.reg_number FROM vehicle_assignment a 
            LEFT JOIN vehicle_details v on v.id = a.vehicle_id WHERE a.branch_id = :branch) AND CAST(updated_date AS CHAR) LIKE CONCAT('%', :yearMonth, '%')
            """, nativeQuery = true)
    Map<String, Integer> getTotalKmWithBranch(@Param("yearMonth") String yearMonth, @Param("branch") Integer branch);

    @Query(value = """
            SELECT SUM(CAST(km AS UNSIGNED)) as total_km FROM km_details WHERE CAST(updated_date AS CHAR) LIKE CONCAT('%', :yearMonth, '%')
            """, nativeQuery = true)
    Map<String, Integer> getTotalKm(@Param("yearMonth") String yearMonth);
}