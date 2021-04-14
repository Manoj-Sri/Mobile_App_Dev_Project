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

public class unit_vol extends AppCompatActivity {

    String[] units = {"Gallons","Quarts","Pints","Cubic Meter","Litres","Millilitres","Cubic Foot"};
    EditText e1;
    int opt1;
    int opt2;
    Double ans;
    TextView t1;

    public Double convert(int a,int b,Double v)
    {
        if(a==b)
            return v;
        if(a==0)
        {
            if(b==1)
                return v*4;
            if(b==2)
                return v*8;
            if(b==3)
                return v*0.003785;
            if(b==4)
                return v*3.785;
            if(b==5)
                return v*3785;
            if(b==6)
                return v*0.1337;
        }
        if(a==1)
        {
            if(b==0)
                return v/4;
            if(b==2)
                return v*2;
            if(b==3)
                return (v*0.003785)/4;
            if(b==4)
                return (v*3.785)/4;
            if(b==5)
                return (v*3785)/4;
            if(b==6)
                return (v*0.1337)/4;
        }
        if(a==2)
        {
            if(b==0)
                return v/8;
            if(b==1)
                return v/2;
            if(b==3)
                return (v*0.003785)/8;
            if(b==4)
                return (v*3.785)/8;
            if(b==5)
                return (v*3785)/8;
            if(b==6)
                return (v*0.1337)/8;
        }
        if(a==3)
        {
            if(b==0)
                return v*264.172;
            if(b==1)
                return v*264.172*4;
            if(b==2)
                return v*264.172*8;
            if(b==4)
                return v*1000;
            if(b==5)
                return v*1000000;
            if(b==6)
                return v*35.315;
        }
        if(a==4)
        {
            if(b==0)
                return v*0.264172;
            if(b==1)
                return v*0.264172*4;
            if(b==2)
                return v*0.264172*8;
            if(b==3)
                return v/1000;
            if(b==5)
                return v*1000;
            if(b==6)
                return v*0.035315;
        }
        if(a==5)
        {
            if(b==0)
                return v*0.000264172;
            if(b==1)
                return v*0.000264172*4;
            if(b==2)
                return v*0.000264172*8;
            if(b==3)
                return v/1000000;
            if(b==4)
                return v/1000;
            if(b==6)
                return v*0.000035315;
        }
        if(a==6)
        {
            if(b==0)
                return v*7.48;
            if(b==1)
                return v*7.48*4;
            if(b==2)
                return v*7.48*8;
            if(b==3)
                return v*0.28317;
            if(b==4)
                return v*283.17;
            if(b==5)
                return v*283170;
        }
        return v;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_vol);
        getSupportActionBar().setTitle("Volume Conversion");
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