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
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = """
            SELECT * FROM customer WHERE status = 1
            """, nativeQuery = true)
    List<Customer> findAllWithStatus();

    @Query(value = """
                SELECT 
                    customer.id, 
                    customer.customer_rank, 
                    customer.email, 
                    customer.name, 
                    customer.phone, 
                    customer.salesperson, 
                    customer.status, 
                    customer.supplier_rank,
                    CASE
                        WHEN customer.team_id = team.id THEN team.name
                        ELSE ''
                    END AS team,
                    customer.type
                FROM 
                    customer
                LEFT JOIN 
                    team ON customer.team_id = team.id
                WHERE 
                    customer.status = 1
                ORDER BY 
                    customer.id;
            """, nativeQuery = true)
    List<Map<String, Object>> getAllData();

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