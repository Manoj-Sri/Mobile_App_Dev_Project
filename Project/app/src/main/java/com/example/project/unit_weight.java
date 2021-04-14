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

public class unit_weight extends AppCompatActivity {

    String[] units = {"Tonnes","Kilograms","Grams","Milligrams","Stones","Pounds","Ounces"};
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
                return v*1000;
            if(b==2)
                return v*1000000;
            if(b==3)
                return v*1000000000;
            if(b==4)
                return v*154.473;
            if(b==5)
                return v*2204.62;
            if(b==6)
                return v*35274;
        }
        if(a==1)
        {
            if(b==0)
                return v/1000;
            if(b==2)
                return v*1000;
            if(b==3)
                return v*1000000;
            if(b==4)
                return v*0.154473;
            if(b==5)
                return v*2.20462;
            if(b==6)
                return v*35.274;
        }
        if(a==2)
        {
            if(b==0)
                return v/1000000;
            if(b==1)
                return v/1000;
            if(b==3)
                return v*1000;
            if(b==4)
                return v*0.000154473;
            if(b==5)
                return v*0.00220462;
            if(b==6)
                return v*0.035274;
        }
        if(a==3)
        {
            if(b==0)
                return v/1000000000;
            if(b==1)
                return v/1000000;
            if(b==2)
                return v/1000;
            if(b==4)
                return v*0.000000154473;
            if(b==5)
                return v*0.00000220462;
            if(b==6)
                return v*0.000035274;
        }
        if(a==4)
        {
            if(b==0)
                return v*0.0063503;
            if(b==1)
                return v*6.3503;
            if(b==2)
                return v*6350.3;
            if(b==3)
                return v*6350300;
            if(b==5)
                return v*14;
            if(b==6)
                return v*224;
        }
        if(a==5)
        {
            if(b==0)
                return v*0.0004536;
            if(b==1)
                return v*0.4536;
            if(b==2)
                return v*453.6;
            if(b==3)
                return v*453600;
            if(b==4)
                return v*0.0714286;
            if(b==6)
                return v*16;
        }
        if(a==6)
        {
            if(b==0)
                return (v*2.835)/100000;
            if(b==1)
                return v*0.02835;
            if(b==2)
                return v*28.35;
            if(b==3)
                return v*28350;
            if(b==4)
                return v*0.0044643;
            if(b==5)
                return v*0.0625;
        }
        return v;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_weight);
        getSupportActionBar().setTitle("Weight Conversion");
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