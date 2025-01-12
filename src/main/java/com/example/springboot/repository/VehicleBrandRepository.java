package com.example.springboot.repository;

import com.example.springboot.entity.VehicleBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface VehicleBrandRepository extends JpaRepository<VehicleBrand, Long> {

    @Query("SELECT v.id AS id, v.brand AS brand FROM VehicleBrand v WHERE v.vehType = :brand_id")
    List<Map<String, String>> getVehicleBrandByType(@Param("brand_id") String brand_id);

    @Query("SELECT v.id AS id, v.brand AS brand " +
            "FROM VehicleBrand v WHERE v.status = 1")
    List<Map<String, String>> getBrandList();
}