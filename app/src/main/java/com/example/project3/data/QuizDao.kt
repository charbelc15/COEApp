package com.example.project3.data

import androidx.room.*

@Dao
abstract class QuizDao {

    // used to insert responses. you will most likely not use this and will use insertAttemptWithResponse instead
    // returns the array of ids inserted
    @Insert
    abstract fun insertAllResponses(responses: List<QuestionResponse>): LongArray

    // used to insert an attempt. you will most likely use insertAttemptWithResponse instead
    @Insert
    abstract fun insertAttempt(attempt: Attempt): Long

    @Query("DELETE FROM attempts")
    abstract fun deleteAllAttempts()

    @Query("DELETE FROM responses")
    abstract fun deleteAllResponses()

    @Transaction
    @Query("SELECT * FROM attempts")
    abstract fun attemptsWithResponses(): List<AttemptWithResponses>

    @Transaction
    open fun insertAttemptWithResponses(a: Attempt, responses: List<QuestionResponse>): Long {
        val attemptId = insertAttempt(a)
        responses.forEach { it.attemptId = attemptId }
        insertAllResponses(responses)
        return attemptId
    }
}