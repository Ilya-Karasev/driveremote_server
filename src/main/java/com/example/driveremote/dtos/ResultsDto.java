package com.example.driveremote.dtos;

import java.time.LocalDate;

public class ResultsDto {
    private Integer id;
    private Integer userId;
    private LocalDate testDate;
    private int emotionalExhaustionScore;
    private int depersonalizationScore;
    private int personalAchievementScore;
    private int totalScore;
    private String status;

    public ResultsDto(Integer id, Integer userId, LocalDate testDate, int emotionalExhaustionScore,
                      int depersonalizationScore, int personalAchievementScore, int totalScore) {
        this.id = id;
        this.userId = userId;
        this.testDate = testDate;
        this.emotionalExhaustionScore = emotionalExhaustionScore;
        this.depersonalizationScore = depersonalizationScore;
        this.personalAchievementScore = personalAchievementScore;
        this.totalScore = totalScore;
        this.status = calculateStatus(totalScore);
    }

    private String calculateStatus(int totalScore) {
        if (totalScore >= 106) return "Критическое";
        if (totalScore >= 66) return "Внимание";
        return "Норма";
    }

    // Getters and Setters
}