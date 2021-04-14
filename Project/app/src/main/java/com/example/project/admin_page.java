package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.github.mikephil.charting.data.PieEntry;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class admin_page extends AppCompatActivity {


    Button add_user,del_user,add_cont,reset_vote,get_results;

    DatabaseReference ref;
    ArrayList<Map> conts;

    ArrayList<Contestant> trial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        add_user = findViewById(R.id.button_add_user);
        del_user = findViewById(R.id.button_delete_user);
        add_cont = findViewById(R.id.button_add_cont);
        reset_vote = findViewById(R.id.button_reset_voting);
        get_results = findViewById(R.id.get_results_button);

        ref = FirebaseDatabase.getInstance().getReference();
        get_db();

    }

    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_add_user:
                Intent i_add_user = new Intent(admin_page.this,add_user.class);
                startActivity(i_add_user);
                break;
            case R.id.button_delete_user:
                Intent i_del_user = new Intent(admin_page.this,delete_user.class);
                startActivity(i_del_user);
                break;
            case R.id.button_add_cont:
                Intent i_add_contestant = new Intent(admin_page.this,add_contestant.class);
                startActivity(i_add_contestant);
                break;
            case R.id.button_reset_voting:
                reset_voting();
                break;
            case R.id.get_results_button:

                Intent i = new Intent(admin_page.this,results.class);
                Parcelable p = Parcels.wrap(trial);
                i.putExtra("parse_key",p);
                startActivity(i);
//
                break;
        }
    }



    public void get_db() {
        ArrayList<Map> contestants = new ArrayList<>();

        trial = new ArrayList<>();
        ref.child("contestants").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(!task.isSuccessful()) {
                    Toast.makeText(admin_page.this,"Data Base Fetch failed...Connect to a network",Toast.LENGTH_LONG).show();
                }

                Map<String,Object> conts =(Map<String, Object>) task.getResult().getValue();

                for(Map.Entry<String,Object> entry : conts.entrySet()) {
                    Map cont = (Map) entry.getValue();
                    contestants.add(cont);
                }
                String toast_info = "";
//
                for(Map cont : contestants) {
                    String name = (String) cont.get("name");
                    toast_info += name;
                    Contestant c = new Contestant();
                    c.setName(name);
                    c.setScore((String)cont.get("score"));



                    trial.add(c);
                }

                String trial_info = "";

                for(Contestant c : trial) {
                    trial_info += c.toString();
                }

                Toast.makeText(admin_page.this,"Voting results are ready", Toast.LENGTH_LONG).show();

            }
        });
    }





















    //reset voting method

    public void reset_voting() {
        ref.child("contestants").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                set_score_zero((Map<String,Object>) snapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(admin_page.this,"Data Base Fetch failed...Connect to a network",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void set_score_zero(Map<String,Object> contestants) {
        conts = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : contestants.entrySet()){
            Map singleUser = (Map) entry.getValue();
            conts.add(singleUser);
        }


        for(Map cont:conts) {
            String name = (String) cont.get("name");
            ref.child("contestants").child(name).child("score").setValue("0");
        }

        ref.child("voted").removeValue();
        Toast.makeText(admin_page.this,"Voting Resetted",Toast.LENGTH_LONG).show();
    }
}