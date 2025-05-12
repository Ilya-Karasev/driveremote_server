package drivermonitor.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    private Integer id; // Совпадает с ID пользователя

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "manager_employees", joinColumns = @JoinColumn(name = "manager_id"))
    @Column(name = "employee_id")
    private List<Integer> employeesList;

    public Manager() {}
    public Manager(Integer id, List<Integer> employeesList) {
        this.id = id;
        this.employeesList = employeesList;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public List<Integer> getEmployeesList() { return employeesList; }
    public void setEmployeesList(List<Integer> employeesList) { this.employeesList = employeesList; }
}