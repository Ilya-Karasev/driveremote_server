package com.example.driveremote.repositories;

import com.example.driveremote.models.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findByReceiver(Integer receiverId);
    List<Request> findBySender(Integer senderId);
}
