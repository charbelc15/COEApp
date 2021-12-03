package com.example.project3;


import android.annotation.SuppressLint;
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

public class QuizActivity extends AppCompatActivity
        implements View.OnClickListener {
    // setting up things
    private Button falseButton;
    private Button trueButton;
    private ImageView prevButton;
    private ImageView Image;
    private TextView questionTextView;
    private int correct = 0;
    // to keep current question track
    private int currentQuestionIndex = 0;

    private Question[] questionBank = new Question[] {
            // array of objects of class Question
            // providing questions from string
            // resource and the correct ans
            //new Question(R.string.a, true),
            //new Question(R.string.b, true),
            //new Question(R.string.c, false),
            //new Question(R.string.d, true),
            //new Question(R.string.e, false),
            //new Question(R.string.f, false),

    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        // setting up the buttons
        // associated with id
        /*falseButton = findViewById(R.id.false_button);
        trueButton = findViewById(R.id.true_button);
        prevButton = findViewById(R.id.prev_button);
        // register our buttons to listen to
        // click events
        questionTextView
                = findViewById(R.id.answer_text_view);
        Image = findViewById(R.id.myimage);
        falseButton.setOnClickListener(this);
        trueButton.setOnClickListener(this);
        prevButton.setOnClickListener(this);*/
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v)
    {
        // checking which button is
        // clicked by user
        // in this case user choose false
        /*switch (v.getId()) {
            case R.id.false_button:
                checkAnswer(false);
                // go to next question
                // limiting question bank range
                if (currentQuestionIndex < 7) {
                    currentQuestionIndex
                            = currentQuestionIndex + 1;
                    // we are safe now!
                    // last question reached
                    // making buttons
                    // invisible
                    if (currentQuestionIndex == 6) {
                        questionTextView.setText(getString(
                                R.string.correct, correct));
                        prevButton.setVisibility(
                                View.INVISIBLE);
                        trueButton.setVisibility(
                                View.INVISIBLE);
                        falseButton.setVisibility(
                                View.INVISIBLE);
                        if (correct > 3)

                            questionTextView.setText(
                                    "CORRECTNESS IS " + correct
                                            + " "
                                            + "OUT OF 6");
                            // showing correctness
                        else
                            Image.setImageResource(
                                    R.drawable.resu);

                        // if correctness < 3 we will be showing sad emoji
                    }
                    else {
                        updateQuestion();
                    }
                }
                break;

            case R.id.true_button:
                checkAnswer(true);
                // go to next question
                // limiting question bank range
                if (currentQuestionIndex < 7) {
                    currentQuestionIndex = currentQuestionIndex + 1;
                    // we are safe now!
                    // last question reached
                    // making buttons
                    // invisible
                    if (currentQuestionIndex == 6) {
                        questionTextView.setText(getString(R.string.correct, correct));
                        prevButton.setVisibility(View.INVISIBLE);
                        trueButton.setVisibility(View.INVISIBLE);
                        falseButton.setVisibility(View.INVISIBLE);

                        if (correct > 3) {
                            questionTextView.setText("CORRECTNESS IS " + correct + " " + "OUT OF 6");
                            //Image.setImageResource(R.drawable.happy);
                            // showing correctness
                        }
                        else
                            Image.setImageResource(R.drawable.resu);

                        // if correctness < 3 we will be showing sad emoji
                    }
                    else {
                        updateQuestion();
                    }
                }
                break;

            case R.id.prev_button:
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex = (currentQuestionIndex - 1) % questionBank.length;
                    updateQuestion();
                }
        } */
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void updateQuestion()
    {/*
        Log.d("Current", "onClick: " + currentQuestionIndex);

        questionTextView.setText(questionBank[currentQuestionIndex].getAnswerResId());
        // setting the textview with new question
        switch (currentQuestionIndex) {
            case 1:
                // setting up image for each
                // question
                Image.setImageResource(R.drawable.f1);
                break;
            case 2:
                Image.setImageResource(R.drawable.f1);
                break;
            case 3:
                Image.setImageResource(R.drawable.f1);
                break;
            case 4:
                Image.setImageResource(R.drawable.f1);
                break;
            case 5:
                Image.setImageResource(R.drawable.f1);
                break;
            case 6:
                Image.setImageResource(R.drawable.f1);
                break;
            case 7:
                Image.setImageResource(R.drawable.f1);
                break;
        }
    }
    private void checkAnswer(boolean userChooseCorrect)
    {
        boolean answerIsTrue = questionBank[currentQuestionIndex].isAnswerTrue();
        // getting correct ans of current question
        int toastMessageId;
        // if ans matches with the
        // button clicked
        if (userChooseCorrect == answerIsTrue) {
            toastMessageId = R.string.correct_answer;
            correct++;
        }
        else {
            // showing toast
            // message correct
            toastMessageId = R.string.wrong_answer;
        }

        Toast.makeText(QuizActivity.this, toastMessageId, Toast.LENGTH_SHORT).show(); */
    }
}