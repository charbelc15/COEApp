package com.example.project3;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project3.data.AppDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize database
        AppDatabase.initDB(getApplicationContext());

        MediaPlayer waterSound = MediaPlayer.create(this, R.raw.watersound);
        ImageView news = this.findViewById(R.id.news);
        ImageView quiz = this.findViewById(R.id.quiz);
        ImageView headquarters = this.findViewById(R.id.headquarters);
        ImageView attempts = this.findViewById(R.id.attemptPageImageView);

        quiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                waterSound.start();
                Intent intent = new Intent(MainActivity.this, QuizTypeActivity.class);
                startActivity(intent);
            }
        });

        news.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                waterSound.start();
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        headquarters.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                waterSound.start();
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        // hook listener for attempts page
        attempts.setOnClickListener( v ->
        {
            waterSound.start();
            Intent intent = new Intent( MainActivity.this , AttemptsActivity.class );
            startActivity(intent);
        });




    }
}