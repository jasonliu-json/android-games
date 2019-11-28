package uoft.csc207.gameapplication.Utility.GameRequestService.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    @JsonProperty("username")
    private String username;

    @JsonProperty("userScores")
    private List<LeaderBoard> userScores;

    @JsonProperty("totalScore")
    private String totalPoints;

    @JsonProperty("timePlayed")
    private String timePlayed;

    @JsonProperty("currentStage")
    private String currentStage;

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }

    public String getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(String totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(String timePlayed) {
        this.timePlayed = timePlayed;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public List<LeaderBoard> getUserScores() {
        return userScores;
    }

    public void setUserScores(List<LeaderBoard> userScores) {
        this.userScores = userScores;
    }
}
