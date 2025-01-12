package com.example.springboot.repository;

import com.example.springboot.entity.StaffVacation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface StaffVacationRepository extends JpaRepository<StaffVacation, Long> {

    @Query(value = """
            Select v.staff_id as staff_id, v.leave_from as leave_from, v.leave_to as leave_to 
            FROM staff_vacation v WHERE v.status=1 AND v.appoval_status=1 AND v.leave_from <= :today
            AND v.rejoin_date ='0' AND v.staff_id in( SELECT id FROM staff_details WHERE status=1)
            """, nativeQuery = true)
    List<Map<String, Object>> getStaffLeaveCount(@Param("today") LocalDate today);

    @Query(value = """
            Select v.staff_id,v.leave_from,v.leave_to,s.first_name,s.last_name 
            FROM staff_vacation v LEFT JOIN staff_details s ON s.id=v.staff_id 
            WHERE v.status=1 AND v.appoval_status=1 AND v.leave_from <= :today AND v.rejoin_date ='0' 
            AND v.staff_id in( SELECT id FROM staff_details WHERE status=1)
            """, nativeQuery = true)
    List<Map<String, Object>> getLeaveOverdue(@Param("today") LocalDate today);

    @Query(value = """
            Select staff_vacation.id,staff_id,leave_from,leave_to,leave_type,leave_form,appoval_status,d.change_date,
            d.date_employed,rejoin_date,staff_vacation.notes,settlement_form,staff_vacation.status 
            FROM staff_vacation LEFT JOIN staff_details d ON d.id=staff_vacation.staff_id WHERE staff_id = :staffId  
            AND rejoin_date ='0' ORDER BY staff_vacation.id DESC LIMIT 1
            """, nativeQuery = true)
    Map<String, Object> getCheckLeave(@Param("staffId") Integer staffId);

    @Query(value = """
            SELECT 
                CAST(staff_vacation.id AS CHAR) AS id,
                CAST(staff_id AS CHAR) AS staff_id,
                CAST(leave_from AS CHAR) AS leave_from,
                CAST(leave_to AS CHAR) AS leave_to,
                CAST(leave_type AS CHAR) AS leave_type,
                CAST(leave_form AS CHAR) AS leave_form,
                CAST(appoval_status AS CHAR) AS appoval_status,
                CAST(rejoin_date AS CHAR) AS rejoin_date,
                CAST(settlement_form AS CHAR) AS settlement_form,
                CAST(staff_vacation.notes AS CHAR) AS notes,
                CAST(staff_vacation.status AS CHAR) AS status 
            FROM staff_vacation  
            WHERE staff_id = :staffId  
            ORDER BY staff_vacation.id DESC LIMIT 1
            """, nativeQuery = true)
    List<Map<String, String>> getVacationDetails(@Param("staffId") Integer staffId);

    @Query(value = """
            SELECT staff_id FROM staff_vacation WHERE staff_id = :id AND status=1
            """, nativeQuery = true)
    List<Map<String, Object>> getDataWithStatus(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE staff_vacation SET status = 0 WHERE staff_id = :id", nativeQuery = true)
    void updateDataWithStaffId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE StaffVacation sv SET sv.appoval_status = :approvalStatus, sv.notes = :notes WHERE sv.id = :vacationId")
    Integer updateApprovalStatus(@Param("approvalStatus") Integer approvalStatus, @Param("vacationId") Integer vacationId, @Param("notes") String notes);

    @Query(value = """
            Select staff_vacation.id, staff_id, leave_from, leave_to, leave_type, leave_form, appoval_status, d.change_date,
            d.date_employed, rejoin_date, settlement_form, staff_vacation.notes, staff_vacation.status 
            FROM staff_vacation LEFT JOIN staff_details d ON d.id = staff_vacation.staff_id WHERE staff_id = :staffId  
            AND rejoin_date = '0' ORDER BY staff_vacation.id DESC
            """, nativeQuery = true)
    List<Map<String, ?>> getStaffVacationWithStaffId(@Param("staffId") Integer staffId);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE staff_vacation SET rejoin_date = :rejoinDate, updated_by = :user 
            WHERE staff_id = :staffId AND id = :vacationId
            """, nativeQuery = true)
    void closeStaffLeave(@Param("staffId") Integer staffId, @Param("rejoinDate") String rejoinDate,
                                 @Param("vacationId") Integer vacationId, @Param("user") Integer user);

    @Query(value = """
        SELECT DISTINCT staff_id FROM staff_vacation WHERE leave_from LIKE %:yearMonth% and appoval_status=1
        """, nativeQuery = true)
    Integer[] getApprovedStaffLeaves(@Param("yearMonth") String yearMonth);
}