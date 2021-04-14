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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_key extends AppCompatActivity {

    TextView tv_key;
    Button b;

    DatabaseReference ref;

    String input_key;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_key);

        tv_key = findViewById(R.id.admin_key);
        b = findViewById(R.id.admin_key_login);

        ref = FirebaseDatabase.getInstance().getReference();

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                input_key = tv_key.getText().toString();

                ref.child("admin_key").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(!task.isSuccessful()) {
                            Toast.makeText(admin_key.this,"Data Base Fetch failed...Connect to a network",Toast.LENGTH_LONG).show();
                        }


                        key = String.valueOf(task.getResult().getValue());
                        vadilate_key();
                    }

                });


            }
        });
    }

    public void vadilate_key() {
        if(input_key.equals(key)) {
            Toast.makeText(admin_key.this,"Hurray!!! Lets add u as Admin",Toast.LENGTH_LONG).show();

            Intent i = new Intent(admin_key.this,admin_register.class);
            startActivity(i);
        }
        else {
            Toast.makeText(admin_key.this,"Ooops!!!Please contact Developer for key",Toast.LENGTH_LONG).show();
        }

    }
}