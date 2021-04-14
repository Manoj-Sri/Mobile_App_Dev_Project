package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class add_user extends AppCompatActivity {

    TextView tv_roll;
    Button b;

    String roll,user_mail;

    DatabaseReference ref;
    user u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        tv_roll = findViewById(R.id.user_roll_no);
        b = findViewById(R.id.add_user);

        ref = FirebaseDatabase.getInstance().getReference().child("users");


    }

    public void onClick(View v){
        roll = tv_roll.getText().toString();
        add_user();

    }

    public void add_user(){

        user_mail = roll+"@nitt";
        u = new user();
        u.setMail(user_mail);
        u.setPassword(roll);

        ref.child(user_mail).setValue(u);
        Toast.makeText(add_user.this,"User added succesfully",Toast.LENGTH_LONG).show();
    }
}