/*
HomeWork06
DatabaseDataManager.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by dharak029 on 10/23/2017.
 */

public class DatabaseDataManager {

    private Context context;
    private DataBaseHelper dataBaseHelper;
    private SQLiteDatabase db;
    private RegistrationDAO registrationDAO;
    private CourseDAO courseDAO;
    private InstructorDAO instructorDAO;

    public DatabaseDataManager(Context context) {
        this.context = context;
        dataBaseHelper = new DataBaseHelper(this.context);
        db = dataBaseHelper.getWritableDatabase();
        registrationDAO = new RegistrationDAO(db);
        courseDAO = new CourseDAO(db);
        instructorDAO = new InstructorDAO(db);
    }

    public void close(){
        if(db != null){
            db.close();
        }
    }

    public RegistrationDAO getRegistrationDAO(){
        return this.registrationDAO;
    }

    public InstructorDAO getInstructorDAO(){
        return this.instructorDAO;
    }

    public CourseDAO getCourseDAO(){
        return this.courseDAO;
    }

    public long save(Registeration registeration){
        return registrationDAO.save(registeration);
    }

    public boolean deleteUser(Registeration registeration){
        return registrationDAO.delete(registeration);
    }

    public boolean updateUser(Registeration registeration){
        return registrationDAO.update(registeration);
    }

    public Registeration getUser(String uname){
        return registrationDAO.get(uname);
    }

    public List<Registeration> getAllUsers(){
        return registrationDAO.getAll();
    }

    public long saveInstructor(Instructor instructor){
        return instructorDAO.save(instructor);
    }

    public Instructor getInstructor(String email){
        return instructorDAO.get(email);
    }

    public List<Instructor> getAllInstructor(){
        return instructorDAO.getAll();
    }

    public long saveCourse(Course course){
        return  courseDAO.save(course);
    }

    public Course getCourse(String title){
        return courseDAO.get(title);
    }

    public boolean deleteCourse(Course course){return courseDAO.delete(course);}

    public List<Course> getAllCourse(String uname){
        return courseDAO.getAll(uname);
    }
}
