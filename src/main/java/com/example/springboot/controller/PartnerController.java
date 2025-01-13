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
@RequestMapping("/api/partners")
public class PartnerController {

    @Autowired
    private PartnerService partnerService;

    @GetMapping
    public List<Partner> getAllPartners() {
        return partnerService.getAllPartners();
    }

    @GetMapping("/{id}")
    public Optional<Partner> getPartnerById(@PathVariable Long id) {
        return partnerService.getPartnerById(id);
    }

    @PostMapping
    public Partner createPartner(@RequestBody Partner partner) {
        return partnerService.createPartner(partner);
    }

    @PutMapping("/{id}")
    public Optional<Partner> updatePartner(@PathVariable Long id, @RequestBody Partner partner) {
        return partnerService.updatePartner(id, partner);
    }

    @PostMapping("/delete")
    public ResponseEntity<Map<String, String>> deletePartner(@RequestBody Map<String, Long> payload) {
        return partnerService.deletePartner(payload.get("id"));
    }
}