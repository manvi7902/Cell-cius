package com.example.cellcius;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText etname, etsap, etemail, etphone, bloodgrp, conditions, allergy, lifestyle;
    Button btn_register;
    TextView tvlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        etname = (EditText) findViewById(R.id.etName);
        etsap = (EditText) findViewById(R.id.etSap);
        etemail = (EditText) findViewById(R.id.etEmail);
        etphone = (EditText) findViewById(R.id.etPhone);

        bloodgrp= (EditText) findViewById(R.id.etblood);
        allergy= (EditText) findViewById(R.id.etallergy);
        conditions= (EditText) findViewById(R.id.etcondition);
        lifestyle= (EditText) findViewById(R.id.etlifestyle);
        tvlogin = (TextView) findViewById(R.id.tvlogin);
        btn_register = (Button) findViewById(R.id.btn_register);

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginintent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginintent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etname.getText().toString().trim();
                String sap = etsap.getText().toString().trim();
                String email = etemail.getText().toString().trim();
                String phone = etphone.getText().toString().trim();

                String group = bloodgrp.getText().toString().trim();
                String allergies = allergy.getText().toString().trim();
                String health_condition = conditions.getText().toString().trim();
                String life_style = lifestyle.getText().toString().trim();


                db.newUser(name, sap, email, phone,group,allergies,health_condition,life_style);
                Toast.makeText(RegisterActivity.this, "REGISTRATION DONE!", Toast.LENGTH_SHORT).show();

                Intent dailydata = new Intent(RegisterActivity.this, DailyInfo.class);
                startActivity(dailydata);
            }
        });
    }
}
