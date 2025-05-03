package com.example.driveremote.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "driver")
public class Driver {
    @Id
    private Integer id; // совпадает с id пользователя

    @Column(nullable = false)
    private Boolean isCompleted;

    @ElementCollection
    @CollectionTable(name = "driver_testing_time", joinColumns = @JoinColumn(name = "driver_id"))
    @Column(name = "testing_time")
    private List<String> testingTime;

    @Column(nullable = false)
    private Integer quantity; // 1 или 2

    @Column(nullable = false)
    private String status = "Норма";

    public Driver() {}
    public Driver(Integer id, Boolean isCompleted, List<String> testingTime, Integer quantity, String status) {
        this.id = id;
        this.isCompleted = isCompleted;
        this.testingTime = testingTime;
        this.quantity = quantity;
        this.status = status;
    }

    // Геттеры и сеттеры
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Boolean getIsCompleted() { return isCompleted; }
    public void setIsCompleted(Boolean isCompleted) { this.isCompleted = isCompleted; }

    public List<String> getTestingTime() { return testingTime; }
    public void setTestingTime(List<String> testingTime) { this.testingTime = testingTime; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) {
        if (quantity != 1 && quantity != 2)
            throw new IllegalArgumentException("Quantity must be 1 or 2");
        this.quantity = quantity;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}