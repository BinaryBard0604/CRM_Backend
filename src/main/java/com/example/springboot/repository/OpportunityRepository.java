package com.example.springboot.repository;

import com.example.springboot.entity.*;
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
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    @Query(value = """
            SELECT * FROM opportunity WHERE status = 1
            """, nativeQuery = true)
    List<Opportunity> findAllWithStatus();

    @Query(value = """
            SELECT salesperson_id, salesperson.name AS salesperson, expected_revenue AS revenue, created_date, probability 
            FROM opportunity 
            JOIN salesperson ON opportunity.salesperson_id = salesperson.id 
            JOIN stage ON opportunity.stage_id = stage.id
            WHERE opportunity.status = 1 AND created_date >= :startDate AND created_date <= :endDate AND stage.stage_role = "Done"
            """, nativeQuery = true)
    List<Map<String, Object>> getAnalysis(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = """
                SELECT opportunity.id, opportunity.name, opportunity.expected_revenue, opportunity.probability ,opportunity.contact, opportunity.email, opportunity.phone,
                c1.name AS salesperson, c1.email AS salesperson_email, opportunity.expected_closing, opportunity.created_date, stage.name AS stage, opportunity.tags, team.name AS team
                                                          FROM opportunity
                                                          JOIN salesperson c1 ON opportunity.salesperson_id = c1.id
                                                          JOIN stage ON opportunity.stage_id = stage.id
                                                          JOIN team ON opportunity.team_id = team.id
                                                          WHERE opportunity.status = 1 Order by opportunity.id
            """, nativeQuery = true)
    List<Map<String, Object>> findAllDataWithStatus();

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Opportunity o SET o.status = 0 WHERE o.id = :id
            """)
    void deleteByIdWithStatus(@Param("id") Long id);

    @Query(value = """
            SELECT * FROM opportunity
            JOIN activity ON opportunity.id = activity.opportunity_id
            WHERE opportunity.id = :id AND activity.activity_status = 1 AND activity.status = 1
            """, nativeQuery = true)
    List<Map<String, Object>> check(@Param("id") Long id);

    @Query(value = """
            SELECT * FROM opportunity WHERE status = 1 AND salesperson_id = :salespersonId
            """, nativeQuery = true)
    List<Map<String, Object>> getCheckSalesperson(@Param("salespersonId") Long salespersonId);
}