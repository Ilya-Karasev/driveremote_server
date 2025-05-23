package drivermonitor.services.impl;

import drivermonitor.models.Request;
import drivermonitor.repositories.RequestRepository;
import drivermonitor.services.RequestService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;

    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    @CacheEvict(value = {"requestsForReceiver", "requestsForSender", "requests"}, allEntries = true)
    public Request createRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    @CacheEvict(value = {"requestsForReceiver", "requestsForSender", "requests"}, allEntries = true)
    public void deleteRequest(Integer id) {
        requestRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "requests")
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    @Cacheable(value = "requestsForReceiver")
    public List<Request> getRequestsForReceiver(Integer receiverId) {
        return requestRepository.findByReceiver(receiverId);
    }

    @Override
    @Cacheable(value = "requestsForSender")
    public List<Request> getRequestsForSender(Integer senderId) {
        return requestRepository.findBySender(senderId);
    }
}