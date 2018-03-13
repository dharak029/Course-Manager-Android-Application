/*
HomeWork06
Registration.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

/**
 * Created by dharak029 on 11/3/2017.
 */

public class Registeration {
    private long id;
    private String fname,lname ,uname,password;
    private byte[] image;

    public Registeration(String uname, String password, byte[] image,String fname,String lname) {
        this.uname = uname;
        this.password = password;
        this.image = image;
        this.fname =  fname;
        this.lname = lname;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Registeration(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
