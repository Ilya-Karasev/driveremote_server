package com.example.driveremote.repositories;

import com.example.driveremote.models.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    boolean existsById(Integer id);
}

