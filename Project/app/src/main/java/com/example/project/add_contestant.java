package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class add_contestant extends AppCompatActivity {

    TextView tv_name;
    Button b;

    String name;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contestant);

        tv_name = findViewById(R.id.add_contestant_name);
        b = findViewById(R.id.add_contestant_submit);
        ref = FirebaseDatabase.getInstance().getReference();
    }

    public void onClick(View v) {

        name = tv_name.getText().toString();
        add_name();
    }

    public void add_name(){
        Contestant c = new Contestant();

        String score="0";
        c.setName(name);
        c.setScore(score);

        ref.child("contestants").child(name).setValue(c);
        Toast.makeText(add_contestant.this,"Contestant added succesfully",Toast.LENGTH_LONG).show();
    }


}