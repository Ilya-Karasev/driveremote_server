package drivermonitor.controllers;
import drivermonitor.models.User;
import drivermonitor.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
        logger.info("Создание пользователя: {}", user.getEmail());
        return userService.createUser(user);
    }
    @GetMapping
    public List<User> getAllUsers() {
        logger.info("Получение всех пользователей");
        return userService.getAllUsers();
    }
    @GetMapping("/login")
    public Optional<User> getUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        logger.info("Попытка входа: {}", email);
        return Optional.of(userService.getUserByEmailAndPassword(email, password))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Неверные логин или пароль"));
    }
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Integer id) {
        logger.info("Получение пользователя по id: {}", id);
        return Optional.of(userService.getUserById(id))
                .orElseThrow(() -> new NotFoundException("пользователя с ID " + id));
    }
    @PostMapping("/by-ids")
    public List<User> getUsersByIds(@RequestBody List<Integer> ids) {
        logger.info("Получение пользователей по id: {}", ids);
        return userService.getUsersByIds(ids);
    }
}