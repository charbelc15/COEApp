package com.example.project3.data;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.project3.data.Question;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

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
