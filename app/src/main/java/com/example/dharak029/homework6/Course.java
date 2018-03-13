package com.example.dharak029.homework6;
/*
HomeWork06
Course.java
Viranchi Deshpande, Dharak Shah
 */

import java.io.Serializable;

/**
 * Created by dharak029 on 11/3/2017.
 */

public class Course implements Serializable{

    String title,instructor,time,semester, uname;
    int credit;
    byte[] image;

    public Course(){

    }



    public Course(String title, String instructor, String time, String semester, int credit,byte[] image, String uname) {
        this.title = title;
        this.instructor = instructor;
        this.time = time;
        this.semester = semester;
        this.credit = credit;
        this.image = image;
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
}
