package com.example.cellcius;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    DatabaseHelper db;
    TextView sapid, blood, allergy, condition, temperature, health_con, graph, home, average, min, max;
    Double defaultValue= 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        condition= (TextView) findViewById(R.id.textView13);
        temperature= (TextView) findViewById(R.id.mark);
        sapid= (TextView) findViewById(R.id.sapidtv);
        blood= (TextView) findViewById(R.id.bldtv);
        allergy=(TextView) findViewById(R.id.allergytv);
        health_con= (TextView) findViewById(R.id.conditiontv);
        graph= (TextView) findViewById(R.id.graphtv);
        home= (TextView) findViewById(R.id.hometv);
        average = (Button) findViewById(R.id.averagetv);
        min = (Button) findViewById(R.id.mintv);
        max = (Button) findViewById(R.id.maxtv);

        Intent intent= getIntent();
        double temp= intent.getDoubleExtra("TEMP", defaultValue);
        final String tempe = Double.toString(temp)+ "°C";
        final String status= intent.getStringExtra("CONDITION");
        final String sap= intent.getStringExtra("SAP");
        final String bld= intent.getStringExtra("BLOOD");
        final String allergies= intent.getStringExtra("ALLERGY");
        final String dis= intent.getStringExtra("DISEASES");

        temperature.setText(tempe);
        condition.setText(status);

        sapid.setText("SAPID: "+ sap);
        blood.setText("BLOOD GROUP: " + bld);
        allergy.setText("ALLERGIES: " + allergies);
        health_con.setText("HEALTH CONDITIONS: "+ dis);
        average.setText("AVERAGE \n 37.07°C");
        max.setText("MAX \n 38.01°C");
        min.setText("MIN \n 36.47°C");

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent graph = new Intent(Profile.this, graph.class);
                startActivity(graph);

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Profile.this, DailyInfo.class);
                startActivity(home);
            }
        });

    }
}
