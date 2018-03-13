/*
HomeWork06
CourseTable.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Viranchi on 11/5/2017.
 */

public class CourseTable {

    static final String TABLENAME = "Course";
    static final String COLUMNTITLE = "title";
    static final String COLUMNINSTRUCTOR = "instructor";
    static final String COLUMNTIME = "time";
    static final String COLUMNSEMESTER = "semester";
    static final String COLUMNCREDIT = "credit";
    static final String COLUMNIMAGE = "image";
    static final String COLUMNUNAME = "uname";


    static public void onCreate(SQLiteDatabase db){

        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME+"(");
        sb.append(COLUMNTITLE +" TEXT NOT NULL PRIMARY KEY , ");
        sb.append(COLUMNINSTRUCTOR +" TEXT NOT NULL, ");
        sb.append(COLUMNTIME +" TEXT NOT NULL, ");
        sb.append(COLUMNSEMESTER +" TEXT NOT NULL, ");
        sb.append(COLUMNCREDIT +" INTEGER NOT NULL, ");
        sb.append(COLUMNIMAGE +" BLOB NOT NULL, ");
        sb.append(COLUMNUNAME + " TEXT NOT NULL);");
        try{
            db.execSQL(sb.toString());
        }
        catch (SQLException e){
            e.printStackTrace();
        }


    }

    static public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+ TABLENAME);
        CourseTable.onCreate(db);
    }

}
