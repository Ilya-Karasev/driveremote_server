package com.example.driveremote.controllers;

import com.example.driveremote.models.Driver;
import com.example.driveremote.services.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public Driver saveDriver(@RequestBody Driver driver) {
        return driverService.saveDriver(driver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriverById(@PathVariable Integer id) {
        Optional<Driver> driver = driverService.getDriverById(id);
        return driver.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Integer id, @RequestBody Driver updatedDriver) {
        Optional<Driver> existingOpt = driverService.getDriverById(id);
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Driver existing = existingOpt.get();
        existing.setIsCompleted(updatedDriver.getIsCompleted());
        existing.setTestingTime(updatedDriver.getTestingTime());
        existing.setQuantity(updatedDriver.getQuantity());
        existing.setStatus(updatedDriver.getStatus());

        Driver saved = driverService.saveDriver(existing);
        return ResponseEntity.ok(saved);
    }
}