package drivermonitor.controllers;

import drivermonitor.models.Manager;
import drivermonitor.services.ManagerService;
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
    public Optional<Manager> getManagerById(@PathVariable Integer id) {
        return Optional.of(managerService.getManagerById(id)).orElseThrow(() -> new NotFoundException("руководителя с ID " + id));
    }

    @PatchMapping("/{id}/employees")
    public void updateEmployeesList(@PathVariable Integer id, @RequestBody List<Integer> employeesList) {
        managerService.getManagerById(id);
        managerService.updateEmployeesList(id, employeesList);
    }
}