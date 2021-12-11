package com.example.project3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.project3.data.AppDatabase;
import com.example.project3.data.Attempt;
import com.example.project3.data.AttemptWithResponses;

import java.util.ArrayList;
import java.util.List;

public class AttemptsActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempts);

        // init the recycler view and its components

        recyclerView = findViewById(R.id.recyclerViewAttempts);
        recyclerView.setNestedScrollingEnabled(true); // to work properly with scroll view
//        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // fetch the attempts from the db and hand them to the recycler view

        List<AttemptWithResponses> values =
        AppDatabase.db.getQuizDao().attemptsWithResponses();

        ArrayList<Attempt> attempts = new ArrayList<>();

        for ( int i = 0 ; i < values.size() ; ++i )
        {
            attempts.add(values.get(i).attempt);
        }

        recyclerViewAdapter = new AttemptRecyclerViewAdapter( attempts );
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}