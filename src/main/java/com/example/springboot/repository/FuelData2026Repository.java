package com.example.springboot.repository;

import com.example.springboot.entity.FuelData2026;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface FuelData2026Repository extends JpaRepository<FuelData2026, Long> {

    @Query(value = "SELECT t.id, t.vendor, t.VehiclePlateNumber, t.TransactionDate, t.UnitPrice, t.Volume, " +
            "t.ActualAmount, t.ProductName, t.VatRate, t.VatAmount, t.TotalAmount " +
            "FROM fuel_data_2026 t WHERE STR_TO_DATE(t.TransactionDate, '%c/%e/%Y %h:%i:%s %p') >= :startDate " +
            "AND STR_TO_DATE(t.TransactionDate, '%c/%e/%Y %h:%i:%s %p') <= :endDate",
            nativeQuery = true)
    List<Object[]> getDataByTable(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT round(SUM(t.TotalAmount), 2) as total_usage " +
            "FROM FuelData2026 t " +
            "WHERE t.VehiclePlateNumber = :vehiclePlateNumber AND t.month = :month_year",
            nativeQuery = true)
    Float getTotalAmount(@Param("vehiclePlateNumber") String vehiclePlateNumber,
                               @Param("month_year") String month_year);

    @Query(value = "SELECT round(SUM(t.TotalAmount), 2) as total_usage, t.VehiclePlateNumber as vehiclePlateNumber " +
            "FROM fuel_data_2026 t " +
            "WHERE t.VehiclePlateNumber IN :vehiclePlateNumbers AND STR_TO_DATE(t.TransactionDate, '%c/%e/%Y %h:%i:%s %p') >= :startDate AND STR_TO_DATE(t.TransactionDate, '%c/%e/%Y %h:%i:%s %p') <= :endDate " +
            "GROUP BY t.VehiclePlateNumber",
            nativeQuery = true)
    List<Map<String, Object>> getTotalAmounts(@Param("vehiclePlateNumbers") List<String> vehiclePlateNumbers,
                                              @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = """
            SELECT t.VehiclePlateNumber as vehicleReg, SUM(t.Volume) as volume, SUM(t.TotalAmount) as total 
            FROM fuel_data_2026 t 
            WHERE t.VehiclePlateNumber IN :vehicles AND STR_TO_DATE(t.TransactionDate, '%c/%e/%Y %h:%i:%s %p') >= :startDate AND STR_TO_DATE(t.TransactionDate, '%c/%e/%Y %h:%i:%s %p') <= :endDate
            GROUP BY t.VehiclePlateNumber
            """, nativeQuery = true)
    List<Map<String, Object>> getFuelDataForVehicles(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate,
                                                     @Param("vehicles") List<String> vehicles);

    @Query(value = """
            SELECT round(Sum(TotalAmount),2) as total_cost 
            FROM fuel_data_2026 f WHERE VehiclePlateNumber IN(SELECT v.reg_number FROM vehicle_assignment a 
            LEFT JOIN vehicle_details v on v.id=a.vehicle_id WHERE a.branch_id = :branch) AND f.month LIKE '%:monthYear%'
            """, nativeQuery = true)
    Map<String, Float> getTotalAmountWithBranch(@Param("monthYear") String monthYear, @Param("branch") String branch);

    @Query(value = """
            SELECT round(SUM(TotalAmount),2) as total_cost FROM fuel_data_2026 f WHERE f.month LIKE '%:monthYear%'
            """, nativeQuery = true)
    Map<String, Float> getTotalAmountWithBranch1(@Param("monthYear") String monthYear);
}