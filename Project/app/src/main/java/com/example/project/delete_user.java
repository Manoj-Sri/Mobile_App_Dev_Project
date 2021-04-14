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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

public class delete_user extends AppCompatActivity {

    TextView tv_del_user_mail;
    Button b;

    String mail;
    DatabaseReference ref;
    Boolean mail_exists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        tv_del_user_mail = findViewById(R.id.del_user_mail);
        b = findViewById(R.id.del_user_button);

        ref = FirebaseDatabase.getInstance().getReference();
    }

    public void onClick(View v) {
        mail = tv_del_user_mail.getText().toString();

        ref.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(!task.isSuccessful()) {
                    Toast.makeText(delete_user.this,"Data Base Fetch failed...Connect to a network",Toast.LENGTH_LONG).show();
                }
                else {
                    Map<String,Object> users = (Map<String,Object>) task.getResult().getValue();
                    for (Map.Entry<String, Object> entry : users.entrySet()){
                        Map singleUser = (Map) entry.getValue();
                        String user_mail = String.valueOf(singleUser.get("mail"));

                        mail_exists = mail.equals(user_mail);
                        if(mail_exists) {
                            break;
                        }
                    }

                    if(!mail_exists) {
                        Toast.makeText(delete_user.this,"Mail doesnt exitst",Toast.LENGTH_LONG).show();
                    }
                    else del_uer();
                }
            }
        });


    }


    public void del_uer() {
        ref.child("users").child(mail).removeValue();
        Toast.makeText(delete_user.this,"User removed succesfully",Toast.LENGTH_LONG).show();

        tv_del_user_mail.setText("");
    }
}