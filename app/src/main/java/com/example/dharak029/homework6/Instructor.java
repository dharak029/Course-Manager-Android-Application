/*
HomeWork06
Instructor.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

/**
 * Created by dharak029 on 11/5/2017.
 */

public class Instructor {
    String fname,lname,email,website;
    byte[] image;


    long id;

    public Instructor(){}

    public Instructor(String fname, String lname, String email, String website, byte[] image) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.website = website;
        this.image = image;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
