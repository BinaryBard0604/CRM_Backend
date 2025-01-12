package com.example.springboot.repository;

import com.example.springboot.entity.Analytics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AnalyticsRepository extends JpaRepository<Analytics, Long> {

    @Query(value = """
        SELECT total_driver, total_vehicle 
        FROM analytics 
        WHERE month_year LIKE %:yearMonth% 
        AND branch = :branch 
        ORDER BY id DESC 
        LIMIT 1
        """, nativeQuery = true)
    Map<String, ?> getDataWithYearMonth(@Param("yearMonth") String yearMonth, @Param("branch") String branch);

}