package drivermonitor.services.impl;

import drivermonitor.models.Driver;
import drivermonitor.repositories.DriverRepository;
import drivermonitor.services.DriverService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
@EnableCaching
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }
    @Override
    @CacheEvict(value = "drivers", allEntries = true)
    public Driver saveDriver(Driver driver) {
        return driverRepository.save(driver);
    }
    @Override
    @Cacheable("drivers")
    public Optional<Driver> getDriverById(Integer id) {
        return driverRepository.findById(id);
    }
    @Override
    @CacheEvict(value = "drivers", allEntries = true)
    public void updateCompletionStatus(Integer id, Boolean isCompleted) {
        driverRepository.findById(id).ifPresent(driver -> {
            driver.setIsCompleted(isCompleted);
            driverRepository.save(driver);
        });
    }
    @Override
    @CacheEvict(value = "drivers", allEntries = true)
    public void updateQuantity(Integer id, Integer quantity) {
        driverRepository.findById(id).ifPresent(driver -> {
            driver.setQuantity(quantity);
            driverRepository.save(driver);
        });
    }
    @Override
    @CacheEvict(value = "drivers", allEntries = true)
    public void updateStatus(Integer id, String status) {
        driverRepository.findById(id).ifPresent(driver -> {
            driver.setStatus(status);
            driverRepository.save(driver);
        });
    }
}