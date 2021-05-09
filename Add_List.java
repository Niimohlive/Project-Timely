
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

public class Add_List extends AppCompatActivity {
    EditText itemName, qty, unitPrice;
    Button insert,update,delete,view;
    DbHandler db;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__list);

        itemName = findViewById(R.id.itemName);
        qty = findViewById(R.id.qty);
        unitPrice = findViewById(R.id.unitPrice);

        insert = findViewById(R.id.btnListInsert);
        update = findViewById(R.id.btnListUpdate);
        delete = findViewById(R.id.btnListDelete);
        view = findViewById(R.id.btnListView);

        db = new DbHandler(this);
        context = this;

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemNameTXT = itemName.getText().toString();
                String qtyTXT = qty.getText().toString();
                String unitPriceTXT = unitPrice.getText().toString();
                String totalTXT = "emty";

                Boolean checkinsertdata = db.insertitemdata(itemNameTXT,qtyTXT,unitPriceTXT,totalTXT);
                if(checkinsertdata == true){

                    Toast.makeText(Add_List.this, "New Item Inserted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Add_List.this, "New Item not inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemNameTXT = itemName.getText().toString();
                String qtyTXT = qty.getText().toString();
                String unitPriceTXT = unitPrice.getText().toString();


                Boolean checkupdatedata = db.updateitemdata(itemNameTXT,qtyTXT,unitPriceTXT);
                if(checkupdatedata == true){
                    Toast.makeText(Add_List.this, " Item Updataed", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Add_List.this, " Invalid ItemName Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String itemNameTXT = itemName.getText().toString();


                Boolean checkdeletedata = db.deletelistdata(itemNameTXT);
                if(checkdeletedata == true){
                    Toast.makeText(Add_List.this, " Item Deleted", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Add_List.this, " Invalid ItemName Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getlistdata();
                if(res.getCount()==0){
                    Toast.makeText(Add_List.this, "No Entry Exist", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Item Name : "+res.getString(0)+"\n" );
                    buffer.append("Qty : "+res.getString(1)+"\n" );
                    buffer.append("Price Rs : "+res.getString(2)+"\n" );
                    buffer.append("Total Cost Rs : "+res.getString(3)+"\n\n" );

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(Add_List.this);
                builder.setCancelable(true);
                builder.setTitle("Item List");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }
    public void navListHome(View view){
        context = this;
        startActivity(new Intent(context,Home.class));
    }
}