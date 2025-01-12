package com.example.springboot.service;

import com.example.springboot.entity.Admin;
import com.example.springboot.entity.CustomerBranch;
import com.example.springboot.repository.AdminRepository;
import com.example.springboot.repository.CustomerBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private CustomerBranchRepository customerBranchRepository;

    @Autowired
    private AdminRepository adminRepository;

    public List<Object[]> getBranchList() {
        return customerBranchRepository.findBranchList();
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<Map<String, Object>> getUserListWithBranches() {
        // Fetch all active users and branches
        List<Admin> users = adminRepository.findAllActiveUsers();
        List<CustomerBranch> branches = customerBranchRepository.findByStatus();

        // Convert branches to a Map for quick lookup
        Map<Integer, String> branchMap = branches.stream()
                .collect(Collectors.toMap(CustomerBranch::getId, CustomerBranch::getBranch));

        List<Map<String, Object>> userList = new ArrayList<>();

        for (Admin user : users) {
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("id", String.valueOf(user.getId()));
            userMap.put("user", user.getUser());
            userMap.put("branch", user.getBranch());
            userMap.put("username", user.getUsername());
            userMap.put("email", user.getEmail());
            userMap.put("phone", user.getPhone());
            userMap.put("role", String.valueOf(user.getRole()));

            // Handle branch names
            String branchIds = user.getBranch();
            List<String> branchNames = new ArrayList<>();

            if (!branchIds.isEmpty()) {
                for (String branchId : branchIds.split(",")) {
                    int bid = Integer.parseInt(branchId.trim());
                    if (branchMap.containsKey(bid)) { // Use branchMap for lookup
                        branchNames.add(branchMap.get(bid));
                    }
                }
            }

            userMap.put("branch_name", branchNames.isEmpty() ? "All" : String.join(", ", branchNames));
            userList.add(userMap);
        }

        return userList;
    }
}
