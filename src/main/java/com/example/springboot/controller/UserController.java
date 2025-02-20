package com.example.springboot.controller;

import com.example.springboot.entity.User;
import com.example.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<Map<String, Object>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/admin/{id}")
    public Optional<User> updateAdminUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateAdminUser(id, user);
    }

    @PutMapping("/admin/nohash/{id}")
    public Optional<User> updateAdminUsernohash(@PathVariable Long id, @RequestBody User user) {
        return userService.updateAdminUsernohash(id, user);
    }

    @PutMapping("/{id}")
    public Optional<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @PutMapping("/nohash/{id}")
    public Optional<User> updateUsernohash(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUsernohash(id, user);
    }
    
    @DeleteMapping("/{id}")
    public Map<String, String> deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}