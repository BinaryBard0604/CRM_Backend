package com.example.springboot.repository;

import com.example.springboot.entity.VehicleName;
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
public interface VehicleNameRepository extends JpaRepository<VehicleName, Long> {

    @Query("SELECT v.id AS id, v.vehicleName AS vehicle_name FROM VehicleName v WHERE v.brandId = :make_id")
    List<Map<String, String>> getMakeNameByBrand(@Param("make_id") String make_id);

    @Modifying
    @Transactional
    @Query("UPDATE VehicleName v SET v.status = 0 WHERE v.id = :record")
    Integer updateVehicleName(@Param("record") Integer record);

    @Query("SELECT v.id AS id, v.vehicleName AS vehicle_name, b.brand AS brand " +
            "FROM VehicleName v LEFT JOIN VehicleBrand b ON v.brandId = b.id WHERE v.status = 1")
    List<Map<String, String>> getVehiceNameByStatus();

    @Query("SELECT CONCAT(v.id, '') AS id, v.vehicleName AS veh_name, CONCAT(v.brandId, '') AS brandId FROM VehicleName v WHERE v.id = :vehicleNameId")
    List<Map<String, String>> getVehicleNameDetail(@Param("vehicleNameId") Integer vehicleNameId);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE vehicle_name SET vehicle_name = :veh_name, brand_id = :brandId WHERE id = :id
        """, nativeQuery = true)
    Integer updateVehicleNameWithData(@Param("id") Integer id, @Param("veh_name") String veh_name, @Param("brandId") Integer brandId);
}