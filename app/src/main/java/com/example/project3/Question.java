package com.example.project3;


import java.net.URL;
import java.util.List;

public class Question
{
    private final int questionId;
    private final String question;
    private final List<String> possibleAnswers;
    private final String correctAnswer;
    private final URL link;
    private final int score;


    public Question(int questionId, String question, List<String> possibleAnswers, String correctAnswer, URL link, int score) {
        this.questionId = questionId;
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
        this.link = link;
        this.score = score;



    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public URL getLink() {
        return link;
    }

    public int getScore() {
        return score;
    }
}
