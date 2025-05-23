package drivermonitor.services;
import drivermonitor.models.User;
import java.util.List;
import java.util.Optional;
public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserByEmailAndPassword(String email, String password);
    Optional<User> getUserById(Integer id);
    List<User> getUsersByIds(List<Integer> ids);
}