package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class voting extends AppCompatActivity {

    //Todo - show pie chart


    RadioGroup rgp;
    Button b;
    RadioButton selected;
    String user_mail;

    DatabaseReference ref;
    ArrayList<Map> conts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voting);

        Bundle bundle = getIntent().getExtras();
        user_mail = bundle.getString("user_mail","");

        rgp = findViewById(R.id.radio_group);
        b = findViewById(R.id.vote_submit_button);
        ref = FirebaseDatabase.getInstance().getReference();

        new_show();

    }




    public void new_show() {
        ref.child("contestants").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                collect_contestants((Map<String,Object>) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(voting.this,"Data Base Fetch failed...Connect to a network",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void collect_contestants(Map<String,Object> users){
        conts = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){

            //Get user map
            Map singleUser = (Map) entry.getValue();
            conts.add(singleUser);
        }

        for(Map cont : conts) {
            String name = (String) cont.get("name");
            RadioButton rdbtn = new RadioButton(this);
            rdbtn.setId(View.generateViewId());
            rdbtn.setText(name);
            rgp.addView(rdbtn);
        }

    }




    public void onClick(View v) {
        int selected_id = rgp.getCheckedRadioButtonId();
        selected = rgp.findViewById(selected_id);
        String selected_name = selected.getText().toString();
        String selected_cont_score;



        if(selected_id != -1) {
            for(Map cont:conts) {
                if(selected_name == cont.get("name")) {
                    selected_cont_score = (String) cont.get("score");
                    Integer int_score = Integer.parseInt(selected_cont_score);
                    int_score++;
                    ref.child("contestants").child(selected_name).child("score").setValue(String.valueOf(int_score));
                    ref.child("voted").push().setValue(user_mail);
                    Toast.makeText(voting.this, "Hurray...Your Vote has been recorded", Toast.LENGTH_LONG).show();

                    finish();
                }
            }
        }
        else
            Toast.makeText(voting.this, "Select your favourite team to vote", Toast.LENGTH_LONG).show();
    }



}