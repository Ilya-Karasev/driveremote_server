package drivermonitor.controllers;

import drivermonitor.models.Driver;
import drivermonitor.services.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    private final DriverService driverService;
    private ModelMapper modelMapper;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public Driver saveDriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }

    @GetMapping("/{id}")
    public Optional<Driver> getDriverById(@PathVariable Integer id) {
        return Optional.of(driverService.getDriverById(id)).orElseThrow(() -> new NotFoundException("водителя с ID " + id));
    }

    @GetMapping("/user/{userId}")
    public Optional<Driver> getDriverByUserId(@PathVariable Integer userId) {
        return Optional.of(driverService.getDriverById(userId)).orElseThrow(() -> new NotFoundException("водителя с ID " + userId));
    }

    @PutMapping("/{id}")
    public Optional<Driver> updateDriver(@PathVariable Integer id, @RequestBody Driver updatedDriver) {
        Driver upd = new Driver(updatedDriver.getId(), updatedDriver.getIsCompleted(), updatedDriver.getTestingTime(), updatedDriver.getQuantity(), updatedDriver.getStatus());
        Driver saved = driverService.saveDriver(upd);
        return Optional.of(saved);

    }
}