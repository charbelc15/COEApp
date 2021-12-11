package com.example.project3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project3.data.Attempt;

import java.util.ArrayList;
import java.util.List;

public class AttemptRecyclerViewAdapter extends RecyclerView.Adapter<AttemptViewHolder>
{
    private List<Attempt> attempts;

    public AttemptRecyclerViewAdapter (ArrayList<Attempt> attempts )
    {
        this.attempts = attempts;
    }

    @NonNull
    @Override
    public AttemptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // inflate a layout
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_attempt_layout , parent , false );
        AttemptViewHolder holder = new AttemptViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AttemptViewHolder holder, int position)
    {
        // fill holder with data

        Attempt current = attempts.get(position);

        holder.textQuizType.setText(current.getQuizType());
        holder.textScore.setText(""+current.getCumulativeScore());
    }

    @Override
    public int getItemCount()
    {
        return attempts.size();
    }
}
