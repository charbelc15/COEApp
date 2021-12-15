package com.example.project3.data;

import android.provider.SearchRecentSuggestions;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "responses")
public class QuestionResponse implements Serializable {

    public int questionId;

    @PrimaryKey(autoGenerate = true)
    public long responseId;
    public String selectedAnswer;

    public long attemptId;

    public QuestionResponse() {

    }

    public QuestionResponse(int questionId, String selectedAnswer){
        this.questionId = questionId;
        this.selectedAnswer = selectedAnswer;
    }
}
