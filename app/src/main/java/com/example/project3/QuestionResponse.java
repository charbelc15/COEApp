package com.example.project3;

public class QuestionResponse {

    private final int questionId;
    private final int responseId;
    private final String selectedAnswer;

    public QuestionResponse(int questionId, int responseId, String selectedAnswer){
        this.questionId = questionId;
        this.responseId = responseId;
        this.selectedAnswer = selectedAnswer;

    }

    public int getQuestionId() {
        return questionId;
    }

    public int getResponseId() {
        return responseId;
    }

    public String getSelectedAnswer() {
        return selectedAnswer;
    }
}
