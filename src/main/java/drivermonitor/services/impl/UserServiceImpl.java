package drivermonitor.services.impl;
import drivermonitor.models.User;
import drivermonitor.repositories.UserRepository;
import drivermonitor.services.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
@Service
@EnableCaching
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User createUser(User user) {
        return userRepository.save(user);
    }
    @Override
    @Cacheable("users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Override
    @Cacheable(value = "users")
    public Optional<User> getUserByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmailAndPassword(email, password);
        if (user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Неверные логин или пароль");
        }
        return user;
    }
    @Override
    @Cacheable(value = "users")
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
    @Override
    @Cacheable(value = "users")
    public List<User> getUsersByIds(List<Integer> ids) {
        return userRepository.findByIdIn(ids);
    }
}