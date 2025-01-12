package com.example.springboot.repository;

import com.example.springboot.entity.TelephoneData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TelephoneDataRepository extends JpaRepository<TelephoneData, Long> {

    @Query(value = """
            SELECT id as id, phone as phone, amount as amount, month as month 
            FROM telephone_data WHERE month = :month_year ORDER BY amount DESC
            """, nativeQuery = true)
    List<Map<String, Object>> getTelephoneDataWithMonthYear(@Param("month_year") String month_year);
}