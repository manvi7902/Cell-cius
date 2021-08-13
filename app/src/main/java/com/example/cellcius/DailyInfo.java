package com.example.cellcius;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.util.Calendar;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.Random;



public class DailyInfo extends AppCompatActivity {

    Button btngraph;
    TextView tvday, tvtemp, tvrecord, tvtemperature, tips, tipstittle, condition, intro, profile;
    String tip="";
    String tiptitle="";
    String day="";
    DatabaseHelper db;
    String status="";
    final int num= 37;
    //String blood= db.getblood();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_info);

        db = new DatabaseHelper(this);
        tvday = (TextView) findViewById(R.id.tvday);
        tvtemp = (TextView) findViewById(R.id.textView8);
        tvrecord = (TextView) findViewById(R.id.tvrecord);
        tvtemperature = (TextView) findViewById(R.id.textView9);
        tips = (TextView) findViewById(R.id.tvtips);
        tipstittle = (TextView) findViewById(R.id.tvtiptitle);
        condition = (TextView) findViewById(R.id.tvcondition);
        intro= (TextView) findViewById(R.id.textView4);
        profile= (TextView) findViewById(R.id.profiletv);

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        tvday.setText(currentDate);

        intro.setText("Hello," + db.getName());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            OffsetDateTime offset = OffsetDateTime.now();
            day = String.valueOf(offset.getDayOfWeek());


        switch (day) {
            case "MONDAY":
                tiptitle = "YOUR TODAY'S TIP:\nNeurobics for your mind.";
                tip = "Neurobics for tasks which activate the brain's own biochemical pathways and to bring new pathways online that can help to strengthen or preserve brain circuits.\n" +
                        "\nBrush your teeth with your ‘other’ hand, take a new route to work or choose your clothes based on sense of touch rather than sight.";
                break;

            case "TUESDAY":
                tiptitle = "YOUR TODAY'S TIP:\nLoad up on vitamin C";
                tip = "We need at least 90 mg of vitamin C per day and the best way to get this is by eating at least five servings of fresh fruit and vegetables every day." +
                        "\nSo hit the oranges and guavas!";
                break;
            case "WEDNESDAY":
                tiptitle = "YOUR TODAY'S TIP:\nBurn the boredom.";
                tip = "Rev up your metabolism by alternating your speed and intensity during aerobic workouts.\n " +
                        "Not only should you alter" +
                        "nate your routine to prevent burnout or boredom, but to give your body a jolt.";
                break;
            case "THURSDAY":
                tiptitle = "YOUR TODAY'S TIP:\nAvoid artificial trans fats";
                tip = "Artificial trans fats are harmful, man-made fats that are strongly linked to inflammation and heart disease \n " +
                        "While trans fats have been largely banned in the United States and elsewhere, the U.S. ban hasn’t gone fully into effect — and some foods still contain them.";
                break;
            case "FRIDAY":
                tiptitle = "YOUR TODAY'S TIP:\nUse extra virgin olive oil";
                tip = "It’s loaded with heart-healthy monounsaturated fats and powerful antioxidants that can fight inflammation\n" +
                        "Extra virgin olive oil benefits heart health, as people who consume it have a much lower risk of dying from heart attacks and strokes ";
                break;
            case "SATURDAY":
                tiptitle = "YOUR TODAY'S TIP:\nBone up daily.";
                tip = "Get your daily calcium by popping a tab, chugging milk or eating yoghurt. It’ll keep your bones strong.\n" +
                        "Remember that your bone density declines after the age of 30. You need at least 200 milligrams daily, which you should combine with magnesium, or it simply won’t be absorbed.";
                break;
            case "SUNDAY":
                tiptitle = "YOUR TODAY'S TIP:\nDon’t drink sugar calories";
                tip = "Sugary drinks are among the most fattening items you can put into your body.\n" +
                        "Sugary drinks are strongly associated with obesity, type 2 diabetes, heart disease, and many other health problems ";
                break;

        }

    }

        final int min = 36;
        final int max = 38;
        int rand = new Random().nextInt((max - min) + 1) + min;
        Double random= Double.valueOf(rand);
        random= random-0.2;
        final double temperature= random;
        final String temp = Double.toString(temperature) + "°C";

        if (temperature>=36.5 && temperature<=37.5){
            status="CONDITION: Normal";
        }
        else {
            if(temperature>=37.6){
                status="CONDITION: Higher than usual temperature chance of fever";
            }
            else{
                status="CONDITION: Lower than usual temperature chance of hypothermia";
            }
        }
        Handler handler = new Handler();
        handler.post(new Runnable() {
                         @Override
                         public void run() {
                             runOnUiThread(new Runnable() {
                                 @Override
                                 public void run() {
                                        tvrecord.setText("");
                                        tvtemp.setText("Today temperature: ");
                                        tvtemperature.setText(temp);
                                        tipstittle.setText(tiptitle);
                                        condition.setText(status);
                                        tips.setText(tip);

                                     try {
                                         Thread.sleep(5000);
                                     } catch (InterruptedException e) {
                                         e.printStackTrace();
                                     }
                                 }
                             });
                         }
                     });

        btngraph = (Button) findViewById(R.id.btnGraph);
        btngraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent monthreview = new Intent(DailyInfo.this, graph.class);
                monthreview.putExtra("TEMPERATURE", num);
                startActivity(monthreview);
            }
        });

       profile.setOnClickListener(new View.OnClickListener() {
          @Override
        public void onClick(View v) {
          Intent profile_page= new Intent(DailyInfo.this, Profile.class);
          profile_page.putExtra("TEMP", temperature);
          profile_page.putExtra("CONDITION",status );
          profile_page.putExtra("SAP", db.getSapid());
          profile_page.putExtra("BLOOD", db.getblood());
          profile_page.putExtra("ALLERGY", db.getallergy());
          profile_page.putExtra("DISEASES", db.getcondition());
        startActivity(profile_page);
  }
});
    }
}
