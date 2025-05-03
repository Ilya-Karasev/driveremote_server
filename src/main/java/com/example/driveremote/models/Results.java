package com.example.driveremote.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "results")
public class Results {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Внешний ключ на User
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy — HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy — HH:mm")
    private LocalDateTime testDate; // Изменено на LocalDateTime для хранения времени

    @Column(nullable = false)
    private int emotionalExhaustionScore;

    @Column(nullable = false)
    private int depersonalizationScore;

    @Column(nullable = false)
    private int personalAchievementScore;

    @Column(nullable = false)
    private int totalScore;

    // Не хранится в БД — вычисляется
    @Transient
    public String getStatus() {
        if (totalScore >= 106) return "Критическое";
        if (totalScore >= 66) return "Внимание";
        return "Норма";
    }

    public Results() {}

    public Results(User user, LocalDateTime testDate, int emotionalExhaustionScore, int depersonalizationScore,
                   int personalAchievementScore, int totalScore) {
        this.user = user;
        this.testDate = testDate;
        this.emotionalExhaustionScore = emotionalExhaustionScore;
        this.depersonalizationScore = depersonalizationScore;
        this.personalAchievementScore = personalAchievementScore;
        this.totalScore = totalScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTestDate() {
        return testDate;
    }

    public void setTestDate(LocalDateTime testDate) {
        this.testDate = testDate;
    }

    public int getEmotionalExhaustionScore() {
        return emotionalExhaustionScore;
    }

    public void setEmotionalExhaustionScore(int emotionalExhaustionScore) {
        this.emotionalExhaustionScore = emotionalExhaustionScore;
    }

    public int getDepersonalizationScore() {
        return depersonalizationScore;
    }

    public void setDepersonalizationScore(int depersonalizationScore) {
        this.depersonalizationScore = depersonalizationScore;
    }

    public int getPersonalAchievementScore() {
        return personalAchievementScore;
    }

    public void setPersonalAchievementScore(int personalAchievementScore) {
        this.personalAchievementScore = personalAchievementScore;
    }
}