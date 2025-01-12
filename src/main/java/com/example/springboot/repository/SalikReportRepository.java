package com.example.springboot.repository;

import com.example.springboot.entity.SalikReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SalikReportRepository extends JpaRepository<SalikReport, Long> {

    @Query(value = """
            SELECT vehicle_reg as vehicleReg, SUM(amount) as amount, COUNT(amount) as times 
            FROM salik_report WHERE month_year = :month_year GROUP BY vehicle_reg, id
            ORDER BY id DESC
            """, nativeQuery = true)
    List<Map<String, Object>> getDataWithMonthYear(@Param("month_year") String month_year);
}