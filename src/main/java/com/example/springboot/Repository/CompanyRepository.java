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
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = """
            SELECT * FROM company WHERE status = 1
            """, nativeQuery = true)
    List<Company> findAllWithStatus();

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE Company c SET c.status = 0 WHERE c.id = :id
            """)
    void deleteByIdWithStatus(@Param("id") Long id);
}