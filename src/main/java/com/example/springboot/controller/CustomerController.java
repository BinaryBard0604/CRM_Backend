package com.example.springboot.Controller;

import com.example.springboot.Entity.*;
import com.example.springboot.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }   

    @GetMapping("/salesperson")
    public List<Customer> getCustomerSalespersonById() {
        return customerService.getCustomerSalespersonById();
    }

    @PostMapping("/updatedEmail")
    public Optional<Customer> updatedEmail(@RequestBody Map<String, String> payload) {
        String id = payload.get("id");
        String email = payload.get("email");

        return customerService.updatedEmail(Long.parseLong(id), email);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Optional<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteCustomer(@RequestBody Map<String, Long> payload) {
        return customerService.deleteCustomer(payload.get("id"));
    }
}