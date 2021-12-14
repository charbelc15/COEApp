package com.example.project3.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.Instant;

@Entity(tableName = "attempts")
public class Attempt {
    private Instant date;
    private String quizType;
    private double cumulativeScore;

    @PrimaryKey(autoGenerate = true)
    private long attemptId;

    public Attempt() {
        date = Instant.now();
    }

    public Instant getDate() {
        return date;
    }

    public String getQuizType() {
        return quizType;
    }

    public double getCumulativeScore() {
        return cumulativeScore;
    }

    public long getAttemptId() {
        return attemptId;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public void setCumulativeScore(double cumulativeScore) {
        this.cumulativeScore = cumulativeScore;
    }

    public void setAttemptId(long attemptId) {
        this.attemptId = attemptId;
    }

    public void addScore(double score) {
        this.cumulativeScore += score;
    }
}
