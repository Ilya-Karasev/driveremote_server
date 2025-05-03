package com.example.driveremote.services;

import com.example.driveremote.models.Results;

import java.util.List;

public interface ResultsService {
    Results saveResult(Results result);
    List<Results> findAll();
    List<Results> getResultsByUserId(Integer userId);
    Results getLastResultByUserId(Integer userId);
}
