package drivermonitor.services.impl;
import drivermonitor.models.Results;
import drivermonitor.repositories.ResultsRepository;
import drivermonitor.services.ResultsService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@EnableCaching
public class ResultsServiceImpl implements ResultsService {
    private final ResultsRepository resultsRepository;
    public ResultsServiceImpl(ResultsRepository resultsRepository) {
        this.resultsRepository = resultsRepository;
    }
    @Override
    @CacheEvict(value = {"results"}, allEntries = true)
    public Results saveResult(Results result) {
        return resultsRepository.save(result);
    }

    @Override
    @Cacheable(value = "results")
    public List<Results> findAll() {
        return resultsRepository.findAll();
    }
    @Override
    @Cacheable(value = "results")
    public List<Results> getResultsByUserId(Integer userId) {
        return resultsRepository.findByUser_IdOrderByTestDateDesc(userId);
    }
    @Override
    @Cacheable(value = "results")
    public Results getLastResultByUserId(Integer userId) {
        return resultsRepository.findFirstByUser_IdOrderByTestDateDesc(userId);
    }
}