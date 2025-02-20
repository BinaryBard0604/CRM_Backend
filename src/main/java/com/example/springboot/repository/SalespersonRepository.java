package com.example.springboot.repository;

import com.example.springboot.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SalespersonRepository extends JpaRepository<Salesperson, Long> {

    @Query(value = """
            SELECT * FROM salesperson WHERE status = 1
            """, nativeQuery = true)
    List<Salesperson> getAllSalesperson();

    @Query(value = """
            SELECT id FROM salesperson WHERE email = :email AND status = 1
            """, nativeQuery = true)
    Long getId(@Param("email") String email);
}