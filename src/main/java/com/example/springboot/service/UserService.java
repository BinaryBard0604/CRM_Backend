package com.example.springboot.Service;

import com.example.springboot.Config.PasswordUtil;
import com.example.springboot.Entity.Salesperson;
import com.example.springboot.Entity.User;
import com.example.springboot.Repository.SalespersonRepository;
import com.example.springboot.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SalespersonRepository salespersonRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<Map<String, Object>> getAllUsers() {
        return userRepository.findAllWithStatus();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        User creatingUser = new User();

        creatingUser.setName(user.getName());
        creatingUser.setRole_id(user.getRole_id());
        creatingUser.setEmail(user.getEmail());
        creatingUser.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        creatingUser.setStatus(1);

        return userRepository.save(creatingUser);
    }

    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            Long salespersonId = salespersonRepository.getId(existingUser.getEmail());
            salespersonRepository.findById(salespersonId).map(existingSalesperson -> {
                existingSalesperson.setName(user.getName());
                existingSalesperson.setEmail(user.getEmail());
                existingSalesperson.setPhone(existingSalesperson.getPhone());
                existingSalesperson.setMobile(existingSalesperson.getMobile());
                existingSalesperson.setCompany(existingSalesperson.getCompany());
                existingSalesperson.setLatest_login(existingSalesperson.getLatest_login());
                existingSalesperson.setStatus(existingSalesperson.getStatus());

                return existingSalesperson;
            });

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole_id(user.getRole_id());
            existingUser.setPassword(PasswordUtil.hashPassword(user.getPassword()));
            existingUser.setStatus(1);
            return userRepository.save(existingUser);
        });
    }

    public Map<String, String> deleteUser(Long id) {
        try {
            userRepository.findById(id).map(existingUser -> {
                Long salespersonId = salespersonRepository.getId(existingUser.getEmail());
                salespersonRepository.findById(salespersonId).map(existingSalesperson -> {
                    existingSalesperson.setName(existingSalesperson.getName());
                    existingSalesperson.setEmail(existingSalesperson.getEmail());
                    existingSalesperson.setPhone(existingSalesperson.getPhone());
                    existingSalesperson.setMobile(existingSalesperson.getMobile());
                    existingSalesperson.setLatest_login(existingSalesperson.getLatest_login());
                    existingSalesperson.setCompany(existingSalesperson.getCompany());
                    existingSalesperson.setStatus(0);
                    return salespersonRepository.save(existingSalesperson);
                });

                existingUser.setName(existingUser.getName());
                existingUser.setEmail(existingUser.getEmail());
                existingUser.setRole_id(existingUser.getRole_id());
                existingUser.setPassword(existingUser.getPassword());
                existingUser.setStatus(0);
                return userRepository.save(existingUser);
            });

            Map<String, String> result = new HashMap<>();

            result.put("msg", "Delete Succesfully");

            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}