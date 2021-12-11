package com.example.project3.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class AttemptWithResponses {

    @Embedded public Attempt attempt;

    @Relation(parentColumn = "attemptId", entityColumn = "attemptId")
    public List<QuestionResponse> responses;
}
