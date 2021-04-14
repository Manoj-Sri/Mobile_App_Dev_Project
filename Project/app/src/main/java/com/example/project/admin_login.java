package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class admin_login extends AppCompatActivity {

    TextView tv_mail,tv_pass;
    Button b_login,b_reg;

    String mail,pass;
    DatabaseReference ref;
    Boolean mail_exists = false;
    String db_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        tv_mail = findViewById(R.id.admin_mail);
        tv_pass = findViewById(R.id.admin_pass);
        b_login = findViewById(R.id.admin_login);
        b_reg = findViewById(R.id.admin_register);

        ref = FirebaseDatabase.getInstance().getReference().child("admins");


        //Admin login
        b_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mail = tv_mail.getText().toString();
                pass = tv_pass.getText().toString();

                new_db_listener();

            }
        });

        //New Admin
        b_reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(admin_login.this,admin_key.class);
                startActivity(i);

            }
        });


    }






    public void new_db_listener() {

        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()) {
                    Toast.makeText(admin_login.this,"Data Base Fetch failed...Connect to a network",Toast.LENGTH_LONG).show();
                }
                else {

                    Map<String,Object> users = (Map<String,Object>) task.getResult().getValue();
                    for (Map.Entry<String, Object> entry : users.entrySet()){


                        Map singleUser = (Map) entry.getValue();
                        String user_mail = String.valueOf(singleUser.get("mail"));

                        mail_exists = mail.equals(user_mail);
                        if(mail_exists) {
                            db_pass = String.valueOf(singleUser.get("password"));
                            break;
                            }
                        }

                    if(!mail_exists) {
                        Toast.makeText(admin_login.this,"Mail doesnt exist...Please register as new admin",Toast.LENGTH_LONG).show();
                    }
                    else login_admin();
                }

            }
        });
    }



    public void login_admin() {
        if(pass.equals(db_pass)) {
            Intent i = new Intent(admin_login.this,admin_page.class);
            startActivity(i);
        }
        else {
            Toast.makeText(admin_login.this,"Oops...wrong password", Toast.LENGTH_LONG).show();
            tv_pass.setText("");
        }
    }






}
