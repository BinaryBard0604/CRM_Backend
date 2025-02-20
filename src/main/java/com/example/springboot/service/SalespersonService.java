package com.example.springboot.service;

import com.example.springboot.entity.Salesperson;
import com.example.springboot.repository.SalespersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SalespersonService {

    private static final Logger logger = LoggerFactory.getLogger(SalespersonService.class);

    @Autowired
    private SalespersonRepository salespersonRepository;

    public List<Salesperson> getAllSalespersons() {
        return salespersonRepository.getAllSalesperson();
    }

    public Salesperson createSalesperson(Salesperson salesperson) {
        return salespersonRepository.save(salesperson);
    }

    public Optional<Salesperson> updateLoginDate(LocalDateTime loginTime, String email) {
        Long id = salespersonRepository.getId(email);

        logger.info("----------" + id);

        return salespersonRepository.findById(id).map(existingSalesperson -> {
            existingSalesperson.setName(existingSalesperson.getName());
            existingSalesperson.setEmail(existingSalesperson.getEmail());
            existingSalesperson.setMobile(existingSalesperson.getMobile());
            existingSalesperson.setPhone(existingSalesperson.getPhone());
            existingSalesperson.setStatus(existingSalesperson.getStatus());
            existingSalesperson.setLatest_login(loginTime);
            return salespersonRepository.save(existingSalesperson);
        });
    }

    public Optional<Salesperson> getSalespersonById(Long id) {
        return salespersonRepository.findById(id);
    }

    public Optional<Salesperson> updateSalesperson(Long id, Salesperson salesperson) {
        return salespersonRepository.findById(id).map(existingSalesperson -> {
            existingSalesperson.setName(salesperson.getName());
            existingSalesperson.setEmail(salesperson.getEmail());
            existingSalesperson.setPhone(salesperson.getPhone());
            existingSalesperson.setMobile(salesperson.getMobile());
            existingSalesperson.setLatest_login(salesperson.getLatest_login());
            existingSalesperson.setStatus(salesperson.getStatus());
            return salespersonRepository.save(existingSalesperson);
        });
    }
}
