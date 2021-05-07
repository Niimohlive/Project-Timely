package com.example.myapplication.timely;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class View_User_Details extends AppCompatActivity {
    EditText name, contact, username,password;
    Button update,delete,view;
    DbHandler db;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__user__details);

        name = findViewById(R.id.showName);
        contact = findViewById(R.id.showPhone);
        username = findViewById(R.id.showUserName);
        password = findViewById(R.id.showPassword);


        update = findViewById(R.id.btn2Update);
        delete = findViewById(R.id.btn2Delete);
        view = findViewById(R.id.btn2View);

        db = new DbHandler(this);
        context = this;

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String usernameTXT = username.getText().toString();
                String passwordTXT = password.getText().toString();

                Boolean checkupdatedata = db.updateuserdata(nameTXT,contactTXT,usernameTXT,passwordTXT);
                if(checkupdatedata == true){
                    Toast.makeText(View_User_Details.this, " Entry Updataed", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(View_User_Details.this, " Invalid Name Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameTXT = name.getText().toString();


                Boolean checkdeletedata = db.deletedata(nameTXT);
                if(checkdeletedata == true){
                    Toast.makeText(View_User_Details.this, " Entry Deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(View_User_Details.this, " Invalid Name Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getdata();
                if(res.getCount()==0){
                    Toast.makeText(View_User_Details.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name : "+res.getString(0)+"\n" );
                    buffer.append("Contact : "+res.getString(1)+"\n" );
                    buffer.append("UserName : "+res.getString(2)+"\n" );
                    buffer.append("Password : "+res.getString(3)+"\n\n" );

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(View_User_Details.this);
                builder.setCancelable(true);
                builder.setTitle("UserDetails");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
    public void navViewHome(View view){
        context = this;
        startActivity(new Intent(context,Home.class));
    }
}