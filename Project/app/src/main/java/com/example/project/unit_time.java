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

public class unit_time extends AppCompatActivity {

    String[] units = {"Weeks","Days","Hours","Minutes","Seconds"};
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
                return v*7;
            if(b==2)
                return v*168;
            if(b==3)
                return v*168*60;
            if(b==4)
                return v*168*3600;
        }
        if(a==1)
        {
            if(b==0)
                return v/7;
            if(b==2)
                return v*24;
            if(b==3)
                return v*24*60;
            if(b==4)
                return v*24*3600;
        }
        if(a==2)
        {
            if(b==0)
                return v/168;
            if(b==1)
                return v/24;
            if(b==3)
                return v*60;
            if(b==4)
                return v*3600;
        }
        if(a==3)
        {
            if(b==0)
                return v/(168*60);
            if(b==1)
                return v/(24*60);
            if(b==2)
                return v/60;
            if(b==4)
                return v*60;
        }
        if(a==4)
        {
            if(b==0)
                return v/(168*60*60);
            if(b==1)
                return v/(24*60*60);
            if(b==2)
                return v/3600;
            if(b==3)
                return v/60;
        }
        return v;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_time);
        getSupportActionBar().setTitle("Time Conversion");
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