package com.example.springboot.repository;

import com.example.springboot.entity.StaffBranch;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface StaffBranchRepository extends JpaRepository<StaffBranch, Long> {

    @Query(value = """
            SELECT 
                CAST(id AS CHAR) AS id,
                CAST(branch AS CHAR) AS branch 
            FROM staff_branch  
            WHERE status = 1
            """, nativeQuery = true)
    List<Map<String, String>> getBranch();
}