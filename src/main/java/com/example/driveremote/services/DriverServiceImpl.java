package com.example.driveremote.services;

import com.example.driveremote.models.Driver;
import com.example.driveremote.repositories.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;

    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }

    @Override
    public Optional<Driver> getDriverById(Integer id) {
        return driverRepository.findById(id);
    }

    @Override
    public void updateCompletionStatus(Integer id, Boolean isCompleted) {
        driverRepository.findById(id).ifPresent(driver -> {
            driver.setIsCompleted(isCompleted);
            driverRepository.save(driver);
        });
    }

    @Override
    public void updateQuantity(Integer id, Integer quantity) {
        driverRepository.findById(id).ifPresent(driver -> {
            driver.setQuantity(quantity);
            driverRepository.save(driver);
        });
    }

    @Override
    public void updateStatus(Integer id, String status) {
        driverRepository.findById(id).ifPresent(driver -> {
            driver.setStatus(status);
            driverRepository.save(driver);
        });
    }
}