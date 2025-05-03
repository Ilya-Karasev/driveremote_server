package com.example.driveremote.services;

import com.example.driveremote.models.Manager;
import com.example.driveremote.repositories.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public Manager saveManager(Manager manager) {
        return managerRepository.save(manager);
    }

    @Override
    public Optional<Manager> getManagerById(Integer id) {
        return managerRepository.findById(id);
    }

    @Override
    public void updateEmployeesList(Integer id, List<Integer> employeesList) {
        managerRepository.findById(id).ifPresent(manager -> {
            manager.setEmployeesList(employeesList);
            managerRepository.save(manager);
        });
    }
}