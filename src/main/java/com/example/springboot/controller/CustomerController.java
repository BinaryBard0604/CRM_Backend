package com.example.springboot.controller;

import com.example.springboot.entity.*;
import com.example.springboot.repository.CustomerRepository;
import com.example.springboot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Optional<Customer> getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/awb/{awb}")
    public Optional<Customer> getCustomerByAWB(@PathVariable String awb) {
        return customerService.getCustomerByAWB(awb);
    }

    @GetMapping("/salesperson")
    public List<Customer> getCustomerSalespersonById() {
        return customerService.getCustomerSalespersonById();
    }

    @PutMapping("/updatedEmail/{id}")
    public Optional<Customer> updatedEmail(@PathVariable Long id, @RequestParam("uploadedFile") MultipartFile file) {

        return customerService.updatedEmail(id, file);
    }

    @PutMapping("/updatedEmail/customerawb/{awb}")
    public Optional<Customer> updatedcustomerawbEmail(@PathVariable String awb, @RequestParam("uploadedFile") MultipartFile file) {

        return customerService.updatedcustomerawbEmail(awb, file);
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<?> getFile(@PathVariable Long id) {
        return customerRepository.findById(id).map(customer -> {
            byte[] fileData = customer.getFile_data();
            String fileName = customer.getFile_name();

            if (fileData == null || fileData.length == 0) {
                return ResponseEntity.notFound().build(); // File not found
            }

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName); // Use the file name from the database

            return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
        }).orElse(ResponseEntity.notFound().build()); // Customer not found
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Optional<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @PutMapping("/cusomter1/{id}")
    public Optional<Customer> updateCustomer1(@PathVariable Long id, @RequestBody Customer customer) {
        return customerService.updateCustomer1(id, customer);
    }

    @PutMapping("/customerawb/{awb}")
    public Optional<Customer> updatecustomerawbCustomer(@PathVariable String awb, @RequestBody Customer customer) {
        return customerService.updatecustomerawbCustomer(awb, customer);
    }

    @PutMapping("/cusomter1/customerawb/{awb}")
    public Optional<Customer> updatecustomerawbCustomer1(@PathVariable String awb, @RequestBody Customer customer) {
        return customerService.updatecustomerawbCustomer1(awb, customer);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> deleteCustomer(@RequestBody Map<String, Long> payload) {
        return customerService.deleteCustomer(payload.get("id"));
    }
}