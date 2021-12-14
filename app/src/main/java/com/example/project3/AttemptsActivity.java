package com.example.project3;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project3.data.AppDatabase;
import com.example.project3.data.Attempt;
import com.example.project3.data.AttemptWithResponses;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class AttemptsActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private PieChart chartTF;
    private PieChart chartFTB;
    private PieChart chartMCQ;

    private float totalTrialsTF =0;
    private float totalTrialsMCQ =0;
    private float totalTrialsFIB =0;

    private float TpercentageTF=0;
    private float FpercentageTF=0;

    private float TpercentageMCQ=0;
    private float FpercentageMCQ=0;

    private float TpercentageFIB=0;
    private float FpercentageFIB=0;

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
            if(values.get(i).attempt.getQuizType().equals("MCQ")){
                totalTrialsMCQ++;
                TpercentageMCQ = (float) (((values.get(i).attempt.getCumulativeScore()/10))/totalTrialsMCQ)*100;
                FpercentageMCQ = 100 - TpercentageMCQ;
                //Log.i("lifecyclefilter",""+TpercentageMCQ);
            }
            if(values.get(i).attempt.getQuizType().equals("True or False")){
                totalTrialsTF++;
                TpercentageTF = (float) (((values.get(i).attempt.getCumulativeScore()/10))/totalTrialsTF)*100;
                FpercentageTF = 100 - TpercentageTF;
            }
            if(values.get(i).attempt.getQuizType().equals("Fill in the blanks")){
                totalTrialsFIB++;
                TpercentageFIB = (float) (((values.get(i).attempt.getCumulativeScore()/10))/totalTrialsFIB)*100;
                FpercentageFIB = 100 - TpercentageFIB;
            }
            //Log.i("lifecyclefilter",""+values.get(i).attempt.getQuizType());
            attempts.add(values.get(i).attempt);
        }

        recyclerViewAdapter = new AttemptRecyclerViewAdapter( attempts );
        recyclerView.setAdapter(recyclerViewAdapter);

        // init pi charts

        chartTF = findViewById(R.id.pieChartTrueFalse);
        chartFTB = findViewById(R.id.pieChartFTB);
        chartMCQ = findViewById(R.id.pieChartMCQ);

        setupPieChart( chartTF,TpercentageTF,FpercentageTF , "True or False");
        setupPieChart( chartFTB,TpercentageFIB,FpercentageFIB , "Fill the Blanks");
        setupPieChart( chartMCQ,TpercentageMCQ,FpercentageMCQ , "Multiple Choice");
    }


    private void setupPieChart( PieChart chart,float percentageT, float percentageF , String title )
    {
        chart.setDrawHoleEnabled(true);
        chart.setUsePercentValues(true);
        chart.setDrawEntryLabels(false);
        chart.setEntryLabelTextSize(20);
        chart.setEntryLabelColor(Color.BLACK);
        chart.getDescription().setEnabled(false);

        chart.setCenterText(title);
        chart.setCenterTextSize(15);

        Legend l = chart.getLegend();
        l.setDrawInside(true);
        l.setEnabled(true);
        l.setTextSize(15);

        List<PieEntry> value = new ArrayList<>();

        value.add(new PieEntry((float) percentageT , "Correct"));
        value.add(new PieEntry( (float) percentageF, "Wrong"));

        PieDataSet pieDataSet = new PieDataSet(value , "");

        pieDataSet.setColors(Color.GREEN , Color.RED);
        PieData pieData = new PieData(pieDataSet);

        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter(chart));
        pieData.setValueTextSize(30);
        pieData.setValueTextColor(Color.BLACK);

        chart.setData(pieData);
        chart.animateXY(600,600);
    }
}