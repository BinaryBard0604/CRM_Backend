package com.example.springboot.Service;

import com.example.springboot.Entity.*;
import com.example.springboot.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Part;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CompanyService {

    private static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAllWithStatus();
    }

    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Optional<Company> updateCompany(Long id, Company company) {
        return companyRepository.findById(id).map(existingCompany -> {
            existingCompany.setName(company.getName());
            existingCompany.setWebsite(company.getWebsite());
            existingCompany.setLinkedin(company.getLinkedin());
            existingCompany.setPhoneNumber(company.getPhoneNumber());
            existingCompany.setSector(company.getSector());
            existingCompany.setSize(company.getSize());
            existingCompany.setRevenue(company.getRevenue());
            existingCompany.setTax(company.getTax());
            existingCompany.setName(company.getName());
            existingCompany.setStateAbbr(company.getAddress());
            existingCompany.setCity(company.getCity());
            existingCompany.setZipCode(company.getZipCode());
            existingCompany.setStateAbbr(company.getStateAbbr());
            existingCompany.setCountry(company.getCountry());
            existingCompany.setDescription(company.getDescription());
            existingCompany.setUser(company.getUser());
            existingCompany.setStatus(company.getStatus());
            return companyRepository.save(existingCompany);
        });
    }

    public ResponseEntity<Map<String, String>> deleteCompany(Long id) {

        Map<String, String> result = new HashMap<>();

        try {
            companyRepository.deleteByIdWithStatus(id);

            result.put("msg", "The company is deleted successfully.");
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An error occurred while deleting the company"));
        }
    }
}