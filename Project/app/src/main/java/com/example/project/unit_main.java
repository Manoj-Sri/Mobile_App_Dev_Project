package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;

public class unit_main extends AppCompatActivity {

    Button temp,speed,weight,time,vol;
    Button len;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_main);
        getSupportActionBar().setTitle("Unit Converter");
        len = (Button)findViewById(R.id.len);
        temp = (Button)findViewById(R.id.temp);
        speed = (Button)findViewById(R.id.speed);
        weight = (Button)findViewById(R.id.weight);
        time = (Button)findViewById(R.id.time);
        vol = (Button)findViewById(R.id.vol);

        len.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(unit_main.this,unit_length.class);
                startActivity(i);
            }
        });
        temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(unit_main.this,unit_temp.class);
                startActivity(i);
            }
        });
        speed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(unit_main.this,unit_speed.class);
                startActivity(i);
            }
        });
        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(unit_main.this,unit_weight.class);
                startActivity(i);
            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(unit_main.this,unit_time.class);
                startActivity(i);
            }
        });
        vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(unit_main.this,unit_vol.class);
                startActivity(i);
            }
        });
    }
}