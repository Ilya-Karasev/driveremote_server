package com.example.driveremote.controllers;

import com.example.driveremote.models.User;
import com.example.driveremote.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin // Чтобы разрешить доступ из Android-приложения
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/login")
    public Optional<User> getUserByEmailAndPassword(
            @RequestParam String email,
            @RequestParam String password
    ) {
        return userService.getUserByEmailAndPassword(email, password);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @PostMapping("/by-ids")
    public List<User> getUsersByIds(@RequestBody List<Integer> ids) {
        return userService.getUsersByIds(ids);
    }
}