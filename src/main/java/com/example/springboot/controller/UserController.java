package com.example.springboot.controller;

import com.example.springboot.entity.Admin;
import com.example.springboot.entity.CustomerBranch;
import com.example.springboot.repository.AdminRepository;
import com.example.springboot.repository.CustomerBranchRepository;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.server.ResponseStatusException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CustomerBranchRepository customerBranchRepository;

    @PostMapping("/branch-list")
    public List<Map<String, Object>> getBranchList() {

        List<Object[]> branchList = userService.getBranchList();

        List<Map<String, Object>> formattedBranchList = new ArrayList<>();
        for (Object[] row : branchList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", row[0]);
            map.put("branch", row[1]);
            formattedBranchList.add(map);
        }

        return formattedBranchList;
    }

    @PostMapping("/adduser")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody Map<String, ?> requestBody) {
        try {
            String name = String.valueOf(requestBody.get("name"));
            String username = String.valueOf(requestBody.get("username"));
            String password = String.valueOf(requestBody.get("password"));
            String email = String.valueOf(requestBody.get("email"));
            String phone = String.valueOf(requestBody.get("phone"));
            String branch = String.valueOf(requestBody.get("branch"));
            String result = Arrays.stream(branch.replaceAll("[\\[\\]]", "").split(","))
                    .map(String::trim) // Trim any extra spaces
                    .collect(Collectors.joining(","));

            logger.info(name);

            Admin admin = new Admin();
            admin.setUser(name);
            admin.setUsername(username);
            admin.setPassword(hashPassword(password)); // Hash password using MD5
            admin.setEmail(email);
            admin.setPhone(phone);
            admin.setBranch(result);
            admin.setStatus(1);

            Admin savedAdmin = userService.saveAdmin(admin);

            Map<String, Object> response = Map.of(
                    "message", "User Added",
                    "userid", savedAdmin.getId(),
                    "status", 1 // 1 for success
            );

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> errorResponse = Map.of(
                    "message", "Add user failed",
                    "status", 0 // 0 for failure
            );
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/userlist")
    public Map<String, Object> getUsers() {
        List<Map<String, Object>> userList = userService.getUserListWithBranches();

        Map<String, Object> response = new HashMap<>();
        response.put("list", userList);

        return response;
    }

    @PostMapping("/deleteuser")
    public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody Map<String, String> requestBody) {
        String deleteuserid = requestBody.get("deleteuserid");
        Long userId = Long.parseLong(deleteuserid);
        Map<String, Object> response = new HashMap<>();

        if (deleteuserid != null) {
            try {
                adminRepository.deleteById(userId);
                response.put("message", "Delete user successful");
                response.put("userid", deleteuserid);
                response.put("status", "1"); // 1 success
            } catch (Exception e) {
                response.put("message", "Delete user failed");
                response.put("status", "0"); // 0 failed
            }
        } else {
            response.put("message", "Delete user failed");
            response.put("status", "0"); // 0 failed
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/edituser")
    public ResponseEntity<Admin> editUser(@RequestBody Map<String, String> requestBody) {
        String user_id = requestBody.get("user_id");

        if (!"".equals(user_id)) {
            Long userId = Long.parseLong(user_id);
            Optional<Admin> user = adminRepository.findById(userId);
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/selectedBranch")
    public ResponseEntity<List<CustomerBranch>> selectedBranch(@RequestBody Map<String, String> requestBody) {
        String user_id = requestBody.get("user_id");
        Long userId = Long.parseLong(user_id);

        if (user_id != null) {
            Optional<Admin> user = adminRepository.findById(userId);
            if (user.isPresent()) {
                String branchIds = user.get().getBranch();
                List<CustomerBranch> branchDataArr = new ArrayList<>();

                if (!branchIds.isEmpty()) {
                    List<Long> branchIdsList = new ArrayList<>();
                    Arrays.stream(branchIds.split(","))
                            .map(Long::parseLong)
                            .forEach(branchIdsList::add);

                    branchDataArr = customerBranchRepository.findByIdIn(branchIdsList);
                }
                return ResponseEntity.ok(branchDataArr);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/updateuser")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, ?> requestBody) {
        // Extract user ID
        Long userId = Long.parseLong(String.valueOf(requestBody.get("user_id")));

        if (userId > 0) {
            // Create or update user object
            Admin admin = new Admin();

            adminRepository.findById(userId).map(existingItem -> {
                existingItem.setUser(String.valueOf(requestBody.get("name")));
                existingItem.setUsername(String.valueOf(requestBody.get("username")));

                String password = String.valueOf(requestBody.get("password"));
                if ("******".equals(password)) {
                    password = String.valueOf(requestBody.get("password1")); // Assuming this is sent
                } else {
                    password = hashPassword(password); // Hashing password
                }
                existingItem.setPassword(password);
                existingItem.setEmail(String.valueOf(requestBody.get("email")));
                existingItem.setPhone(String.valueOf(requestBody.get("phone")));

                // Handle branches
                String branch = String.valueOf(requestBody.get("branch"));
                String result = Arrays.stream(branch.replaceAll("[\\[\\]]", "").split(","))
                        .map(String::trim) // Trim any extra spaces
                        .collect(Collectors.joining(","));
                existingItem.setBranch(result);

                // Update user
                return adminRepository.save(existingItem);
            });

            return ResponseEntity.ok().body("{\"message\":\"Update user success\", \"status\":\"1\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\":\"Update user failed\", \"status\":\"0\"}");
        }
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
