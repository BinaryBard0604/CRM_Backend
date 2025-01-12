package com.example.springboot.controller;

import com.example.springboot.entity.CustomerBranch;
import com.example.springboot.service.CustomerBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerBranchController {

    @Autowired
    private CustomerBranchService customerBranchService;

    @PostMapping("/branches")
    public List<Map<String, Object>> getCustomerBranches(@RequestBody Map<String, String> requestBody) {
        String branch = requestBody.get("branch");
        String user_role = requestBody.get("user_role");

        int userRole = Integer.parseInt(user_role);
        int userBranch = Integer.parseInt(branch);

        List<Object[]> branches = customerBranchService.getBranches(userBranch, userRole);

        List<Map<String, Object>> formattedBranches = new ArrayList<>();
        for (Object[] row : branches) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", row[0]);
            map.put("branch", row[1]);
            formattedBranches.add(map);
        }
        return formattedBranches;
    }
}