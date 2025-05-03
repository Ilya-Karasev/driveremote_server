package com.example.driveremote.controllers;

import com.example.driveremote.models.Request;
import com.example.driveremote.services.RequestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {
    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public Request createRequest(@RequestBody Request request) {
        return requestService.createRequest(request);
    }

    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Integer id) {
        requestService.deleteRequest(id);
    }

    @GetMapping
    public List<Request> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/receiver/{receiver}")
    public List<Request> getRequestsForReceiver(@PathVariable Integer receiver) {
        System.out.println("Получаем запросы для receiver: " + receiver);
        return requestService.getRequestsForReceiver(receiver);
    }

    @GetMapping("/sender/{sender}")
    public List<Request> getRequestsForSender(@PathVariable Integer sender) {
        return requestService.getRequestsForSender(sender);
    }
}