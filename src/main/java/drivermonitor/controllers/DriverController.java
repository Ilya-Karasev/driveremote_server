package drivermonitor.controllers;
import drivermonitor.models.Driver;
import drivermonitor.services.DriverService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    private final DriverService driverService;
    private static final Logger logger = LoggerFactory.getLogger(ResultsController.class);
    private ModelMapper modelMapper;
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }
    @PostMapping
    public Driver saveDriver(@RequestBody Driver driver) {
        logger.info("Создание водителя: {}", driver.getId());
        return driverService.saveDriver(driver);
    }
    @GetMapping("/{id}")
    public Optional<Driver> getDriverById(@PathVariable Integer id) {
        logger.info("Получение водителя по id: {}", id);
        return Optional.of(driverService.getDriverById(id)).orElseThrow(() -> new NotFoundException("водителя с ID " + id));
    }
    @GetMapping("/user/{userId}")
    public Optional<Driver> getDriverByUserId(@PathVariable Integer userId) {
        logger.info("Получение водителя по id пользователя: {}", userId);
        return Optional.of(driverService.getDriverById(userId)).orElseThrow(() -> new NotFoundException("водителя с ID " + userId));
    }
    @PutMapping("/{id}")
    public Optional<Driver> updateDriver(@PathVariable Integer id, @RequestBody Driver updatedDriver) {
        logger.info("Обновление водителя по id: {}", id);
        Driver upd = new Driver(id, updatedDriver.getIsCompleted(), updatedDriver.getTestingTime(), updatedDriver.getQuantity(), updatedDriver.getStatus());
        Driver saved = driverService.saveDriver(upd);
        return Optional.of(saved);

    }
}