package com.example.project3;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizTypeActivity extends AppCompatActivity
        {

    public Question[] generatedQuestions;

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;
    String[] quizTypes = {"MCQ","True or False","Fill in the blanks"};
    int nbOfQuest=10;
    String quizType;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_type_layout);

        listView = findViewById(R.id.quizTypes);

        list = new ArrayList<>();

        for (int i = 0;i<quizTypes.length;i++){

            list.add(quizTypes[i]);

        }
        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        adapter = new ArrayAdapter(QuizTypeActivity.this,android.R.layout.simple_list_item_single_choice,list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String selected_type = quizTypes[i];
                Toast.makeText(QuizTypeActivity.this, "selected: "+  quizTypes[i], Toast.LENGTH_SHORT).show();

                if (selected_type == "True or False"){
                    quizType="tof.json";
                }
                else{
                    if (selected_type == "MCQ"){
                        quizType="mcq.json";
                    }
                    else{
                        quizType="fitb.json";
                    }
                }

                String jsonFileString = getJsonFromAssets(getApplicationContext(), quizType);
                Log.i("data", jsonFileString);

                Gson gson = new Gson();
                Type questionTypeFile = new TypeToken<List<Question>>() { }.getType();

                List<Question> questionsJson = gson.fromJson(jsonFileString, questionTypeFile);
                for (int j = 0; j < questionsJson.size(); j++) {
                    Log.i("data", "> Item " + j + "\n" + questionsJson.get(i).getQuestion());
                }
                try {
                    generatedQuestions = generate_questions(questionsJson);
                    Toast.makeText(QuizTypeActivity.this, "Generated questions", Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


    }

            static String getJsonFromAssets(Context context, String fileName) {
                String jsonString;
                try {
                    InputStream is = context.getAssets().open(fileName);

                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();

                    jsonString = new String(buffer, "UTF-8");
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }

                return jsonString;
            }



    @RequiresApi(api = Build.VERSION_CODES.O)
    private Question[] generate_questions(List<Question> questions) throws JSONException {

        Question[] generatedQuestionPerType = new Question[nbOfQuest];
        Gson gson = new Gson();
        Random rand = new Random();

        for(int i=0; i<nbOfQuest; i++) {
            int int_random = rand.nextInt(questions.size());
            Object curr_quest = questions.get(int_random);
            Question ques = (Question) curr_quest;
            generatedQuestionPerType[i] = ques;
        }
        //String question_print = generatedQuestionPerType[0].getQuestion();
        //Toast.makeText(QuizTypeActivity.this, question_print, Toast.LENGTH_SHORT).show();
        //Toast.makeText(QuizTypeActivity.this, "Selected: "+  selected_type, Toast.LENGTH_SHORT).show();
        return generatedQuestionPerType;


    }






}
