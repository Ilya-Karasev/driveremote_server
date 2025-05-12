package drivermonitor.repositories;

import drivermonitor.models.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultsRepository extends JpaRepository<Results, Integer> {
    List<Results> findByUser_IdOrderByTestDateDesc(Integer userId);
    Results findFirstByUser_IdOrderByTestDateDesc(Integer userId);
}
