package drivermonitor.controllers;
import drivermonitor.models.Results;
import drivermonitor.models.User;
import drivermonitor.repositories.UserRepository;
import drivermonitor.services.ResultsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/results")
public class ResultsController {
    private final ResultsService resultsService;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(ResultsController.class);
    public ResultsController(ResultsService resultsService, UserRepository userRepository) {
        this.resultsService = resultsService;
        this.userRepository = userRepository;
    }
    @GetMapping("/results")
    public List<Results> getResults() {
        return resultsService.findAll();
    }
    @PostMapping
    public ResponseEntity<?> addResult(@RequestBody Map<String, Object> payload) {
        try {
            logger.info("Создание результатов: {}", payload);
            Integer userId = (Integer) payload.get("userId");
            String testDateStr = (String) payload.get("testDate");
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new NotFoundException("пользователя с ID " + userId));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy — HH:mm");
            LocalDateTime testDate = LocalDateTime.parse(testDateStr, formatter);
            int exhaustion = (Integer) payload.get("emotionalExhaustionScore");
            int depersonalization = (Integer) payload.get("depersonalizationScore");
            int achievement = (Integer) payload.get("personalAchievementScore");
            int total = (Integer) payload.get("totalScore");
            Results result = new Results(user, testDate, exhaustion, depersonalization, achievement, total);
            Results saved = resultsService.saveResult(result);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка: " + e.getMessage());
        }
    }
    @GetMapping("/user/{userId}")
    public List<Results> getResultsByUserId(@PathVariable Integer userId) {
        logger.info("Получение результатов пользователя: {}", userId);
        return resultsService.getResultsByUserId(userId);
    }
    @GetMapping("/user/{userId}/latest")
    public Results getLastResultByUserId(@PathVariable Integer userId) throws NotFoundException {
        logger.info("Получение последних результатов пользователя: {}", userId);
        Results result = resultsService.getLastResultByUserId(userId);
        if (result == null) {
            throw new NotFoundException("последний результат у пользователя с ID " + userId);
        }
        return result;
    }
}