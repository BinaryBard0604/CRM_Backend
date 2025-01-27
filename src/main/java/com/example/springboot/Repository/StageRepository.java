package com.example.springboot.Repository;

import com.example.springboot.Dto.StageAllResponse;
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
public interface StageRepository extends JpaRepository<Stage, Long> {

    @Query(value = """
            SELECT * FROM stage WHERE status = 1
            """, nativeQuery = true)
    List<Stage> findAllWithStatus();

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Stage s SET s.status = 0 WHERE s.id = :id
            """)
    void deleteByIdWithStatus(@Param("id") Long id);

    @Query(value = """
    SELECT
        stage.id AS id,
        stage.name AS name,
        stage.status AS status,
        CASE
            WHEN COUNT(opportunity.id) > 0 THEN
                JSON_ARRAYAGG(
                    JSON_OBJECT(
                        'id', opportunity.id,
                        'name', opportunity.name,
                        'expected_revenue', opportunity.expected_revenue,
                        'probability', opportunity.probability,
                        'contact_id', opportunity.contact_id,
                        'salesperson_id', opportunity.salesperson_id,
                        'expected_closing', opportunity.expected_closing,
                        'tags', opportunity.tags,
                        'stage_id', opportunity.stage_id,
                        'rating', opportunity.rating,
                        'status', opportunity.status,
                        'activities', IFNULL((
                            SELECT
                                JSON_ARRAYAGG(
                                    JSON_OBJECT(
                                        'id', activity.id,
                                        'opportunity_id', activity.opportunity_id,
                                        'type', activity.type,
                                        'deadline', activity.deadline,
                                        'summary', activity.summary,
                                        'assign_id', activity.assign_id,
                                        'notes', activity.notes,
                                        'activity_status', activity.activity_status,
                                        'status', activity.status
                                    )
                                )
                            FROM
                                activity
                            WHERE
                                activity.opportunity_id = opportunity.id
                            AND 
                                activity.status = 1
                        ), JSON_ARRAY())  -- Return empty array if NULL
                    )
                )
            ELSE
                JSON_ARRAY()
        END AS opportunities
    FROM
        stage
    LEFT JOIN
        opportunity ON stage.id = opportunity.stage_id AND opportunity.status = 1
    WHERE
        stage.status = 1
    GROUP BY
        stage.id, stage.name, stage.status;
    """, nativeQuery = true)
    List<Map<String, Object>> getAllStageResponse();

}