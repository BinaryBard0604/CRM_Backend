package com.example.springboot.Repository;

import com.example.springboot.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(@Param("email") String email);

    @Query(value = """
            SELECT user.id, user.email, user.name, user.password, role.role FROM user JOIN role WHERE user.role_id = role.id AND user.status = 1
            """, nativeQuery = true)
    List<Map<String, Object>> findAllWithStatus();
}