package drivermonitor.services;
import drivermonitor.models.Manager;
import java.util.List;
import java.util.Optional;
public interface ManagerService {
    Manager saveManager(Manager manager);
    Optional<Manager> getManagerById(Integer id);
    void updateEmployeesList(Integer id, List<Integer> employeesList);
}