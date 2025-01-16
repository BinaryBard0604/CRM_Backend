package com.example.springboot.Repository;

import com.example.springboot.Entity.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = """
            SELECT * FROM customer WHERE status = 1
            """, nativeQuery = true)
    List<Customer> findAllWithStatus();

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
}