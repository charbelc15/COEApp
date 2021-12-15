package com.example.project3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project3.data.Attempt;
import com.example.project3.data.Question;
import com.example.project3.data.QuestionResponse;

import java.util.List;

public class QuestionsRecyclerViewAdapter extends RecyclerView.Adapter<QuestionViewHolder>
{

    private List<Question> questions;
    private List<QuestionResponse> responses;

    AttemptViewActivity activity;

    public QuestionsRecyclerViewAdapter(List<Question> questions , List<QuestionResponse> responses , AttemptViewActivity activity )
    {
        // question corresponds to each attempt, lists have equal size
        this.questions = questions;
        this.responses = responses;

        this.activity = activity;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // inflate the view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_question_layout , parent , false );
        QuestionViewHolder holder = new QuestionViewHolder(view , activity );

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position)
    {
        // fill holder with data

        // get the question text and correct answer from the Question object

        Question question = questions.get(position);
        String questionText = question.getQuestion();
        String correctAnswer = question.getCorrectAnswer();

        // get the user answer from the Attempts object

        String userAnswer = responses.get(position).selectedAnswer ;

        // fill in the text views in the holder
        holder.questionText.setText(questionText);
        holder.correctAnswerText.setText(correctAnswer);
        holder.userAnswerText.setText(userAnswer);
    }

    @Override
    public int getItemCount()
    {
        return questions.size();
    }
}
