package com.example.project3;

import android.content.Context;
import android.content.Intent;
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


import com.example.project3.data.Question;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuizTypeActivity extends AppCompatActivity
        {

    public ArrayList<Question> generatedQuestions;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;
    String[] quizTypes = {"MCQ","True or False","Fill in the blanks"};
    int nbOfQuest=10;
    String quizType;

    private void switchActivities(ArrayList<Question> generatedQuestions, String selectedType) {
        Intent switchActivityIntent = new Intent(this, QuizActivity.class);
        Bundle args = new Bundle();
        args.putSerializable("ARRAYLIST",(Serializable)generatedQuestions);
        args.putString("TYPE", selectedType);
        switchActivityIntent.putExtra("BUNDLE",args);

        startActivity(switchActivityIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_type_layout);

        listView = findViewById(R.id.quizTypes);

        list = new ArrayList<>();

        for (int i = 0;i<quizTypes.length;i++){

            list.add(quizTypes[i]);

        }
//        listView.setChoiceMode(listView.CHOICE_MODE_SINGLE);

        adapter = new ArrayAdapter(QuizTypeActivity.this,android.R.layout.simple_list_item_1,list);
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
                    switchActivities(generatedQuestions, selected_type);
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
    private ArrayList<Question> generate_questions(List<Question> questions) throws JSONException {

        ArrayList<Question> generatedQuestionPerType = new ArrayList<Question>();
        Gson gson = new Gson();
        Random rand = new Random();
        ArrayList<Integer> questions_indices = new ArrayList<>();
        for (int j=0; j<questions.size(); j++)
            questions_indices.add(j);

        for(int i=0; i<nbOfQuest; i++) {

            int int_random = (int) (Math.random()*questions_indices.size());
            int random_index = questions_indices.get(int_random);
            questions_indices.remove(int_random);

            Object curr_quest = questions.get(random_index);
            Question ques = (Question) curr_quest;
            generatedQuestionPerType.add(ques);
            //Toast.makeText(QuizTypeActivity.this, "Index Q: "+  i + "-> "+random_index , Toast.LENGTH_SHORT).show();


        }
        return generatedQuestionPerType;


    }






}
