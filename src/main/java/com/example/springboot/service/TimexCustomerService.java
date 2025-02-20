package com.example.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class TimexCustomerService {

    private final String url = "https://www.timexpress.ae/modal/get_timex_customers.php";

    @Autowired
    private RestTemplate restTemplate;

    public Map<String, String> getTimexCustomers() {
        Map<String, String> result = new HashMap<>();
        try {
            // Make a POST request (you can add request body if needed)
            String response = restTemplate.postForObject(url, null, String.class);

            result.put("result", response);
        } catch (Exception e) {
            String response = "Error: " + e.getMessage();

            result.put("result", response);
        }

        return result;
    }
}
