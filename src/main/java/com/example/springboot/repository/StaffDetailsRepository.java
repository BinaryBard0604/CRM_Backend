package com.example.springboot.repository;

import com.example.springboot.entity.StaffDetails;
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
public interface StaffDetailsRepository extends JpaRepository<StaffDetails, Long> {

    @Query(value = """
            SELECT staff_details.id as id FROM staff_details WHERE status=1
            """, nativeQuery = true)
    List<Map<String, Object>> getStaffCount();

    @Query(value = """
            SELECT first_name,last_name,pdrp_expiry_date 
            FROM staff_details WHERE status=1 AND pdrp_expiry_date 
            between DATE_FORMAT(NOW() ,'%Y-%m-01') AND LAST_DAY(DATE_ADD(NOW(), INTERVAL 15 DAY))
            """, nativeQuery = true)
    List<Map<String, Object>> getPassportExpiry();

    @Query(value = """
            SELECT first_name,last_name,work_permit_expiry 
            FROM staff_details WHERE status=1 AND work_permit_expiry
            between DATE_FORMAT(NOW() ,'%Y-%m-01') AND LAST_DAY(DATE_ADD(NOW(), INTERVAL 15 DAY))  AND work_permit_expiry > curdate()
            """, nativeQuery = true)
    List<Map<String, Object>> getVisaExpiry();

    @Query(value = """
            SELECT first_name,last_name,work_permit_expiry 
            FROM staff_details WHERE status=1 AND work_permit_expiry 
            between DATE_FORMAT(NOW() ,'%Y-%m-01') AND LAST_DAY(DATE_ADD(NOW(), INTERVAL 15 DAY)) AND work_permit_expiry < curdate()
            """, nativeQuery = true)
    List<Map<String, Object>> getVisaExpired();

    @Query(value = """
            SELECT first_name,last_name,citizenship,date_employed 
            FROM staff_details WHERE status=1 AND date_employed between :monthStart AND curdate()
            """, nativeQuery = true)
    List<Map<String, Object>> getNewJoiness(@Param("monthStart") LocalDate monthStart);

    @Query(value = """
            SELECT staff_details.id,CONCAT(first_name,' ', last_name) as full_name,common_name,license_type,phone,email,
            employee_number,work_permit_expiry,date_employed,pdrp_expiry_date,passport_number,license_expiry_date,c.branch,staff_details.status 
            FROM staff_details LEFT JOIN staff_branch c ON staff_details.branch=c.id 
            WHERE staff_details.status=1 AND staff_details.id IN(SELECT staff_id FROM staff_vacation WHERE appoval_status=0 AND status=1)
            """, nativeQuery = true)
    List<Map<String, Object>> getStaff();

    @Query(value = """
            SELECT 
                CAST(id AS CHAR) AS id,
                CAST(CONCAT(first_name, ' ', last_name) AS CHAR) AS staff_name,
                CAST(passport_number AS CHAR) AS passport_number,
                CAST(change_date AS CHAR) AS change_date,
                CAST(date_employed AS CHAR) AS date_employed 
            FROM staff_details  
            WHERE id = :staffId
            """, nativeQuery = true)
    List<Map<String, String>> getJoinDetails(@Param("staffId") Integer staffId);

    @Query(value = """
            SELECT staff_details.id,CONCAT(first_name,' ', last_name) as full_name,common_name,license_type,phone,email,
            employee_number,work_permit_expiry,date_employed,pdrp_expiry_date,passport_number,license_expiry_date,c.branch,staff_details.status 
            FROM staff_details LEFT JOIN staff_branch c ON staff_details.branch=c.id WHERE staff_details.status=1
            """, nativeQuery = true)
    List<Map<String, Object>> getStaffList();

    @Modifying
    @Transactional
    @Query("UPDATE StaffDetails s SET s.status = 0 WHERE s.id = :staffId")
    void deleteStaff(@Param("staffId") Integer staffId);

    @Query(value = """
            SELECT id, designation, email, branch, first_name, last_name, license_type, common_name, employee_number, phone, 
            date_employed, citizenship, passport_number, change_date, license_expiry_date, pdrp_expiry_date, work_permit_number, 
            work_permit_expiry, ctc, res_address, res_city, notes, passport, visa_type, own_vehicle, visa, driving_license,
            emirates_id, photo, labour_contract, status, home_contact, home_contact_no, emirates_id_no, license_no, other_doc, dob 
            FROM staff_details WHERE id = :staffId
            """, nativeQuery = true)
    List<Map<String, ?>> getBasicStaffDetails(@Param("staffId") Integer staffId);
}