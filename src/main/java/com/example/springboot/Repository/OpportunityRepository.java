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
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    @Query(value = """
            SELECT * FROM opportunity WHERE status = 1
            """, nativeQuery = true)
    List<Opportunity> findAllWithStatus();

    @Query(value = """
            SELECT opportunity.id, opportunity.name, opportunity.expected_revenue, opportunity.probability ,customer.name AS contact, c1.name AS salesperson, opportunity.expected_closing, stage.name AS stage, opportunity.tags
                                                      FROM opportunity\s
                                                      JOIN customer ON opportunity.contact_id = customer.id
                                                      JOIN customer c1 ON opportunity.salesperson_id = customer.id
                                                      JOIN stage ON opportunity.stage_id = stage.id\s
                                                      WHERE opportunity.status = 1 Order by opportunity.id
            """, nativeQuery = true)
    List<Map<String, Object>> findAllDataWithStatus();

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Opportunity o SET o.status = 0 WHERE o.id = :id
            """)
    void deleteByIdWithStatus(@Param("id") Long id);
}