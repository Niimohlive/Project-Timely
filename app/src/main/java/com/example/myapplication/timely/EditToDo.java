package com.example.myapplication.timely;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditToDo extends AppCompatActivity {

    private EditText date,desc;
    private Button edit;
    private DbHandler dbHandler;
    private Context context;
    private Long updateDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_to_do);

        context = this;
        dbHandler = new DbHandler(context);

        date = findViewById(R.id.editToDoTextDate);
        desc = findViewById(R.id.editToDoTextDescription);
        edit = findViewById(R.id.buttonEdit);

        final String id = getIntent().getStringExtra("id");
        System.out.println(id);

        ToDo todo = dbHandler.getSingleToDo(Integer.parseInt(id));

        date.setText(todo.getDate());
        desc.setText(todo.getDescription());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = date.getText().toString();
                String decText = desc.getText().toString();
                updateDate = System.currentTimeMillis();

                ToDo toDo = new ToDo(Integer.parseInt(id),titleText,decText,updateDate,0);
                int state = dbHandler.updateSingleToDo(toDo);
                System.out.println(state);
                startActivity(new Intent(context,show_todo.class));
                Toast.makeText(EditToDo.this, "Todo Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

}