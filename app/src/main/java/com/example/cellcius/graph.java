package com.example.cellcius;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class graph extends AppCompatActivity {
    int numb;
    BarChart barchart;
    BarData bardata;
    BarDataSet bardataset;
    ArrayList days;
    TextView homee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        barchart= findViewById(R.id.barChart);
        days= new ArrayList<>();
        homee= (TextView) findViewById(R.id.gohome);

        homee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homes = new Intent(graph.this, DailyInfo.class);
                startActivity(homes);
            }
        });

        SetData();
        bardataset= new BarDataSet(days, "Daily temperature check");
        bardataset.setColor(getResources().getColor(R.color.colorPrimaryDark));
        bardata= new BarData(bardataset);
        barchart.setData(bardata);
        bardataset.setValueTextColor(Color.BLACK);
        bardataset.setValueTextSize(16f);
        barchart.setFitBars(true);
        barchart.animateY(2000);
        bardataset.setDrawValues(false);
        barchart.getAxisLeft().setDrawGridLines(false);
        barchart.getXAxis().setDrawGridLines(false);
        XAxis xAxis= barchart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        Description des = barchart.getDescription();
        des.setEnabled(false);

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("Mon");
        xAxisLabel.add("Tue");
        xAxisLabel.add("Wed");
        xAxisLabel.add("Thu");
        xAxisLabel.add("Fri");
        xAxisLabel.add("Sat");
        xAxisLabel.add("Sun");
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xAxisLabel.get((int) value);
            }
        });


    }



    private void SetData() {
        days.add(new BarEntry(0,36.07f));
        days.add(new BarEntry(1,37f));
        days.add(new BarEntry(2,38.01f));
        days.add(new BarEntry(3,36.81f));
        days.add(new BarEntry(4,36.40f));
        days.add(new BarEntry(5,37.48f));
        days.add(new BarEntry(6,37.50f));
    }}

