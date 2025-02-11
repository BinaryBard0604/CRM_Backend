package com.example.springboot.Controller;

import com.example.springboot.Config.JwtUtil;
import com.example.springboot.Config.PasswordUtil;
import com.example.springboot.Entity.User;
import com.example.springboot.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowCredentials = "true")
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        // Find the user by email
        User existingUser = userRepository.findByEmail(user.getEmail());

        // Check if the user exists
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        if (existingUser.getPassword() != null && !existingUser.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        // Update the user's details
        existingUser.setName(existingUser.getName());
        existingUser.setRole_id(existingUser.getRole_id());
        existingUser.setEmail(existingUser.getEmail());
        existingUser.setPassword(PasswordUtil.hashPassword(user.getPassword())); // Encode the new password

        // Save the updated user
        userRepository.save(existingUser);

        return ResponseEntity.ok("User updated successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());

        // Check if the user exists
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        // Check if the password is empty
        if (existingUser.getPassword() == null || existingUser.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must register");
        }

        // Validate the password
        if (existingUser.getPassword().equals(PasswordUtil.hashPassword(user.getPassword()))) {
            String token = JwtUtil.generateToken(user.getEmail());
            logger.info(">>>>>>>>>>>> {}", token);
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @GetMapping("/email")
    public Optional<User> findUserByEmail(@RequestParam String email) {
        return userRepository.findUserByEmail(email);
    }
}