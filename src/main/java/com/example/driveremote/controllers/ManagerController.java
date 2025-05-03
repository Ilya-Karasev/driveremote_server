package com.example.driveremote.controllers;

import com.example.driveremote.models.Manager;
import com.example.driveremote.services.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping
    public Manager saveManager(@RequestBody Manager manager) {
        return managerService.saveManager(manager);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manager> getManagerById(@PathVariable Integer id) {
        Optional<Manager> manager = managerService.getManagerById(id);
        return manager.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/employees")
    public void updateEmployeesList(@PathVariable Integer id, @RequestBody List<Integer> employeesList) {
        managerService.updateEmployeesList(id, employeesList);
    }
}