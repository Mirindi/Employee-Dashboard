package com.example.pankhurirastogi.gail_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PANKHURI RASTOGI on 6/23/2015.This class is database helper class used for performing various CURD
 * operations.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 26;

    // Database Name
    private static final String DATABASE_NAME = "Gail_Employee";

    // Contacts table name
    private static final String TABLE_Employee = "trial_employee";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String Department = "department";
    private  static  final String salary="salary";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_Employee + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT UNIQUE,"
                + Department + " TEXT," + salary+ " TEXT)";
        Log.d("qurt!!!",CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Employee);

        // Create tables again
        onCreate(db);
    }


    public void addContact(employe emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, emp.name);
        values.put(Department, emp.Department);
        Log.d("salry++", emp.salary);
        values.put(salary, emp.salary);
        try {


            db.insertOrThrow(TABLE_Employee, null, values);
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        db.close(); // Closing database connection
    }

    public List<employe> getAllContacts() {
        List<employe> contactList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_Employee;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                employe contact = new employe();
               // contact.(Integer.parseInt(cursor.getString(0)));
                contact.name=cursor.getString(1);
                contact.Department=cursor.getString(2);
                contact.salary=cursor.getString(3);
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

}