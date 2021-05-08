package com.example.myapplication.timely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add_User extends AppCompatActivity {
    EditText name, contact, username,password;
    Button insert;
    DbHandler db;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__user);

        name = findViewById(R.id.acountName);
        contact = findViewById(R.id.acountDate);
        username = findViewById(R.id.acountUserName);
        password = findViewById(R.id.acountPassword);

        insert = findViewById(R.id.btnCreate);
        db = new DbHandler(this);
        context = this;

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String usernameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();

                Boolean checkinsertdata = db.insertuserdata(nameTXT,contactTXT,usernameTXT,passwordTXT);
                if(checkinsertdata == true){
                    startActivity(new Intent(context,Home.class));
                    Toast.makeText(Add_User.this, "Successfully Registed", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Add_User.this, "Registration Failed or Invalid Name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}