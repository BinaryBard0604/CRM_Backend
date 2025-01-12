package com.example.springboot.controller;

import com.example.springboot.entity.Admin;
import com.example.springboot.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");

        Admin user = loginService.authenticate(username, password);
        Map<String, Object> response = new HashMap<>();

        if (user == null) {
            response.put("status", false);
            response.put("message", "Username not exist");
            return ResponseEntity.ok(response);
        } else {
            response.put("userid", user.getId());
            response.put("branch", user.getBranch());
            response.put("role", user.getRole());
            response.put("status", true);
            response.put("message", "Success. Will redirect to dashboard in a moment.");
            return ResponseEntity.ok(response);
        }
    }
}