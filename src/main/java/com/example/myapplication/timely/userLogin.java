package com.example.myapplication.timely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class userLogin extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);
        context = this;
    }

    public void navTodos(View view){

        startActivity(new Intent(context,Home.class));
    }
    public void navRegister(View view){

        startActivity(new Intent(context,Add_User.class));
    }
}