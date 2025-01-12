package com.example.springboot.repository;

import com.example.springboot.entity.PodUploads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Repository
public interface PodUploadsRepository extends JpaRepository<PodUploads, Long> {

    @Query(value = """
            Select p.id,b.branch,p.manager,p.updated_on,a.user 
            FROM pod_uploads p LEFT JOIN admin a ON a.id=p.manager 
            LEFT JOIN customer_branch b ON b.id=p.branch WHERE p.status=1 AND p.manager = :manager
            """, nativeQuery = true)
    List<Map<String, Object>> getPodList(@RequestParam("manager") Integer manager);
}
