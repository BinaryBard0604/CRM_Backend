package com.example.springboot.Repository;

import com.example.springboot.Entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    @Query(value = """
            SELECT * FROM activity WHERE status = 1
            """, nativeQuery = true)
    List<Activity> findAllWithStatus();

    @Query(value = """
            SELECT activity.id, activity.activity_status, activity.deadline, activity.notes, activity.summary, activity.status, activity.type,
            opportunity.name AS opportunity, customer.name AS assign
            FROM activity
            JOIN opportunity ON activity.opportunity_id = opportunity.id
            JOIN customer ON activity.assign_id = customer.id
            WHERE activity.status = 1 Order by activity.id
            """, nativeQuery = true)
    List<Map<String, Object>> findAllDataWithStatus();

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Activity o SET o.status = 0 WHERE o.id = :id
            """)
    void deleteByIdWithStatus(@Param("id") Long id);
}