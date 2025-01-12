package com.example.springboot.repository;

import com.example.springboot.entity.WpCountry;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface WpCountryRepository extends JpaRepository<WpCountry, Long> {

    @Query(value = """
            SELECT country_name FROM wp_country
            """, nativeQuery = true)
    List<Map<String, String>> getAllData();
}