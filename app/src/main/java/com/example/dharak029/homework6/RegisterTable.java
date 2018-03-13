/*
HomeWork06
RegisterTable.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

/**
 * Created by dharak029 on 10/23/2017.
 */

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RegisterTable {

    static final String TABLENAME = "Registeration";
    static final String COLUMNID = "id";
    static final String COLUMNFNAME = "fname";
    static final String COLUMNLNAME = "lname";
    static final String COLUMNUNAME = "uname";
    static final String COLUMNPASSWORD = "password";
    static final String COLUMNIMAGE = "image";

    static public void onCreate(SQLiteDatabase db){

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME+"(");
        sb.append(COLUMNID + " INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(COLUMNFNAME +" TEXT NOT NULL, ");
        sb.append(COLUMNLNAME +" TEXT NOT NULL, ");
        sb.append(COLUMNUNAME +" TEXT NOT NULL, ");
        sb.append(COLUMNPASSWORD +" TEXT NOT NULL, ");
        sb.append(COLUMNIMAGE +" BLOB NOT NULL); ");
        try{
            db.execSQL(sb.toString());
        }
        catch (SQLException e){

        }


    }

    static public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);
        RegisterTable.onCreate(db);
    }
}
