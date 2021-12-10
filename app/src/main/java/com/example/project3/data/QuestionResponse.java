package com.example.project3.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "responses")
public class QuestionResponse {

    public int questionId;

    @PrimaryKey(autoGenerate = true)
    public long responseId;
    public String selectedAnswer;

    public long attemptId;

    public QuestionResponse(int questionId, long responseId, String selectedAnswer){
        this.questionId = questionId;
        this.responseId = responseId;
        this.selectedAnswer = selectedAnswer;

    }
}
