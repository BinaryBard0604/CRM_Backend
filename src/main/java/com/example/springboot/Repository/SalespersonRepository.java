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
public interface SalespersonRepository extends JpaRepository<Salesperson, Long> {

    @Query(value = """
            SELECT * FROM salesperson WHERE status = 1
            """, nativeQuery = true)
    List<Salesperson> getAllSalesperson();

    @Query(value = """
            SELECT id FROM salesperson WHERE email = :email
            """, nativeQuery = true)
    Long getId(@Param("email") String email);
}