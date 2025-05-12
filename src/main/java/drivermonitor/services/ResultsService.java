package drivermonitor.services;

import drivermonitor.models.Results;

import java.util.List;

public interface ResultsService {
    Results saveResult(Results result);
    List<Results> findAll();
    List<Results> getResultsByUserId(Integer userId);
    Results getLastResultByUserId(Integer userId);
}
