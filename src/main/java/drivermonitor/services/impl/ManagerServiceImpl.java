package drivermonitor.services.impl;

import drivermonitor.models.Manager;
import drivermonitor.repositories.ManagerRepository;
import drivermonitor.services.ManagerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@EnableCaching
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    @CacheEvict(value = "managers", allEntries = true)
    public Manager saveManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    @Cacheable("managers")
    public Optional<Manager> getManagerById(Integer id) {
        return managerRepository.findById(id);
    }

    @Override
    @CacheEvict(value = "managers", allEntries = true)
    public void updateEmployeesList(Integer id, List<Integer> employeesList) {
        managerRepository.findById(id).ifPresent(manager -> {
            manager.setEmployeesList(employeesList);
            managerRepository.save(manager);
        });
    }
}