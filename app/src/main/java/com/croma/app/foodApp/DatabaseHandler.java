package com.croma.app.foodApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by suppi on 26/07/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION 	= 	1;
    // Database Name
    private static final String DATABASE_NAME 	= 	"FoodyDatabase";
    //Database Tables
    //Register User Data table
    private static final String TABLE_REGISTERUSER_DATA = "RegisterUserData";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // query to create student Data Table
       // String CREATE_REGISTERUSER_TABLE = "CREATE TABLE " + TABLE_REGISTERUSER_DATA + "("
          //      + UserName + " TEXT," + EmailAddress + " TEXT" +", "+Password+" TEXT, "+ConfirmPassword+" TEXT, "+PhoneNumber+" TEXT)";

        //db.execSQL(CREATE_REGISTERUSER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
