package com.example.project3.data;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;

public class AttemptWithResponses implements Serializable {

    @Embedded public Attempt attempt;

    @Relation(parentColumn = "attemptId", entityColumn = "attemptId")
    public List<QuestionResponse> responses;
}
