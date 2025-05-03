package com.example.driveremote.controllers;

import com.example.driveremote.models.Results;
import com.example.driveremote.services.ResultsService;
import org.hibernate.Hibernate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultsController {
    private final ResultsService resultsService;

    public ResultsController(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @GetMapping("/results")
    public List<Results> getResults() {
        List<Results> results = resultsService.findAll();
        for (Results result : results) {
            Hibernate.initialize(result.getUser());  // Инициализация ленивой связи
        }
        return results;
    }

    @PostMapping
    public Results saveResult(@RequestBody Results result) {
        result.setTestDate(LocalDateTime.now());  // Устанавливаем дату и время на текущий момент
        return resultsService.saveResult(result);
    }

    @GetMapping("/user/{userId}")
    public List<Results> getResultsByUserId(@PathVariable Integer userId) {
        return resultsService.getResultsByUserId(userId);
    }

    @GetMapping("/user/{userId}/latest")
    public Results getLastResultByUserId(@PathVariable Integer userId) {
        return resultsService.getLastResultByUserId(userId);
    }
}