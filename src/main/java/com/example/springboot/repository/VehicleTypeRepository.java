package com.example.springboot.repository;

import com.example.springboot.entity.VehicleType;
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
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long> {

    @Query("SELECT v.id AS id, v.vehicleType AS vehicle_type, v.kmAllowance AS km_allowance, v.monthAllowance AS month_allowance " +
            "FROM VehicleType v WHERE v.status = 1")
    List<Map<String, String>> getVehicleTypeByStatus();

    @Modifying
    @Transactional
    @Query("UPDATE VehicleType v SET v.status = 0 WHERE v.id = :record")
    Integer updateVehicleType(@Param("record") Integer record);

    @Query("SELECT v.id AS id, v.vehicleType AS veh_type, v.kmAllowance AS km_allowance, v.monthAllowance AS fuel_allowance FROM VehicleType v WHERE v.id = :vehicletypeId")
    List<Map<String, String>> getVehicleTypeDetail(@Param("vehicletypeId") Integer vehicletypeId);

    @Query(value = """
            SELECT v.month_allowance FROM vehicle_type v WHERE id = :vehicleType
            """, nativeQuery = true)
    String getMonthAllowance(@Param("vehicleType") Integer vehicleType);

    @Modifying
    @Transactional
    @Query(value = """
        UPDATE vehicle_type SET vehicle_type = :veh_type, km_allowance = :km_allowance, month_allowance = :fuel_allowance WHERE id = :id
        """, nativeQuery = true)
    Integer updateVehicleTypeWithData(@Param("id") Integer id, @Param("veh_type") String veh_type, @Param("km_allowance") String km_allowance,
                                      @Param("fuel_allowance") String fuel_allowance);

    @Query(value = """
            SELECT id,vehicle_type,km_allowance,month_allowance FROM vehicle_type WHERE status=1
            """, nativeQuery = true)
    List<Map<String, String>> getList();
}