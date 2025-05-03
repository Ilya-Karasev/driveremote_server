package com.example.driveremote.services;

import com.example.driveremote.models.Results;
import com.example.driveremote.repositories.ResultsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultsServiceImpl implements ResultsService {
    private final ResultsRepository resultsRepository;

    public ResultsServiceImpl(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }

    @Override
    public Results saveResult(Results result) {
        return resultsRepository.save(result);
    }

    @Override
    public List<Results> findAll() {
        return resultsRepository.findAll();
    }

    @Override
    public List<Results> getResultsByUserId(Integer userId) {
        return resultsRepository.findByUserIdOrderByTestDateDesc(userId);
    }

    @Override
    public Results getLastResultByUserId(Integer userId) {
        return resultsRepository.findFirstByUserIdOrderByTestDateDesc(userId);
    }
}