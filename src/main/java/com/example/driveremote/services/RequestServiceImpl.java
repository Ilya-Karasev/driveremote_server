package com.example.driveremote.services;

import com.example.driveremote.models.Request;
import com.example.driveremote.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Integer id) {
        requestRepository.deleteById(id);
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public List<Request> getRequestsForReceiver(Integer receiverId) {
        return requestRepository.findByReceiver(receiverId);
    }

    @Override
    public List<Request> getRequestsForSender(Integer senderId) {
        return requestRepository.findBySender(senderId);
    }
}