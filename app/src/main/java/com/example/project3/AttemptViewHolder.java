package com.example.project3;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AttemptViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    TextView textQuizType;
    TextView textScore;

    // needed to jump to other acitivies
    onAttemptClickedListener listener;

    public AttemptViewHolder(@NonNull View itemView , onAttemptClickedListener listener)
    {
        super(itemView);

        textQuizType = itemView.findViewById(R.id.textQuizType);
        textScore = itemView.findViewById(R.id.textScore);

        this.listener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        this.listener.onAttemptClicked(getAdapterPosition());
    }

    public interface onAttemptClickedListener
    {
        void onAttemptClicked(int position );
    }

}
