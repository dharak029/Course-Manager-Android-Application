/*
HomeWork06
CourseDAO.java
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

public class CourseDAO {

    private SQLiteDatabase db;

    public CourseDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Course course){

        ContentValues values = new ContentValues();
        values.put(CourseTable.COLUMNTITLE,course.getTitle());
        values.put(CourseTable.COLUMNINSTRUCTOR,course.getInstructor());
        values.put(CourseTable.COLUMNTIME,course.getTime());
        values.put(CourseTable.COLUMNSEMESTER,course.getSemester());
        values.put(CourseTable.COLUMNCREDIT,course.getCredit());
        values.put(CourseTable.COLUMNIMAGE,course.getImage());
        values.put(CourseTable.COLUMNUNAME, course.getUname());

        return db.insert(CourseTable.TABLENAME,null,values);
    }

    public boolean update(Course course){
        ContentValues values = new ContentValues();
        values.put(CourseTable.COLUMNTITLE,course.getTitle());
        values.put(CourseTable.COLUMNINSTRUCTOR,course.getInstructor());
        values.put(CourseTable.COLUMNTIME,course.getTime());
        values.put(CourseTable.COLUMNSEMESTER,course.getSemester());
        values.put(CourseTable.COLUMNCREDIT,course.getCredit());
        values.put(CourseTable.COLUMNCREDIT,course.getImage());
        return db.update(CourseTable.TABLENAME,values, CourseTable.COLUMNTITLE +"=?",new String[]{course.getTitle()})>0;
    }

    public boolean delete(Course course){
        return db.delete(CourseTable.TABLENAME, CourseTable.COLUMNTITLE +"=?"+" and "+CourseTable.COLUMNUNAME+"=?"+" and "+CourseTable.COLUMNINSTRUCTOR+"=?",new String[]{course.getTitle(),course.getUname(),course.getInstructor()})>0;
    }

    public Course get(String title){
        Course course = null;

        Cursor c = db.query(true, CourseTable.TABLENAME,new String[]{CourseTable.COLUMNTITLE,CourseTable.COLUMNINSTRUCTOR,CourseTable.COLUMNTIME, CourseTable.COLUMNSEMESTER,CourseTable.COLUMNCREDIT,CourseTable.COLUMNIMAGE, CourseTable.COLUMNUNAME}
                , CourseTable.COLUMNTITLE +"=?",new String[]{title},null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            course = buildFilterFromCursor(c);
            if(!c.isClosed()){
                c.close();
            }
        }
        return course;
    }

    public List<Course> getAll(String uname){

        List<Course> courseList = new ArrayList<Course>();
        Cursor c = db.query(true,CourseTable.TABLENAME,new String[]{CourseTable.COLUMNTITLE,CourseTable.COLUMNINSTRUCTOR,CourseTable.COLUMNTIME, CourseTable.COLUMNSEMESTER,CourseTable.COLUMNCREDIT,CourseTable.COLUMNIMAGE, CourseTable.COLUMNUNAME}
                ,CourseTable.COLUMNUNAME +"=?", new String[]{uname},null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            do{
                Course course = buildFilterFromCursor(c);
                if(course!=null){
                    courseList.add(course);
                }

            }
            while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return courseList;
    }

    private Course buildFilterFromCursor(Cursor c){
        Course course=null;

        if(c!=null){
            course = new Course();
            course.setTitle(c.getString(0));
            course.setInstructor(c.getString(1));
            course.setTime(c.getString(2));
            course.setSemester(c.getString(3));
            course.setCredit(c.getInt(4));
            course.setImage(c.getBlob(5));
            course.setUname(c.getString(6));
        }

        return course;
    }
}
