package com.example.springboot.repository;

import com.example.springboot.entity.SimAssignment;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimAssignmentRepository extends JpaRepository<SimAssignment, Long> {

    @Query("SELECT s FROM SimAssignment s WHERE s.driverId = :driverId AND s.mobile = :phone AND s.status = 1")
    List<SimAssignment> getSimAssingmentByBranchId(@Param("driverId") Integer driverId, @Param("phone") String phone);

    @Modifying
    @Transactional
    @Query("UPDATE SimAssignment s SET s.status = 0")
    void updateStatusToInactive();
}