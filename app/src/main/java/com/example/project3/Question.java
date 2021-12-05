package com.example.project3;


import android.os.Parcel;
import android.os.Parcelable;

import java.net.URL;
import java.util.List;

public class Question implements Parcelable
{
    private final int questionId;
    private final String question;
    private final List<String> possibleAnswers;
    private final String correctAnswer;
    private URL link;
    private final int score;


    public Question(int questionId, String question, List<String> possibleAnswers, String correctAnswer, URL link, int score) {
        this.questionId = questionId;
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.correctAnswer = correctAnswer;
        this.link = link;
        this.score = score;



    }

    protected Question(Parcel in) {
        questionId = in.readInt();
        question = in.readString();
        possibleAnswers = in.createStringArrayList();
        correctAnswer = in.readString();
        score = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(questionId);
        dest.writeString(question);
        dest.writeStringList(possibleAnswers);
        dest.writeString(correctAnswer);
        dest.writeInt(score);
    }
}
