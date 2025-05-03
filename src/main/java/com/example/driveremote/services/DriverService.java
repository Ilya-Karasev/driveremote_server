package com.example.driveremote.services;

import com.example.driveremote.models.Driver;

import java.util.Optional;

public interface DriverService {
    Driver saveDriver(Driver driver);
    Optional<Driver> getDriverById(Integer id);
    void updateCompletionStatus(Integer id, Boolean isCompleted);
    void updateQuantity(Integer id, Integer quantity);
    void updateStatus(Integer id, String status);
}
