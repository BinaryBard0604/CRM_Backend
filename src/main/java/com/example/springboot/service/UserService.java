package com.example.springboot.service;

import com.example.springboot.Config.PasswordUtil;
import com.example.springboot.entity.*;
import com.example.springboot.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    private OpportunityRepository opportunityRepository;

    @Autowired
    private SalespersonRepository salespersonRepository;

    @Autowired
    private TeamRepository teamRepository;

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

    public Optional<User> updateAdminUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole_id(user.getRole_id());
            existingUser.setPassword(PasswordUtil.hashPassword(user.getPassword()));
            existingUser.setStatus(1);
            return userRepository.save(existingUser);
        });
    }

    public Optional<User> updateAdminUsernohash(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole_id(user.getRole_id());
            existingUser.setPassword(user.getPassword());
            existingUser.setStatus(1);
            return userRepository.save(existingUser);
        });
    }

    public Optional<User> updateUser(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            Long salespersonId = salespersonRepository.getId(existingUser.getEmail());
            logger.info("----------" + salespersonId + "-------" + existingUser.getEmail());
            salespersonRepository.findById(salespersonId).map(existingSalesperson -> {
                existingSalesperson.setName(user.getName());
                existingSalesperson.setEmail(user.getEmail());
                existingSalesperson.setPhone(existingSalesperson.getPhone());
                existingSalesperson.setMobile(existingSalesperson.getMobile());
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

    public Optional<User> updateUsernohash(Long id, User user) {
        return userRepository.findById(id).map(existingUser -> {
            Long salespersonId = salespersonRepository.getId(existingUser.getEmail());
            logger.info("----------" + salespersonId + "-------" + existingUser.getEmail());
            salespersonRepository.findById(salespersonId).map(existingSalesperson -> {
                existingSalesperson.setName(user.getName());
                existingSalesperson.setEmail(user.getEmail());
                existingSalesperson.setPhone(existingSalesperson.getPhone());
                existingSalesperson.setMobile(existingSalesperson.getMobile());
                existingSalesperson.setLatest_login(existingSalesperson.getLatest_login());
                existingSalesperson.setStatus(existingSalesperson.getStatus());

                return existingSalesperson;
            });

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole_id(user.getRole_id());
            existingUser.setPassword(user.getPassword());
            existingUser.setStatus(1);
            return userRepository.save(existingUser);
        });
    }

    public Map<String, String> deleteUser(Long id) {
        Map<String, String> result = new HashMap<>();

        Optional<User> findUser = userRepository.findById(id);
        Long salespersonId = salespersonRepository.getId(findUser.get().getEmail());
        List<Map<String, Object>> check1 = opportunityRepository.getCheckSalesperson(salespersonId);

        if (check1.size() > 0) {
            result.put("msg", "This is in specific opportunity");
            result.put("flag", "0");
        } else  {
            List<Map<String, Object>> check2 = teamRepository.getCheckSalesperson(salespersonId);

            if (check2.size() > 0) {
                result.put("msg", "This is in specific team");
                result.put("flag", "0");
            } else {
                try {
                    userRepository.findById(id).map(existingUser -> {
                        salespersonRepository.findById(salespersonId).map(existingSalesperson -> {
                            existingSalesperson.setName(existingSalesperson.getName());
                            existingSalesperson.setEmail(existingSalesperson.getEmail());
                            existingSalesperson.setPhone(existingSalesperson.getPhone());
                            existingSalesperson.setMobile(existingSalesperson.getMobile());
                            existingSalesperson.setLatest_login(existingSalesperson.getLatest_login());
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

                    result.put("msg", "Delete Succesfully");
                    result.put("flag", "1");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return result;
    }
}