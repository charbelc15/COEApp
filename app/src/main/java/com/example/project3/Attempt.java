package com.example.project3;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.project3.data.Question;

import java.time.LocalDate;
import java.util.List;

public class Attempt {
    private final LocalDate date;
    private final int quizType;
    private final double cumulativeScore;
    private final List<Question> questions;
    private final List<String> answers;
    private final int attemptId;

    @RequiresApi(api = Build.VERSION_CODES.O)

    public Attempt(int attemptId, int quizType, double cumulativeScore, List<Question> questions, List<String> answers ){
        LocalDate quizDate = LocalDate.now();
        this.date = quizDate;
        this.quizType = quizType;
        this.cumulativeScore = cumulativeScore;
        this.questions = questions;
        this.answers = answers;
        this.attemptId = attemptId;

    }

    public LocalDate getDate() {
        return date;
    }

    public int getQuizType() {
        return quizType;
    }

    public double getCumulativeScore() {
        return cumulativeScore;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getAttemptId() {
        return attemptId;
    }

}
