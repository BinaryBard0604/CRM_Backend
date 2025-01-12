package com.example.springboot.service;

import com.example.springboot.repository.CustomerBranchRepository;
import com.example.springboot.entity.CustomerBranch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerBranchService {

    @Autowired
    private CustomerBranchRepository customerBranchRepository;

    public List<Object[]> getBranches(Integer userBranch, Integer userRole) {
        if (userBranch == 0 || userRole == 1) {
            // No filtering, return all active branches
            return customerBranchRepository.findBranchList();
        } else {
            // Filter by branch IDs
            List<Long> branchIds = new ArrayList<>();
            for (String id : userBranch.toString().split(",")) {
                branchIds.add(Long.parseLong(id.trim()));
            }
            return customerBranchRepository.findActiveBranchesByIds(branchIds);
        }
    }
}
