/*
HomeWork06
DataBaseHelper.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by dharak029 on 10/23/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "myfilter.db";
    static final int DB_VERSION = 1;
    public DataBaseHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        RegisterTable.onCreate(db);
        InstructorTable.onCreate(db);
        CourseTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        RegisterTable.onUpgrade(db,oldVersion,newVersion);
        InstructorTable.onUpgrade(db,oldVersion,newVersion);
        CourseTable.onUpgrade(db,oldVersion,newVersion);
    }
}
