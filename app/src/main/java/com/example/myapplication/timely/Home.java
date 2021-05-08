package com.example.myapplication.timely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = this;
    }

    public void navToDOs(View view){

        startActivity(new Intent(context,show_todo.class));
    }
    public void navList(View view){

        startActivity(new Intent(context,Add_List.class));
    }
    public void navUser(View view){

        startActivity(new Intent(context,View_User_Details.class));
    }
}