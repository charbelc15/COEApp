package com.example.project3;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Consumer;
import java.util.function.Function;

class QuestionViewHolder extends RecyclerView.ViewHolder
{
    public TextView questionText;
    public TextView correctAnswerText;
    public TextView userAnswerText;

    public Button tutorialButton;

    public QuestionViewHolder(@NonNull View itemView , AttemptViewActivity activity )
    {
        super(itemView);

        this.questionText = itemView.findViewById(R.id.QuestionText);
        this.correctAnswerText = itemView.findViewById(R.id.CorrectAnswerText);
        this.userAnswerText = itemView.findViewById(R.id.UserAnswerText);

        this.tutorialButton = itemView.findViewById(R.id.TutorialButton);

        this.tutorialButton.setOnClickListener(v -> { activity.QuestionSelected(getAdapterPosition());});

    }
}
