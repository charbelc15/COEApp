package com.example.project3;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.project3.data.AppDatabase;
import com.example.project3.data.Attempt;
import com.example.project3.data.Question;
import com.example.project3.data.QuestionResponse;
import com.example.project3.fragments.FBFragment;
import com.example.project3.fragments.MCQFragment;
import com.example.project3.fragments.QuizFragment;
import com.example.project3.fragments.TFFragment;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

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
        Question current = questions.get(currentQuestionIndex);

        if (current.getCorrectAnswer().equalsIgnoreCase(answer)) {
            currentAttempt.addScore(current.getScore());
        }
        responses.add(new QuestionResponse(current.getQuestionId(), answer));


        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            setQuestionFragment(currentQuestionIndex);
            updateTexts();
        } else {
            // done with quiz!
            AppDatabase.db.getQuizDao().insertAttemptWithResponses(currentAttempt, responses);
            Snackbar.make(constraintLayout, "Quiz Done!", Snackbar.LENGTH_SHORT).show();
            finish();
        }
    }

}