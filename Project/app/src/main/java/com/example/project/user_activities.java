package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class user_activities extends AppCompatActivity {

    String user_mail;
    TextView mail;

    Button vote,unit;
    DatabaseReference ref;
    Boolean already_voted=false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activities);

        Bundle b = getIntent().getExtras();
        user_mail = b.getString("user_mail","");
        mail = findViewById(R.id.mail);
        vote = findViewById(R.id.vote);
        unit = findViewById(R.id.unit_converter);
        mail.setText(user_mail);

        ref = FirebaseDatabase.getInstance().getReference();

        //Voting Button
        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.hasChild("voted")) {
                            valid_user();
                        }
                        else go_to_voting();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(user_activities.this,"Data Base Fetch failed...Connect to a network",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });


        //Unit Converter Button
        unit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i_unit = new Intent(user_activities.this,unit_main.class);
            startActivity(i_unit);
            }
        });



    }



    public void valid_user() {

        ref.child("voted").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Map<String,String> users = (Map<String,String>) task.getResult().getValue();

                for(Map.Entry<String,String> entry : users.entrySet()) {
                    String name = entry.getValue();
                    if(user_mail.equals(name)) {
                        already_voted = true;
                        break;
                    }
                }
                if(already_voted) {
                    Toast.makeText(user_activities.this,"You already voted",Toast.LENGTH_LONG).show();
                }
                else {
                    go_to_voting();
                }
            }
        });

    }




    public void go_to_voting() {

        Bundle b = new Bundle();
        b.putString("user_mail",user_mail);

        Intent i_vote = new Intent(user_activities.this,voting.class);
        i_vote.putExtras(b);
        startActivity(i_vote);
    }

}