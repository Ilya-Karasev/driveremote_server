package drivermonitor.models;
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "dd.MM.yyyy — HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy — HH:mm")
    private LocalDateTime testDate;
    @Column(nullable = false)
    private int emotionalExhaustionScore;
    @Column(nullable = false)
    private int depersonalizationScore;
    @Column(nullable = false)
    private int personalAchievementScore;
    @Column(nullable = false)
    private int totalScore;
    @Transient
    public String getStatus() {
        if (totalScore >= 106) return "Критическое";
        if (totalScore >= 66) return "Внимание";
        return "Норма";
    }
    public Results() {}
    public Results(User user, LocalDateTime testDate, int emotionalExhaustionScore,
                   int depersonalizationScore, int personalAchievementScore, int totalScore) {
        this.user = user;
        this.testDate = testDate;
        this.emotionalExhaustionScore = emotionalExhaustionScore;
        this.depersonalizationScore = depersonalizationScore;
        this.personalAchievementScore = personalAchievementScore;
        this.totalScore = totalScore;
    }
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDateTime getTestDate() { return testDate; }
    public void setTestDate(LocalDateTime testDate) { this.testDate = testDate; }
    public int getEmotionalExhaustionScore() { return emotionalExhaustionScore; }
    public void setEmotionalExhaustionScore(int score) { this.emotionalExhaustionScore = score; }
    public int getDepersonalizationScore() { return depersonalizationScore; }
    public void setDepersonalizationScore(int score) { this.depersonalizationScore = score; }
    public int getPersonalAchievementScore() { return personalAchievementScore; }
    public void setPersonalAchievementScore(int score) { this.personalAchievementScore = score; }
    public int getTotalScore() { return totalScore; }
    public void setTotalScore(int totalScore) { this.totalScore = totalScore; }
}