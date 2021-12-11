package com.example.project3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AttemptViewHolder extends RecyclerView.ViewHolder
{
    TextView textQuizType;
    TextView textScore;

    public AttemptViewHolder(@NonNull View itemView)
    {
        super(itemView);

        textQuizType = itemView.findViewById(R.id.textQuizType);
        textScore = itemView.findViewById(R.id.textScore);

    }
}
