package com.example.springboot.repository;

import com.example.springboot.entity.CustomerBranch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerBranchRepository extends JpaRepository<CustomerBranch, Long> {

    @Query("SELECT c.id, c.branch FROM CustomerBranch c WHERE c.status = 1")
    List<Object[]> findBranchList();

    @Query("SELECT c FROM CustomerBranch c WHERE c.status = 1")
    List<CustomerBranch> findByStatus();

    @Query("SELECT b FROM CustomerBranch b WHERE b.id IN :ids")
    List<CustomerBranch> findByIdIn(@Param("ids") List<Long> ids);

    @Query("SELECT cb.id, cb.branch FROM CustomerBranch cb WHERE cb.status = 1 AND cb.id IN :branchIds")
    List<Object[]> findActiveBranchesByIds(@Param("branchIds") List<Long> branchIds);

    @Query("SELECT cb FROM CustomerBranch cb WHERE cb.id IN :branchIds")
    List<CustomerBranch> getBranchById(@Param("branchIds") List<Integer> branchIds);

    @Query("SELECT cb.id AS id, cb.branch AS branch FROM CustomerBranch cb WHERE cb.status = 1")
    List<Map<String, Object>> findDataByStatus();

    @Query("SELECT cb.id AS id, cb.branch AS branch FROM CustomerBranch cb WHERE cb.status = 1 AND cb.id = :user_branch")
    List<Map<String, Object>> findDataByIdInAndStatus(@Param("user_branch") Integer user_branch);

    @Query(value = """
        SELECT 
            CAST(id AS CHAR) AS id, 
            CAST(branch AS CHAR) AS branch 
        FROM customer_branch 
        WHERE status = 1
        """, nativeQuery = true)
    List<Map<String, String>> getBranch();
}