package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {

    TextView tv_mail,tv_pass;
    Button b;
    String mail,pass;

    DatabaseReference ref;
    Boolean mail_exists;
    String db_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_mail = findViewById(R.id.user_mail);
        tv_pass = findViewById(R.id.user_pass);
        b = findViewById(R.id.user_login);

        ref = FirebaseDatabase.getInstance().getReference().child("users");

    }

    public void onClick(View v) {

        mail = tv_mail.getText().toString();
        pass = tv_pass.getText().toString();

        ref.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if(!task.isSuccessful()) {
                    Toast.makeText(MainActivity.this,"Data Base Fetch failed...Connect to a network",Toast.LENGTH_LONG).show();
                }


                Map<String,Object> users = (Map<String,Object>) task.getResult().getValue();

                for(Map.Entry<String,Object> entry : users.entrySet()) {
                    Map single_user = (Map) entry.getValue();

                    String user_mail = String.valueOf(single_user.get("mail"));

                    mail_exists = mail.equals(user_mail);
                    if(mail_exists) {
                        db_pass = String.valueOf(single_user.get("password"));
                        break;
                    }

                }

                if(mail_exists) {
                    valid_pass();
                }
                else {
                    Toast.makeText(MainActivity.this,"Mail doesnt exist...contact admin",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void valid_pass() {
        if(pass.equals(db_pass)) {

            Bundle b = new Bundle();
            b.putString("user_mail",mail);

            Intent i = new Intent(MainActivity.this,user_activities.class);
            i.putExtras(b);
            startActivity(i);
        }
        else {
            Toast.makeText(MainActivity.this,"Uhuhu...Wrong Password",Toast.LENGTH_LONG).show();
            tv_pass.setText("");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        getMenuInflater().inflate(R.menu.my_menu,m);
        return super.onCreateOptionsMenu(m);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(MainActivity.this,"Admin Log-In",Toast.LENGTH_LONG).show();

        Intent i = new Intent(MainActivity.this,admin_login.class);
        startActivity(i);

        return super.onOptionsItemSelected(item);
    }

}