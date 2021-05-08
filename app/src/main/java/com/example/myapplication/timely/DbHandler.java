package com.example.myapplication.timely;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DbHandler extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DB_NAME = "todo1.db";
    private static final String TABLE_NAME = "todo";
    private  static final String TABLE_2 = "userDetails";
    private static final String TABLE_3 = "itemDetails";

    // Column names
    private static final String ID = "id";
    private static final String DATE = "date";
    private static final String DESCRIPTION = "description";
    private static final String STARTED = "started";
    private static final String FINISHED = "finished";
    private static final String NAME = "name";
    private static final String PHONE = "phone";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String ITEMNAME = "itemname";
    private static final String QTY = "qty";
    private static final String UNITPRICE = "unitprice";
    private static final String TOTALPRICE = "totalprice";

    public DbHandler(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_CREATE_QUERY = "CREATE TABLE "+TABLE_NAME+" " +          //Create Todo Table
                "("
                +ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +DATE + " TEXT,"
                +DESCRIPTION + " TEXT,"
                +STARTED+ " TEXT,"
                +FINISHED+ " TEXT" +
                ");";

        String TABLE_CREATE_QUERY2 = "CREATE TABLE "+TABLE_2+" " +          //Create User Table
                "("
                +NAME+" TEXT PRIMARY KEY,"
                +PHONE + " TEXT,"
                +USERNAME + " TEXT,"
                +PASSWORD+ " TEXT" +
                ");";

        String TABLE_CREATE_QUERY3 = "CREATE TABLE "+TABLE_3+" " +          //Create Item Table
                "("
                +ITEMNAME+" TEXT PRIMARY KEY ,"
                +QTY + " TEXT,"
                +UNITPRICE + " TEXT,"
                +TOTALPRICE+ " TEXT" +
                ");";

        db.execSQL(TABLE_CREATE_QUERY3);
        db.execSQL(TABLE_CREATE_QUERY2);
        db.execSQL(TABLE_CREATE_QUERY);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        // Drop older table if existed
        db.execSQL(DROP_TABLE_QUERY);
        // Create tables again
        onCreate(db);

    }


    //----------------ADD Item-------------------
    public Boolean insertitemdata(String itemname, String qty, String unitprice, String totalprice){                  //Add Item Function
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int qty1 = Integer.parseInt(qty);
        int unitprice1 = Integer.parseInt(unitprice);
        int totalprice1;
        totalprice1 = qty1 * unitprice1;

        contentValues.put("itemname",itemname);
        contentValues.put("qty",qty);
        contentValues.put("unitprice",unitprice);
        contentValues.put("totalprice",totalprice1);

        long result = db.insert(TABLE_3,null,contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }

    }

    //---------------------UPDATE Item---------------------------------------

    public Boolean updateitemdata(String itemname, String qty, String unitprice){               //Update Item Function
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("qty",qty);
        contentValues.put("unitprice",unitprice);
        Cursor cursor = db.rawQuery("Select * from itemDetails  where itemname = ?",new String[] {itemname});
        if (cursor.getCount()>0) {


            long result = db.update(TABLE_3, contentValues, "itemname=?", new String[]{itemname});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }

    }

    //---------------------DELETE Item---------------------------------------
    public Boolean deletelistdata(String itemname){                                                                 //Delete Item Function
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from itemDetails where itemname = ?",new String[] {itemname});
        if (cursor.getCount()>0) {


            long result = db.delete(TABLE_3, "itemname=?", new String[]{itemname});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }

    }

    //---------------------Read All List Deatails---------------------------------------
    public Cursor getlistdata(){                                                                                 //Read All list data Function
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from itemDetails",null);

        return cursor;

    }

    //----------------ADD User-------------------
    public Boolean insertuserdata(String name, String phone, String username, String password){                  //Add User Function
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name",name);
        contentValues.put("phone",phone);
        contentValues.put("username",username);
        contentValues.put("password",password);

        long result = db.insert(TABLE_2,null,contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }

    }

    //---------------------UPDATE USER---------------------------------------

    public Boolean updateuserdata(String name, String phone, String username, String password){               //Update User Function
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put("phone",phone);
        contentValues.put("username",username);
        Cursor cursor = db.rawQuery("Select * from userDetails  where name = ?",new String[] {name});
        if (cursor.getCount()>0) {


            long result = db.update(TABLE_2, contentValues, "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }

    }

    //---------------------DELETE USER---------------------------------------
    public Boolean deletedata(String name){                                                                 //Delete User Function
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from userDetails where name = ?",new String[] {name});
        if (cursor.getCount()>0) {


            long result = db.delete(TABLE_2, "name=?", new String[]{name});

            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }
        else {
            return false;
        }

    }

    //---------------------Read All USER Deatails---------------------------------------
    public Cursor getdata(){                                                                                 //Read All user data Function
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from userDetails",null);

        return cursor;

    }


    //------------------------Add ToDo---------------
    public void addToDo(ToDo toDo){                                                                         //Add ToDo function
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DATE,toDo.getDate());
        contentValues.put(DESCRIPTION, toDo.getDescription());
        contentValues.put(STARTED,toDo.getStarted());
        contentValues.put(FINISHED,toDo.getFinished());

        //save to table
        sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        // close database
        sqLiteDatabase.close();


    }




    // Count
    public int countToDo(){                                                                          //Function to count No of ToDos
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    // Returns all todos into a list
    public List<ToDo> getAllToDos(){                                                                 //Function to Display All todos

        List<ToDo> toDos = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;

        Cursor cursor = db.rawQuery(query,null);

        if(cursor.moveToFirst()){
            do {
                // Create new ToDo object
                ToDo toDo = new ToDo();

                toDo.setId(cursor.getInt(0));
                toDo.setDate(cursor.getString(1));
                toDo.setDescription(cursor.getString(2));
                toDo.setStarted(cursor.getLong(3));
                toDo.setFinished(cursor.getLong(4));

                //toDos [obj,objs,asas,asa]
                toDos.add(toDo);
            }while (cursor.moveToNext());
        }
        return toDos;
    }

    // Delete item
    public void deleteToDo(int id){                                                                     //Function to Delete Todo
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME,"id =?",new String[]{String.valueOf(id)});
        db.close();
    }

    // Get a single todo to edit activity
    public ToDo getSingleToDo(int id){                                                                //Function to show single Todo
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.query(TABLE_NAME,new String[]{ID,DATE,DESCRIPTION,STARTED, FINISHED},
                ID + "= ?",new String[]{String.valueOf(id)}
                ,null,null,null);

        ToDo toDo;
        if(cursor != null){
            cursor.moveToFirst();
            toDo = new ToDo(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getLong(3),
                    cursor.getLong(4)
            );
            return toDo;
        }
        return null;
    }

    // Update a single todo
    public int updateSingleToDo(ToDo toDo){                                                           //Funtion to Update ToDo
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(DATE,toDo.getDate());
        contentValues.put(DESCRIPTION, toDo.getDescription());
        contentValues.put(STARTED,toDo.getStarted());
        contentValues.put(FINISHED,toDo.getFinished());

        int status = db.update(TABLE_NAME,contentValues,ID +" =?",
                new String[]{String.valueOf(toDo.getId())});

        db.close();
        return status;
    }
}
