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
public class PartnerService {

    private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);

    @Autowired
    private PartnerRepository partnerRepository;

    public List<Partner> getAllPartners() {
        return partnerRepository.findAllWithStatus();
    }

    public Optional<Partner> getPartnerById(Long id) {
        return partnerRepository.findById(id);
    }

    public Partner createPartner(Partner partner) {
        return partnerRepository.save(partner);
    }

    public Optional<Partner> updatePartner(Long id, Partner partner) {
        return partnerRepository.findById(id).map(existingPartner -> {
            existingPartner.setName(partner.getName());
            existingPartner.setEmail(partner.getEmail());
            existingPartner.setPhone(partner.getPhone());
            existingPartner.setType(partner.getType());
            existingPartner.setCustomer_rank(partner.getCustomer_rank());
            existingPartner.setSupplier_rank(partner.getSupplier_rank());
            existingPartner.setStatus(partner.getStatus());
            return partnerRepository.save(existingPartner);
        });
    }

    public ResponseEntity<Map<String, String>> deletePartner(Long id) {

        Map<String, String> result = new HashMap<>();

//        try {
            partnerRepository.deleteByIdWithStatus(id);

            result.put("msg", "The partner is deleted successfully.");
            return ResponseEntity.ok(result);
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An error occurred while deleting the partner"));
//        }
    }
}