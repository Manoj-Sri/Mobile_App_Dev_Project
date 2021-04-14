package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class unit_speed extends AppCompatActivity {

    String[] units = {"Meters per Second","Kilometers per Hour","Miles per Hour","Foot per Second"};
    EditText e1;
    int opt1;
    int opt2;
    Double ans;
    TextView t1;

    public Double convert(int a,int b,Double v1)
    {
        if(a==b)
            return v1;
        if(a==0)
        {
            if(b==1)
                return v1*3.6;
            if(b==2)
                return v1*2.237;
            if(b==3)
                return v1*3.281;
        }
        if(a==1)
        {
            if(b==0)
                return v1*0.2778;
            if(b==2)
                return v1*0.6213;
            if(b==3)
                return v1*0.911;
        }
        if(a==2)
        {
            if(b==0)
                return v1*0.447;
            if(b==1)
                return v1*1.61;
            if(b==3)
                return v1*1.467;
        }
        if(a==3)
        {
            if(b==0)
                return v1*0.3048;
            if(b==1)
                return v1*1.097;
            if(b==2)
                return v1*0.6818;
        }
        return v1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_speed);
        getSupportActionBar().setTitle("Speed Conversion");
        Spinner s1 = (Spinner)findViewById(R.id.unit1);
        Spinner s2 = (Spinner)findViewById(R.id.unit2);
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item,units);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(ad);
        s2.setAdapter(ad);
        s2.setSelection(1);
        e1 = (EditText)findViewById(R.id.val1);
        t1 = (TextView)findViewById(R.id.val2);
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(e1.getText().toString().matches(""))
                {
                    t1.setText("Value : ");
                }
                else
                {
                    opt1 = s1.getSelectedItemPosition();
                    opt2 = s2.getSelectedItemPosition();
                    Double v = Double.parseDouble(e1.getText().toString());
                    ans = convert(opt1,opt2,v);
                    t1.setText("Value : "+ans.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(e1.getText().toString().matches(""))
                {
                    t1.setText("Value : ");
                }
                else
                {
                    opt1 = s1.getSelectedItemPosition();
                    opt2 = s2.getSelectedItemPosition();
                    Double v = Double.parseDouble(e1.getText().toString());
                    ans = convert(opt1,opt2,v);
                    t1.setText("Value : "+ans.toString());
                    e1.selectAll();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(e1.getText().toString().matches(""))
                {
                    t1.setText("Value : ");
                }
                else
                {
                    opt1 = s1.getSelectedItemPosition();
                    opt2 = s2.getSelectedItemPosition();
                    Double v = Double.parseDouble(e1.getText().toString());
                    ans = convert(opt1,opt2,v);
                    t1.setText("Value : "+ans.toString());
                    e1.selectAll();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}