package com.example.myapplication.timely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddToDo extends AppCompatActivity {

    private EditText date,desc;
    private Button add;
    private DbHandler dbHandler;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);

        date = findViewById(R.id.editTextDate);
        desc = findViewById(R.id.editTextDescription);
        add = findViewById(R.id.buttonAdd);
        context = this;
        dbHandler = new DbHandler(context);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userDate = date.getText().toString();
                String userDesc = desc.getText().toString();
                long started = System.currentTimeMillis();

                ToDo toDo = new ToDo(userDate,userDesc,started,0);
                dbHandler.addToDo(toDo);

                startActivity(new Intent(context,show_todo.class));
                Toast.makeText(AddToDo.this, "New ToDo Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

}