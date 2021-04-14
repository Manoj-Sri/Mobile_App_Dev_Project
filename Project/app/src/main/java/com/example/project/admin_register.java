package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class admin_register extends AppCompatActivity {

    TextView tv_mail,tv_pass,tv_conf_pass;
    Button b;

    String mail,pass,conf_pass;
    DatabaseReference ref;
    user u;

    Boolean mail_exists = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_register);

        tv_mail =findViewById(R.id.admin_reg_mail);
        tv_pass = findViewById(R.id.admin_reg_pass);
        tv_conf_pass = findViewById(R.id.admin_reg_confirm_pass);
        b = findViewById(R.id.admin_reg_button);

        ref = FirebaseDatabase.getInstance().getReference().child("admins");

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mail = tv_mail.getText().toString();
                pass = tv_pass.getText().toString();
                conf_pass = tv_conf_pass.getText().toString();
//        ref = FirebaseDatabase.getInstance().getReference().child("users");


                if(pass.equals(conf_pass)) {
                    //check_admin();
                    new_check_admin();
                }
                else {
                    Toast.makeText(admin_register.this,"Passwords Not Matched",Toast.LENGTH_LONG).show();

                    tv_pass.setText("");
                    tv_conf_pass.setText("");
                }
            }
        });
    }








    public void new_check_admin() {
        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()) {
                    Toast.makeText(admin_register.this,"Data Base Fetch failed...Connect to a network",Toast.LENGTH_LONG).show();
                }

                Map<String,Object> users = (Map<String,Object>) task.getResult().getValue();
                for (Map.Entry<String, Object> entry : users.entrySet()){

                    Map singleUser = (Map) entry.getValue();
                    String user_mail = String.valueOf(singleUser.get("mail"));

                    mail_exists =  mail.equals(user_mail);
                    if(mail_exists) break;
                }

                if(mail_exists) {
                    Toast.makeText(admin_register.this,"Mail-ID exists...please login",Toast.LENGTH_LONG).show();
                    tv_mail.setText("");
                    tv_pass.setText("");
                    tv_conf_pass.setText("");
                }
                else add_admin();

            }
        });
    }




    public void add_admin() {
        u = new user();
        u.setMail(mail);
        u.setPassword(pass);

        ref.child(mail).setValue(u);
        Toast.makeText(admin_register.this,"Admin added succesfully",Toast.LENGTH_LONG).show();

    }






}