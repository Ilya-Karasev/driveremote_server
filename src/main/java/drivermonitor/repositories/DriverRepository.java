package drivermonitor.repositories;

import drivermonitor.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    boolean existsById(Integer id);
    Optional<Driver> findById(Integer id);
}

