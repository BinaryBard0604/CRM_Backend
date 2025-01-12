package com.example.springboot.repository;

import com.example.springboot.entity.PodFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PodFilesRepository extends JpaRepository<PodFiles, Long> {

    List<PodFiles> findByPodId(Long podId);
}
