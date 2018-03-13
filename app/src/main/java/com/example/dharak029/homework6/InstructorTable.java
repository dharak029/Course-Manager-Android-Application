/*
HomeWork06
InstructorTable.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by dharak029 on 11/5/2017.
 */

public class InstructorTable {
    static final String TABLENAME = "Intsructor";
    static final String COLUMNID = "id";
    static final String COLUMNFNAME = "fname";
    static final String COLUMNLNAME = "lname";
    static final String COLUMNEMAIL = "email";
    static final String COLUMNWEBSITE = "website";
    static final String COLUMNIMAGE = "image";


    static public void onCreate(SQLiteDatabase db){

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME+"(");
        sb.append(COLUMNID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(COLUMNFNAME +" TEXT NOT NULL, ");
        sb.append(COLUMNLNAME +" TEXT NOT NULL, ");
        sb.append(COLUMNEMAIL +" TEXT NOT NULL, ");
        sb.append(COLUMNWEBSITE +" TEXT NOT NULL, ");
        sb.append(COLUMNIMAGE +" BLOB NOT NULL); ");
        try{
            db.execSQL(sb.toString());
        }
        catch (SQLException e){

        }


    }

    static public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);
        InstructorTable.onCreate(db);
    }
}
