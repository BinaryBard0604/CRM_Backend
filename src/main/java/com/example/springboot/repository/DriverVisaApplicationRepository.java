package com.example.springboot.repository;

import com.example.springboot.entity.DriverVacation;
import com.example.springboot.entity.DriverVisaApplication;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverVisaApplicationRepository extends JpaRepository<DriverVisaApplication, Long> {

    @Query("SELECT v FROM DriverVisaApplication v WHERE v.driverId IN :driverIds ORDER BY v.id DESC")
    List<DriverVisaApplication> findVisaApprovalStatusesByDrivers(@Param("driverIds") List<Integer> driverIds);

    @Query("SELECT d FROM DriverVisaApplication d WHERE d.driverId = :driver AND d.status = 1")
    List<DriverVisaApplication> findDriverVisaApplicationIdWithStatus(@Param("driver") Integer driver);

    @Modifying
    @Transactional
    @Query("UPDATE DriverVisaApplication d SET d.status = 0 WHERE d.driverId = :driver")
    void setDriverVisaApplicationWithDriverId(@Param("driver") Integer driver);
}