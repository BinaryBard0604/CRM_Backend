package com.example.springboot.Service;

import com.example.springboot.Entity.Opportunity;
import com.example.springboot.Entity.Salesperson;
import com.example.springboot.Repository.OpportunityRepository;
import com.example.springboot.Repository.SalespersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class SalespersonService {

    @Autowired
    private SalespersonRepository salespersonRepository;

    public List<Salesperson> getAllSalespersons() {
        return salespersonRepository.getAllSalesperson();
    }

    public Salesperson createSalesperson(Salesperson salesperson) {
        return salespersonRepository.save(salesperson);
    }
}
