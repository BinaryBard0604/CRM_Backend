package com.example.springboot.repository;

import com.example.springboot.entity.DriverFinalSettlement;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface DriverFinalSettlementRepository extends JpaRepository<DriverFinalSettlement, Long> {

    @Query("SELECT d " +
            "FROM DriverFinalSettlement d WHERE d.driverId = :driverId")
    Optional<DriverFinalSettlement> findByDriverId(@Param("driverId") Long driverId);

    @Query("SELECT d " +
            "FROM DriverFinalSettlement d WHERE d.driverId = :driverId AND d.status = 1")
    List<DriverFinalSettlement> getDriverFinalSettlementsWithDriverId(@Param("driverId") Integer driverId);

    @Modifying
    @Transactional
    @Query("UPDATE DriverFinalSettlement d SET d.status = 0 WHERE d.driverId = :driverId")
    int updateDriverFinalSettlement(@Param("driverId") Integer driverId);

    @Query(value = """
            SELECT id FROM driver_final_settlement WHERE CAST(resign_date AS CHAR) LIKE CONCAT('%', :year, '%')
            """, nativeQuery = true)
    Integer[] getCouryierStatResigned(@Param("year") String year);
}