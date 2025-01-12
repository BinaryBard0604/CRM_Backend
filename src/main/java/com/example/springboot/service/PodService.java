package com.example.springboot.service;

import com.example.springboot.controller.PodController;
import com.example.springboot.entity.PodFiles;
import com.example.springboot.entity.PodUploads;
import com.example.springboot.repository.AdminRepository;
import com.example.springboot.repository.CustomerBranchRepository;
import com.example.springboot.repository.PodFilesRepository;
import com.example.springboot.repository.PodUploadsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class PodService {

    private static final Logger logger = LoggerFactory.getLogger(PodService.class);

    @Autowired
    CustomerBranchRepository customerBranchRepository;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    PodUploadsRepository podUploadsRepository;

    @Autowired
    PodFilesRepository podFilesRepository;

    public List<Map<String, Object>> getBranch1(Integer user_branch, Integer user_role) {
        List<Map<String, Object>> branches = new ArrayList<>();

        if (user_branch == 0 || user_role == 1) {
            branches = customerBranchRepository.findDataByStatus();
        } else {
            branches = customerBranchRepository.findDataByIdInAndStatus(user_branch);
        }

        return branches;
    }

    public List<Map<String, Object>> getUserList(Integer user_branch, Integer user_role) {
        return adminRepository.getDataWithStatus();
    }

    public List<Map<String, Object>> uploadPod(String branch, String manager, String updated_on) {
        List<Map<String, Object>> responses = new ArrayList<>();
        Map<String, Object> response = new HashMap<>();
        PodUploads podUploads = new PodUploads();

        LocalTime currentTime = LocalTime.now();

        podUploads.setBranch(Integer.parseInt(branch));
        podUploads.setManager(Integer.parseInt(manager));
        podUploads.setUpdatedOn(LocalDate.parse(updated_on));
        podUploads.setStatus(1);
        podUploads.setUploadingDate(currentTime);

        try {
            PodUploads savedPodUpload = podUploadsRepository.save(podUploads);
            response.put("message", "Upload file successfully");
            response.put("id", savedPodUpload.getId());
            response.put("status", "1"); // 1 success
        } catch (Exception e) {
            logger.info(">>>>>>>>>>>" + e.getMessage());
            response.put("message", "Upload file failed");
            response.put("status", "0"); // 0 failed
        }

        responses.add(response);

        return responses;
    }

    public List<Map<String, Object>> getPodList(String manager) {
        return podUploadsRepository.getPodList(Integer.parseInt(manager));
    }

    public List<Map<String, Object>> deleteRecord(String deleteId) {
        List<PodFiles> files = podFilesRepository.findByPodId(Long.parseLong(deleteId));

        for (PodFiles file : files) {
            File podFile = new File(file.getPodPath());
            if (podFile.exists()) {
                podFile.delete();
            }
        }

        // Update pod_uploads status
        PodUploads podUpload = podUploadsRepository.findById(Long.parseLong(deleteId)).orElse(null);
        if (podUpload != null) {
            podUpload.setStatus(0);
            podUploadsRepository.save(podUpload);
        }

        // Update pod_files status
        for (PodFiles file : files) {
            file.setStatus(0);
            podFilesRepository.save(file);
        }

        return Collections.singletonList(Collections.singletonMap("status", 1));
    }

    public List<Map<String, Object>> podView(String podId) {
        List<PodFiles> files = podFilesRepository.findByPodId(Long.parseLong(podId));
        List<Map<String, Object>> dataArr = new ArrayList<>();

        int count = 1;
        for (PodFiles file : files) {
            Map<String, Object> fileData = new HashMap<>();
            String[] parts = file.getPodPath().split("POD/");
            if (parts.length > 1) {
                fileData.put("filename", parts[1]);
            } else {
                fileData.put("filename", file.getPodPath());
            }
            fileData.put("count", count);
            fileData.put("pod_path", file.getPodPath());
            fileData.put("id", file.getId());
            dataArr.add(fileData);
            count++;
        }

        List<Map<String, Object>> result = new ArrayList<>();

        Map<String, Object> returnData = new HashMap<>();
        returnData.put("list", dataArr);

        result.add(returnData);

        return result;
    }
}
