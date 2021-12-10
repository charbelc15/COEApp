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
    private int quizType;
    private double cumulativeScore;

    @PrimaryKey(autoGenerate = true)
    private long attemptId;

    @RequiresApi(api = Build.VERSION_CODES.O)

    public Attempt() {

    }

    public Attempt(int attemptId, int quizType, double cumulativeScore){
        this.date = Instant.now();
        this.quizType = quizType;
        this.cumulativeScore = cumulativeScore;
        this.attemptId = attemptId;
    }

    public Instant getDate() {
        return date;
    }

    public int getQuizType() {
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

    public void setQuizType(int quizType) {
        this.quizType = quizType;
    }

    public void setCumulativeScore(double cumulativeScore) {
        this.cumulativeScore = cumulativeScore;
    }

    public void setAttemptId(long attemptId) {
        this.attemptId = attemptId;
    }
}
