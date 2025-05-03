package com.example.driveremote.services;

import com.example.driveremote.models.Manager;

import java.util.List;
import java.util.Optional;

public interface ManagerService {
    Manager saveManager(Manager manager);
    Optional<Manager> getManagerById(Integer id);
    void updateEmployeesList(Integer id, List<Integer> employeesList);
}