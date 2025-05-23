package drivermonitor.init;
import drivermonitor.models.*;
import drivermonitor.repositories.DriverRepository;
import drivermonitor.repositories.ManagerRepository;
import drivermonitor.repositories.ResultsRepository;
import drivermonitor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private ResultsRepository resultsRepository;
    @Override
    public void run(String... args) throws Exception {
        seedData();
    }
    private void seedData() {
        if (userRepository.count() == 0) {
            List<User> testUsers = Arrays.asList(
                    new User("Иванов", "Иван", "Иванович", 30, Post.ВОДИТЕЛЬ, "driver@example.com", "driver123"),
                    new User("Петров", "Петр", "Петрович", 40, Post.РУКОВОДИТЕЛЬ, "manager@example.com", "manager123")
            );
            userRepository.saveAll(testUsers);
        }
        userRepository.findByPost(Post.ВОДИТЕЛЬ).forEach(user -> {
            if (!driverRepository.existsById(user.getId())) {
                Driver driver = new Driver(user.getId(), false, List.of("08:00"), 1, "Норма");
                driverRepository.save(driver);
            }
        });
        userRepository.findByPost(Post.РУКОВОДИТЕЛЬ).forEach(user -> {
            if (!managerRepository.existsById(user.getId())) {
                Manager manager = new Manager(user.getId(), List.of());
                managerRepository.save(manager);
            }
        });
        if (resultsRepository.count() == 0) {
            User driverUser = userRepository.findByEmail("driver@example.com").orElseThrow();
            Results result1 = new Results(driverUser, LocalDateTime.now(), 30, 20, 15, 65);
            Results result2 = new Results(driverUser, LocalDateTime.now().minusDays(1), 35, 22, 18, 75);
            resultsRepository.saveAll(Arrays.asList(result1, result2));
        }
    }
}