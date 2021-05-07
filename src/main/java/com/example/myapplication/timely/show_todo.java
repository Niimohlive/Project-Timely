package com.example.myapplication.timely;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class show_todo extends AppCompatActivity {

    private Button add;
    private ListView listView;
    private TextView count;
    Context context;
    private DbHandler dbHandler;
    private List<ToDo> toDos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_todo);

        context = this;
        dbHandler = new DbHandler(context);
        add = findViewById(R.id.add);
        listView = findViewById(R.id.todolist);
        count = findViewById(R.id.todocount);
        toDos = new ArrayList<>();

        toDos = dbHandler.getAllToDos();

        //displaying data to main page from singleTodd
        ToDoAdapter adapter = new ToDoAdapter(context,R.layout.single_todo,toDos);

        //displaying all todos in the listView on showTodo class
        listView.setAdapter(adapter);

        //get todo counts from the table
        int countTodo = dbHandler.countToDo();
        count.setText("You have "+countTodo+" todos");

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context,AddToDo.class));
            }
        });

        //showing single todo as alert dialog box
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {          //position variable gives the index of the column

                final ToDo todo =toDos.get(position) ;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(todo.getDate());
                builder.setMessage(todo.getDescription());


                builder.setPositiveButton("Finished", new DialogInterface.OnClickListener() {           //finished button
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        todo.setFinished(System.currentTimeMillis());
                        dbHandler.updateSingleToDo(todo);
                        startActivity(new Intent(context,show_todo.class));
                    }
                });
                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {            //delete button
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHandler.deleteToDo(todo.getId());
                        startActivity(new Intent(context,show_todo.class));
                    }
                });
                builder.setNeutralButton("Update", new DialogInterface.OnClickListener() {              //update button
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context,EditToDo.class);
                        intent.putExtra("id",String.valueOf(todo.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }
        });
    }
    public void navHome(View view){
        context = this;
        startActivity(new Intent(context,Home.class));
    }
}