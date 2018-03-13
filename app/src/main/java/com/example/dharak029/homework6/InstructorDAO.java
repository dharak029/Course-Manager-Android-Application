/*
HomeWork06
InstructorDAO.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viranchi on 11/5/2017.
 */

public class InstructorDAO {

    private SQLiteDatabase db;

    public InstructorDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Instructor instructor){
        ContentValues values = new ContentValues();
        values.put(InstructorTable.COLUMNFNAME,instructor.getFname());
        values.put(InstructorTable.COLUMNLNAME,instructor.getLname());
        values.put(InstructorTable.COLUMNEMAIL,instructor.getEmail());
        values.put(InstructorTable.COLUMNWEBSITE,instructor.getWebsite());
        values.put(InstructorTable.COLUMNIMAGE,instructor.getImage());
        return db.insert(InstructorTable.TABLENAME,null,values);
    }

    public boolean update(Instructor instructor){
        ContentValues values = new ContentValues();
        values.put(InstructorTable.COLUMNFNAME,instructor.getFname());
        values.put(InstructorTable.COLUMNLNAME,instructor.getLname());
        values.put(InstructorTable.COLUMNEMAIL,instructor.getEmail());
        values.put(InstructorTable.COLUMNWEBSITE,instructor.getWebsite());
        values.put(InstructorTable.COLUMNIMAGE,instructor.getImage());
        return db.update(InstructorTable.TABLENAME,values, InstructorTable.COLUMNEMAIL +"=?",new String[]{instructor.getEmail()})>0;
    }

    public boolean delete(Instructor instructor){
        boolean result =  db.delete(InstructorTable.TABLENAME, InstructorTable.COLUMNEMAIL +"=?",new String[]{instructor.getEmail()})>0;
        if (result)
        {
            result = db.delete(CourseTable.TABLENAME, CourseTable.COLUMNINSTRUCTOR +"=?", new String[]{instructor.getFname()+" "+instructor.getLname()})>0;
        }
        else{
            result = false;
        }
        return result;
    }

    public Instructor get(String email){
        Instructor instructor = null;

        Cursor c = db.query(true, InstructorTable.TABLENAME,new String[]{InstructorTable.COLUMNID,InstructorTable.COLUMNFNAME,InstructorTable.COLUMNLNAME, InstructorTable.COLUMNEMAIL,InstructorTable.COLUMNWEBSITE,InstructorTable.COLUMNIMAGE}
                , InstructorTable.COLUMNEMAIL +"=?",new String[]{email},null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            instructor = buildFilterFromCursor(c);
            if(!c.isClosed()){
                c.close();
            }
        }
        return instructor;
    }

    /*public Course get(String ){
        Instructor instructor = null;

        Cursor c = db.query(true, InstructorTable.TABLENAME,new String[]{InstructorTable.COLUMNID,InstructorTable.COLUMNFNAME,InstructorTable.COLUMNLNAME, InstructorTable.COLUMNEMAIL,InstructorTable.COLUMNWEBSITE,InstructorTable.COLUMNIMAGE}
                , InstructorTable.COLUMNEMAIL +"=?",new String[]{email},null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            instructor = buildFilterFromCursor(c);
            if(!c.isClosed()){
                c.close();
            }
        }
        return instructor;
    }*/

    public List<Instructor> getAll(){

        List<Instructor> instructors = new ArrayList<Instructor>();
        Cursor c = db.query(InstructorTable.TABLENAME,new String[]{InstructorTable.COLUMNID,InstructorTable.COLUMNFNAME,InstructorTable.COLUMNLNAME, InstructorTable.COLUMNEMAIL,InstructorTable.COLUMNWEBSITE,InstructorTable.COLUMNIMAGE}
                ,null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            do{
                Instructor instructor = buildFilterFromCursor(c);
                if(instructor!=null){
                    instructors.add(instructor);
                }

            }
            while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return instructors;
    }

    private Instructor buildFilterFromCursor(Cursor c){
        Instructor instructor=null;

        if(c!=null){
            instructor = new Instructor();
            instructor.setId(c.getLong(0));
            instructor.setFname(c.getString(1));
            instructor.setLname(c.getString(2));
            instructor.setEmail(c.getString(3));
            instructor.setWebsite(c.getString(4));
            instructor.setImage(c.getBlob(5));
        }

        return instructor;
    }

    private Course buildCourseFromCursor(Cursor cursor)
    {
        Course c = null;
        if (cursor != null)
        {
            c = new Course();
            c.setTitle(cursor.getString(0));
        }

        return c;
    }
}
