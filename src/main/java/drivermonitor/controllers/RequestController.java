package drivermonitor.controllers;

import drivermonitor.models.Request;
import drivermonitor.services.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/requests")
public class RequestController {
    private final RequestService requestService;
    private static final Logger logger = LoggerFactory.getLogger(ResultsController.class);
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }
    @PostMapping
    public Request createRequest(@RequestBody Request request) {
        logger.info("Создание запроса: {}", request.getId());
        return requestService.createRequest(request);
    }
    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Integer id) {
        logger.info("Удаление запроса: {}", id);
        requestService.deleteRequest(id);
    }
    @GetMapping
    public List<Request> getAllRequests() {
        logger.info("Получение всех запросов");
        return requestService.getAllRequests();
    }
    @GetMapping("/receiver/{receiver}")
    public List<Request> getRequestsForReceiver(@PathVariable Integer receiver) {
        logger.info("Получение запросов по получателю: {}", receiver);
        return requestService.getRequestsForReceiver(receiver);
    }
    @GetMapping("/sender/{sender}")
    public List<Request> getRequestsForSender(@PathVariable Integer sender) {
        logger.info("Получение запросов по отправителю: {}", sender);
        return requestService.getRequestsForSender(sender);
    }
}