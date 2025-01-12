package com.example.springboot.repository;

import com.example.springboot.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    @Query("SELECT a FROM Admin a WHERE a.username = :username AND a.password = :password")
    Optional<Admin> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT a FROM Admin a WHERE a.status = 1 ORDER BY a.id DESC")
    List<Admin> findAllActiveUsers();

    @Query("SELECT a.user FROM Admin a WHERE a.id = :user")
    String getUserName(@Param("user") Integer user);

    @Query(value = """
            SELECT admin.id,user,branch,username,email,phone,role From admin  WHERE admin.status=1 ORDER BY id DESC
            """, nativeQuery = true)
    List<Map<String, Object>> getDataWithStatus();
}