package com.example.project3;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QuizTypeActivity extends AppCompatActivity
        implements View.OnClickListener {

    public  List<Question> generatedQuestions;

    ListView listView;
    ArrayList<String> list;
    ArrayAdapter adapter;
    String[] quizTypes = {"MCQ","True or False","Fill in the blanks"};

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
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String selected_type = quizTypes[i];

                generatedQuestions = generate_questions(selected_type);

                Toast.makeText(QuizTypeActivity.this, "Selected -> " + quizTypes[i], Toast.LENGTH_SHORT).show();
            }
        });


    }

    private List<Question> generate_questions(String selected_type) {
        List<Question> generatedQuestionPerType = null;
        String jsonFileName="";
        //GSON gson = new GSON();
        if (selected_type == "True or False"){
            jsonFileName = "fillInTheBlanks.json";
        }
        else{
            if (selected_type == "MCQ"){
                jsonFileName = "mcq.json";
            }
            else{
                jsonFileName = "trueOrFalse.json";
                
            }
        }
        return generatedQuestionPerType;
        
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v)
    {

    }


}
