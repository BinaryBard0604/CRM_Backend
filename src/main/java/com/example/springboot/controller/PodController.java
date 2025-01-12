package com.example.springboot.controller;

import com.example.springboot.entity.PodFiles;
import com.example.springboot.repository.PodFilesRepository;
import com.example.springboot.service.PodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PodController {

    private static final Logger logger = LoggerFactory.getLogger(PodController.class);

    private final String UPLOAD_DIR = "/home/ubuntu/uploads/";

    @Autowired
    PodService podService;

    @Autowired
    PodFilesRepository podFilesRepository;

    @PostMapping("/pod")
    public List<Map<String, Object>> getPod(@RequestBody Map<String, String> payload) {
        String type = payload.get("type");

        if ("getbranch1".equals(type)) {
            String user_branch = payload.get("user");
            String user_role = payload.get("role");

            return podService.getBranch1(Integer.parseInt(user_branch), Integer.parseInt(user_role));
        } else if ("getuserlist".equals(type)) {
            String user_branch = payload.get("user");
            String user_role = payload.get("role");

            return podService.getUserList(Integer.parseInt(user_branch), Integer.parseInt(user_role));
        } else if ("uploadPOD".equals(type)) {
            String branch = payload.get("branch");
            String manager = payload.get("manager");
            String updated_on = payload.get("updated_on");

            return podService.uploadPod(branch, manager, updated_on);
        } else if ("podlist".equals(type)) {
            String manager = payload.get("manager");

            return podService.getPodList(manager);
        } else if ("deleterecord".equals(type)) {
            String deleteId = payload.get("deleteid");

            return podService.deleteRecord(deleteId);
        } else if ("podview".equals(type)) {
             String podId = payload.get("manager");

             return podService.podView(podId);
        } else {
            return null;
        }
    }

    @PostMapping("/podUpload")
    public ResponseEntity<Map<String, Object>> uploadPOD(
            @RequestParam("pod_id") Integer pod_id,
            @RequestParam("filelist") MultipartFile[] files) {

        Map<String, Object> response = new HashMap<>();
        List<String> uploadedFiles = new ArrayList<>();

        // Create directory if it doesn't exist
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                response.put("status", "error");
                response.put("message", "Error uploading the file!");
                return ResponseEntity.badRequest().body(response);
            }

            try {
                String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR, fileName);
                Files.copy(file.getInputStream(), filePath);

                // Save file path to the database
                PodFiles podFile = new PodFiles();
                podFile.setPodId(pod_id);

                String relativePath = "../" + filePath.toString().replace("/home/ubuntu/", "");
                relativePath = relativePath.substring(0, relativePath.length() - 1);

                podFile.setPodPath(relativePath);
                podFile.setStatus(1);
                podFilesRepository.save(podFile);

                uploadedFiles.add(fileName);
            } catch (IOException e) {
                response.put("status", "error");
                response.put("message", "Error uploading the file!");
                return ResponseEntity.badRequest().body(response);
            }
        }

        response.put("status", "1");
        response.put("message", "Upload file successfully");
        return ResponseEntity.ok(response);
    }
}
