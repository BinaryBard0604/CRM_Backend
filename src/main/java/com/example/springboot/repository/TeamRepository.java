package com.example.springboot.repository;

import com.example.springboot.entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = """
            SELECT * FROM team WHERE status = 1
            """, nativeQuery = true)
    List<Team> findAllWithStatus();

    @Query(value = """
            SELECT team.id, salesperson.name AS leader, team.name, team.email, team.target, team.status FROM team 
            JOIN salesperson ON team.leader_id = salesperson.id
            WHERE team.status = 1
            """, nativeQuery = true)
    List<Map<String, Object>> getAllData();

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Team o SET o.status = 0 WHERE o.id = :id
            """)
    void deleteByIdWithStatus(@Param("id") Long id);

    @Query(value = """
            SELECT * FROM team 
            JOIN opportunity ON team.id = opportunity.team_id
            WHERE team.id = :id AND opportunity.status = 1
            """, nativeQuery = true)
    List<Map<String, Object>> check(@Param("id") Long id);

    @Query(value = """
            SELECT name FROM team
            WHERE salespersons LIKE CONCAT('%', :salespersonId, '%')
            """, nativeQuery = true)
    List<Map<String, Object>> searchTeamWithSalesperson(@Param("salespersonId") String salespersonId);
}