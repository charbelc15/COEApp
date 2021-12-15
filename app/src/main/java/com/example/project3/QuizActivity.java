package com.example.project3;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.project3.data.AppDatabase;
import com.example.project3.data.Attempt;
import com.example.project3.data.Question;
import com.example.project3.data.QuestionResponse;
import com.example.project3.fragments.CancelQuizDialogFragment;
import com.example.project3.fragments.FBFragment;
import com.example.project3.fragments.MCQFragment;
import com.example.project3.fragments.QuizFragment;
import com.example.project3.fragments.TFFragment;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    // setting up things
    private TextView questionNbr;
    private TextView userScore;
    private View constraintLayout;

    private Attempt currentAttempt;
    private String questionsType;

    // to keep current question track
    Class<? extends QuizFragment> fragmentType;
    private int currentQuestionIndex = 0;
    private ArrayList<Question> questions;
    private ArrayList<QuestionResponse> responses;
    private boolean isQuizDone;

    private MediaPlayer correctPlayer;
    private MediaPlayer incorrectPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);

        questionNbr = findViewById(R.id.textQNbr);
        userScore = findViewById(R.id.textScore);
        constraintLayout = findViewById(R.id.quizLayout);

        Intent questionsIntent = getIntent();
        Bundle args = questionsIntent.getBundleExtra("BUNDLE");
        questions = (ArrayList<Question>) args.getSerializable("ARRAYLIST");
        questionsType = args.getString("TYPE");

        currentAttempt = new Attempt();
        currentAttempt.setQuizType(questionsType);
        responses = new ArrayList<>();
        isQuizDone = false;

        correctPlayer = MediaPlayer.create(this, R.raw.positive_sound);
        incorrectPlayer = MediaPlayer.create(this, R.raw.negative_sound);

        switch (questionsType) {
            case "MCQ":
                fragmentType = MCQFragment.class;
                break;
            case "True or False":
                fragmentType = TFFragment.class;
                break;
            case "Fill in the blanks":
                fragmentType = FBFragment.class;
                break;
            default:
                fragmentType = MCQFragment.class; // should never happen
        }

       if (savedInstanceState == null) {
           setQuestionFragment(0);
       }

       updateTexts();

       ActionBar actionBar = getSupportActionBar();
       if (actionBar != null) {
           actionBar.setDisplayHomeAsUpEnabled(true);
           actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
       }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("attempt", currentAttempt);
        outState.putInt("index", currentQuestionIndex);
        outState.putSerializable("questions", questions);
        outState.putSerializable("responses", responses);
        outState.putBoolean("isDone", isQuizDone);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentAttempt = (Attempt) savedInstanceState.getSerializable("attempt");
        currentQuestionIndex = savedInstanceState.getInt("index");
        questions = (ArrayList<Question>) savedInstanceState.getSerializable("questions");
        responses = (ArrayList<QuestionResponse>) savedInstanceState.getSerializable("responses");
        isQuizDone = savedInstanceState.getBoolean("isDone");

        updateTexts();
    }

    private void setQuestionFragment(int questionIndex) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("question", questions.get(questionIndex));

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainerView, fragmentType, bundle)
                .commit();
    }

    private void updateTexts() {
        userScore.setText("Score: " + currentAttempt.getCumulativeScore());
        questionNbr.setText("Question " + (currentQuestionIndex+1) + "/" + questions.size());
    }

    public void submitQuestionAnswer(String answer) {
        if (!isQuizDone) {
            Question current = questions.get(currentQuestionIndex);

            if (current.getCorrectAnswer().equalsIgnoreCase(answer)) {
                currentAttempt.addScore(current.getScore());
                correctPlayer.start();
            } else {
                incorrectPlayer.start();
            }
            responses.add(new QuestionResponse(current.getQuestionId(), answer));


            currentQuestionIndex++;
            if (currentQuestionIndex < questions.size()) {
                setQuestionFragment(currentQuestionIndex);
                updateTexts();
            } else {
                // done with quiz!
                isQuizDone = true;
                AppDatabase.db.getQuizDao().insertAttemptWithResponses(currentAttempt, responses);
                Snackbar.make(constraintLayout, "Quiz Complete!", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Done", v -> finish())
                        .show();
            }
            if (this.getCurrentFocus() != null) {
                InputMethodManager inputMethodManager = ContextCompat.getSystemService(this, InputMethodManager.class);
                inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        correctPlayer.release();
        incorrectPlayer.release();
        correctPlayer = null;
        incorrectPlayer = null;
    }

    @Override
    public void onBackPressed() {
        CancelQuizDialogFragment dialog = new CancelQuizDialogFragment();
        dialog.show(getSupportFragmentManager(), "quiz");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void cancelQuiz() {
        this.finish();
    }

}