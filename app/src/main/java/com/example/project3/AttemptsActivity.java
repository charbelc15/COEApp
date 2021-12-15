package com.example.project3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AttemptsActivity extends AppCompatActivity implements AttemptViewHolder.onAttemptClickedListener
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

    // current List of attempts
    private  ArrayList<Attempt> attempts;
    private List<AttemptWithResponses> attemptWithResponses;

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

        attemptWithResponses =
        AppDatabase.db.getQuizDao().attemptsWithResponses();

        attempts = new ArrayList<>();

        for ( int i = 0 ; i < attemptWithResponses.size() ; ++i )
        {
            if(attemptWithResponses.get(i).attempt.getQuizType().equals("MCQ")){
                totalTrialsMCQ++;
                TpercentageMCQ = (float) (((attemptWithResponses.get(i).attempt.getCumulativeScore()/10))/totalTrialsMCQ)*100;
                FpercentageMCQ = 100 - TpercentageMCQ;
                //Log.i("lifecyclefilter",""+TpercentageMCQ);
            }
            if(attemptWithResponses.get(i).attempt.getQuizType().equals("True or False")){
                totalTrialsTF++;
                TpercentageTF = (float) (((attemptWithResponses.get(i).attempt.getCumulativeScore()/10))/totalTrialsTF)*100;
                FpercentageTF = 100 - TpercentageTF;
            }
            if(attemptWithResponses.get(i).attempt.getQuizType().equals("Fill in the blanks")){
                totalTrialsFIB++;
                TpercentageFIB = (float) (((attemptWithResponses.get(i).attempt.getCumulativeScore()/10))/totalTrialsFIB)*100;
                FpercentageFIB = 100 - TpercentageFIB;
            }
            //Log.i("lifecyclefilter",""+values.get(i).attempt.getQuizType());
            attempts.add(attemptWithResponses.get(i).attempt);
        }

        recyclerViewAdapter = new AttemptRecyclerViewAdapter( attempts , this );
        recyclerView.setAdapter(recyclerViewAdapter);

        // init pi charts

        chartTF = findViewById(R.id.pieChartTrueFalse);
        chartFTB = findViewById(R.id.pieChartFTB);
        chartMCQ = findViewById(R.id.pieChartMCQ);

        setupPieChart( chartTF,TpercentageTF,FpercentageTF , "True or False");
        setupPieChart( chartFTB,TpercentageFIB,FpercentageFIB , "Fill the Blanks");
        setupPieChart( chartMCQ,TpercentageMCQ,FpercentageMCQ , "Multiple Choice");
    }

    @ColorInt
    private int getColorResCompat(Context ctx, @AttrRes int id) {
        TypedValue resolvedAttr = new TypedValue();
        ctx.getTheme().resolveAttribute(id, resolvedAttr, true);

        int colorRes = (resolvedAttr.resourceId != 0) ? resolvedAttr.resourceId : resolvedAttr.data;
        return ContextCompat.getColor(ctx, colorRes);
    }


    private void setupPieChart( PieChart chart,float percentageT, float percentageF , String title )
    {
        Log.d("PERCENTAGE", "" + percentageT);
        if (percentageT == 0.0 && percentageF == 0.0) {
            chart.setVisibility(View.GONE);
            return;
        }
        int textColor = getColorResCompat(this, android.R.attr.textColorPrimary);
        int backgroundColor = getColorResCompat(this, android.R.attr.colorBackground);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(backgroundColor);
        chart.setUsePercentValues(true);
        chart.setDrawEntryLabels(false);
        chart.setEntryLabelTextSize(20);
        chart.setEntryLabelColor(textColor);
        chart.getDescription().setEnabled(false);

        chart.setCenterText(title);
        chart.setCenterTextSize(15);
        chart.setCenterTextColor(textColor);

        Legend l = chart.getLegend();
        l.setDrawInside(true);
        l.setEnabled(true);
        l.setTextSize(15);
        l.setTextColor(textColor);

        List<PieEntry> value = new ArrayList<>();

        value.add(new PieEntry((float) percentageT , "Correct"));
        value.add(new PieEntry( (float) percentageF, "Wrong"));

        PieDataSet pieDataSet = new PieDataSet(value , "");

        pieDataSet.setColors(Color.GREEN , Color.RED);
        PieData pieData = new PieData(pieDataSet);

        pieData.setDrawValues(true);
        pieData.setValueFormatter(new PercentFormatter(chart));
        pieData.setValueTextSize(30);
        pieData.setValueTextColor(textColor);

        chart.setData(pieData);
        chart.animateXY(600,600);
    }

    // happens when the user presses on an entry on the recycler view attempts list
    @Override
    public void onAttemptClicked(int position)
    {
        Bundle extra = new Bundle();
        extra.putSerializable("ATTEMPT" , (Serializable) attempts.get(position) );
        extra.putSerializable("LIST" , ( Serializable) attemptWithResponses.get(position).responses );
        Intent intent = new Intent ( this , AttemptViewActivity.class );
        intent.putExtra("BUNDLE"  , extra );
        startActivity(intent);
    }
}