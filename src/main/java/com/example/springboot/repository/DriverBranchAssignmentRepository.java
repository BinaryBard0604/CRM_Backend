package com.example.springboot.repository;

import com.example.springboot.entity.DriverBranchAssignment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverBranchAssignmentRepository extends JpaRepository<DriverBranchAssignment, Long> {

    @Query("SELECT d FROM DriverBranchAssignment d WHERE d.driverId = :driverId AND d.branchId = :branch AND d.status = 1")
    List<DriverBranchAssignment> getDriverBranchAssignmentsByBranchId(@Param("driverId") Integer driverId, @Param("branch") Integer branch);

    @Modifying
    @Transactional
    @Query("UPDATE DriverBranchAssignment d SET d.status = 0")
    void updateStatusToInactive();
}