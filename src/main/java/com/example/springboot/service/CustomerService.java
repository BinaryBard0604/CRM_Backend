package com.example.springboot.Service;

import com.example.springboot.Entity.*;
import com.example.springboot.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAllWithStatus();
    }

    public List<Customer> getCustomerSalespersonById() {
        return customerRepository.getCustomerSalespersonById();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> updatedEmail(Long id, MultipartFile file) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(existingCustomer.getName());
            existingCustomer.setEmail(existingCustomer.getEmail());
            existingCustomer.setPhone(existingCustomer.getPhone());
            existingCustomer.setType(existingCustomer.getType());
            existingCustomer.setCustomer_rank(existingCustomer.getCustomer_rank());
            existingCustomer.setSupplier_rank(existingCustomer.getSupplier_rank());
            existingCustomer.setStatus(existingCustomer.getStatus());
            existingCustomer.setReference(existingCustomer.getReference());
            try {
                existingCustomer.setFile_data(file.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            existingCustomer.setFile_name(file.getOriginalFilename());
            existingCustomer.setMobile(existingCustomer.getMobile());
            return customerRepository.save(existingCustomer);
        });
    }

    public Optional<Customer> updateCustomer(Long id, Customer customer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setType(customer.getType());
            existingCustomer.setCustomer_rank(customer.getCustomer_rank());
            existingCustomer.setSupplier_rank(customer.getSupplier_rank());
            existingCustomer.setStatus(customer.getStatus());
            existingCustomer.setFile_data(customer.getFile_data());
            existingCustomer.setFile_name(customer.getFile_name());
            existingCustomer.setReference(customer.getReference());
            existingCustomer.setMobile(customer.getMobile());
            return customerRepository.save(existingCustomer);
        });
    }

    public Optional<Customer> updateCustomer1(Long id, Customer customer) {
        return customerRepository.findById(id).map(existingCustomer -> {
            existingCustomer.setName(customer.getName());
            existingCustomer.setEmail(customer.getEmail());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setType(customer.getType());
            existingCustomer.setCustomer_rank(customer.getCustomer_rank());
            existingCustomer.setSupplier_rank(customer.getSupplier_rank());
            existingCustomer.setStatus(customer.getStatus());
            existingCustomer.setFile_data(existingCustomer.getFile_data());
            existingCustomer.setFile_name(existingCustomer.getFile_name());
            existingCustomer.setReference(customer.getReference());
            existingCustomer.setMobile(customer.getMobile());
            return customerRepository.save(existingCustomer);
        });
    }

    public ResponseEntity<Map<String, String>> deleteCustomer(Long id) {

        Map<String, String> result = new HashMap<>();

        try {
            List<Map<String, Object>> check1 = customerRepository.check1(id);
            List<Map<String, Object>> check2 = customerRepository.check2(id);

            if ((check1.size() == 0 || check1 == null) && (check2.size() == 0 || check2 == null)) {
                customerRepository.deleteByIdWithStatus(id);

                result.put("flag", "0");
                result.put("msg", "The customer is deleted successfully.");
            } else {
                result.put("flag", "1");
                result.put("msg", "Cannot delete");
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An error occurred while deleting the customer"));
        }
    }
}