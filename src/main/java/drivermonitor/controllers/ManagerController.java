package drivermonitor.controllers;
import drivermonitor.models.Manager;
import drivermonitor.services.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/managers")
public class ManagerController {
    private final ManagerService managerService;
    private static final Logger logger = LoggerFactory.getLogger(ResultsController.class);
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }
    @PostMapping
    public Manager saveManager(@RequestBody Manager manager) {
        logger.info("Создание руководителя: {}", manager.getId());
        return managerService.saveManager(manager);
    }
    @GetMapping("/{id}")
    public Optional<Manager> getManagerById(@PathVariable Integer id) {
        logger.info("Получение руководителя по id пользователя: {}", id);
        return Optional.of(managerService.getManagerById(id)).orElseThrow(() -> new NotFoundException("руководителя с ID " + id));
    }
    @PatchMapping("/{id}/employees")
    public void updateEmployeesList(@PathVariable Integer id, @RequestBody List<Integer> employeesList) {
        logger.info("Обновление списка подчинённых у руководителя: {}", id);
        managerService.getManagerById(id);
        managerService.updateEmployeesList(id, employeesList);
    }
}