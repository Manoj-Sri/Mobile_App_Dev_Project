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

public class unit_length extends AppCompatActivity {


    String[] units = {"Inches","Feet","Yard","Centimeters","Meters","Kilometers","Millimeters","Miles"};
    EditText e1;
    int opt1;
    int opt2;
    Double ans;
    TextView t1;
    public Double convert(int a,int b,Double v1)
    {
        if(a==b)
        {
            return v1;
        }
        if(a == 0)
        {
            if(b==1)
                return v1*0.083;
            if(b==2)
                return v1*0.02766666;
            if(b==3)
                return v1*2.54;
            if(b==4)
                return v1*0.0254;
            if(b==5)
                return (v1*0.0254)/1000;
            if(b==6)
                return v1*2.54*10;
            if(b==7)
                return (v1*0.0254)/1610;
        }
        if(a==1)
        {
            if(b==0)
                return v1*12;
            if(b==2)
                return v1/3;
            if(b==3)
                return v1*30.48;
            if(b==4)
                return v1*0.3048;
            if(b==5)
                return (v1*0.3048)/1000;
            if(b==6)
                return v1*304.8;
            if(b==7)
                return (v1*0.3048)/1610;
        }
        if(a==2)
        {
            if(b==0)
                return v1*36;
            if(b==1)
                return v1*3;
            if(b==3)
                return v1*30.48*3;
            if(b==4)
                return v1*0.3048*3;
            if(b==5)
                return (v1*0.3048*3)/1000;
            if(b==6)
                return v1*304.8*3;
            if(b==7)
                return (v1*0.3048*3)/1610;
        }
        if(a==3)
        {
            if(b==0)
                return v1*0.3937;
            if(b==1)
                return v1*0.0328;
            if(b==2)
                return (v1*0.0328)/3;
            if(b==4)
                return v1/100;
            if(b==5)
                return v1/100000;
            if(b==6)
                return v1*10;
            if(b==7)
                return v1/161000;
        }
        if(a==4)
        {
            if(b==0)
                return v1*0.3937*100;
            if(b==1)
                return v1*3.28;
            if(b==2)
                return (v1*3.28)/3;
            if(b==3)
                return v1*100;
            if(b==5)
                return v1/1000;
            if(b==6)
                return v1*1000;
            if(b==7)
                return v1/1610;
        }
        if(a==5)
        {
            if(b==0)
                return v1*0.3937*100000;
            if(b==1)
                return v1*3280;
            if(b==2)
                return (v1*3280)/3;
            if(b==3)
                return v1*100000;
            if(b==4)
                return v1*1000;
            if(b==6)
                return v1*1000000;
            if(b==7)
                return (v1*1000)/1610;
        }
        if(a==6)
        {
            if(b==0)
                return v1*0.03937;
            if(b==1)
                return v1*0.00328;
            if(b==2)
                return (v1*0.00328)/3;
            if(b==3)
                return v1/10;
            if(b==4)
                return v1/1000;
            if(b==5)
                return v1/1000000;
            if(b==7)
                return v1/1610000;
        }
        if(a==7)
        {
            if(b==0)
                return v1*63360;
            if(b==1)
                return v1*5280;
            if(b==2)
                return (v1*5280)/3;
            if(b==3)
                return v1*161000;
            if(b==4)
                return v1*1610;
            if(b==6)
                return v1*1610000;
            if(b==5)
                return v1*1.61;
        }
        return v1;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_length);
        getSupportActionBar().setTitle("Length Conversion");
        Spinner s1 = (Spinner)findViewById(R.id.unit1);
        Spinner s2 = (Spinner)findViewById(R.id.unit2);
        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_item,units);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(ad);
        s2.setAdapter(ad);
        s2.setSelection(3);
        e1 = (EditText)findViewById(R.id.val1);
        TextView t1 = (TextView)findViewById(R.id.val2);
        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(e1.getText().toString().matches(""))
                {
                    t1.setText("");
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