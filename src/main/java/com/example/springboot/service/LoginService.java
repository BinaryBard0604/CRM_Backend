package com.example.springboot.service;

import com.example.springboot.entity.Admin;
import com.example.springboot.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class LoginService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin authenticate(String username, String password) {
        String hashedPassword = hashPassword(password);
        return adminRepository.findByUsernameAndPassword(username, hashedPassword).orElse(null);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : messageDigest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
