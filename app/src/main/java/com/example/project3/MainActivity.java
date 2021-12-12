package com.example.project3;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.project3.data.AppDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize database
        AppDatabase.initDB(getApplicationContext());

        MediaPlayer waterSound = MediaPlayer.create(this, R.raw.watersound);
        ImageView quiz = this.findViewById(R.id.quiz);
        ImageView attempts = this.findViewById(R.id.attemptPageImageView);

        quiz.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                waterSound.start();
                Intent intent = new Intent(MainActivity.this, QuizTypeActivity.class);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}