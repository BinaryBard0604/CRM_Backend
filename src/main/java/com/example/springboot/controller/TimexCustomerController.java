package com.example.springboot.Controller;

import com.example.springboot.Service.TimexCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TimexCustomerController {

    @Autowired
    private TimexCustomerService timexCustomerService;

    @GetMapping("/timex-customers")
    public Map<String, String> getTimexCustomers() {
        return timexCustomerService.getTimexCustomers();
    }
}
