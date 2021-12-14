package com.example.project3;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project3.data.Attempt;
import com.example.project3.data.Question;
import com.example.project3.data.QuestionResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AttemptViewActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView scoreText;

    private List<QuestionResponse> currentResponses;
    private ArrayList<Question> currentQuestions;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attempt_view);

        this.scoreText = findViewById(R.id.ScoreText);

        this.recyclerView = findViewById(R.id.recyclerViewQuestions);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // get the attempts from the bundle
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("BUNDLE");
        Attempt attempt = (Attempt) bundle.getSerializable("ATTEMPT");

        currentResponses = ( List<QuestionResponse>) bundle.getSerializable("LIST");

        // at this point we have a list of responses with the question ids
        String quizType = attempt.getQuizType();
        String json;

        if (quizType.equals("True or False")){
            json="tof.json";
        }
        else{
            if (quizType.equals("MCQ")){
                json="mcq.json";
            }
            else{
                json="fitb.json";
            }
        }

        String jsonFileString = QuizTypeActivity.getJsonFromAssets(getApplicationContext(), json);

        Gson gson = new Gson();
        Type questionTypeFile = new TypeToken<List<Question>>() { }.getType();
        List<Question> questionsJson = gson.fromJson(jsonFileString, questionTypeFile);

        // populate the list of Question objects, basically filter it from json
        currentQuestions = new ArrayList<>();

        for ( int i = 0 ; i < currentResponses.size() ; ++i )
        {
            currentQuestions.add(getEachQuestionForResponse( currentResponses.get(i) , questionsJson ));
        }

        // start the recycler view and adapter

        recyclerViewAdapter = new QuestionsRecyclerViewAdapter( currentQuestions , currentResponses , this );
        recyclerView.setAdapter(recyclerViewAdapter);

        // set score at the top of the page
        this.scoreText.setText("Score: " + attempt.getCumulativeScore());
    }

    public void QuestionSelected ( int position )
    {
        // the user clicked the tutorial button on the item that has the position i

        URL link = this.currentQuestions.get(position).getLink();

        // launch web view with the link

        WebView theWebPage = new WebView(this);
        theWebPage.getSettings().setJavaScriptEnabled(true);
        setContentView(theWebPage);
        theWebPage.loadUrl(String.valueOf(link));
    }




    static Question getEachQuestionForResponse( QuestionResponse response , List<Question> questions )
    {
        int questionId = response.questionId;

        for ( int i = 0 ; i < questions.size() ; ++i )
        {
            if ( questions.get(i).getQuestionId() == questionId )
                return questions.get(i);
        }

        return null; // should never happen!
    }
}