package drivermonitor.controllers;

import drivermonitor.models.User;
import drivermonitor.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
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
        return Optional.of(userService.getUserByEmailAndPassword(email, password))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Неверные логин или пароль"));
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        return Optional.of(userService.getUserById(id))
                .orElseThrow(() -> new NotFoundException("пользователя с ID " + id));
    }

    @PostMapping("/by-ids")
    public List<User> getUsersByIds(@RequestBody List<Integer> ids) {
        return userService.getUsersByIds(ids);
    }
}