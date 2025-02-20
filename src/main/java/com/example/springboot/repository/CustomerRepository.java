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
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = """
            SELECT id FROM customer WHERE reference = :awb
            """, nativeQuery = true)
    Map<String, Long> findIDByAWB(@Param("awb") String awb);

    @Query(value = """
            SELECT customer.*, salesperson.name as created_salesperson_name, salesperson.email as created_salesperson_email 
            FROM customer JOIN salesperson ON customer.created_salespersonid = salesperson.id WHERE customer.status = 1
            """, nativeQuery = true)
    List<Map<String, Object>> findAllWithStatus();

    @Query(value = """
            SELECT * FROM customer WHERE reference = :awb
            """, nativeQuery = true)
    Optional<Customer> findByAWB(@Param("awb") String awb);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Customer p SET p.status = 0 WHERE p.id = :id
            """)
    void deleteByIdWithStatus(@Param("id") Long id);

    @Query(value = """
            SELECT * FROM customer WHERE status = 1 AND salesperson = 1
            """, nativeQuery = true)
    List<Customer> getCustomerSalespersonById();

    @Query(value = """
            SELECT * FROM customer 
            JOIN opportunity ON customer.id = opportunity.contact_id
            WHERE customer.id = :id
            """, nativeQuery = true)
    List<Map<String, Object>> check1(@Param("id") Long id);

    @Query(value = """
            SELECT * FROM customer 
            JOIN opportunity ON customer.id = opportunity.salesperson_id
            WHERE customer.id = :id
            """, nativeQuery = true)
    List<Map<String, Object>> check2(@Param("id") Long id);
}