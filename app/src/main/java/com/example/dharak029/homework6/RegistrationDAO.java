/*
HomeWork06
RegistrationDAO.java
Viranchi Deshpande, Dharak Shah
 */
package com.example.dharak029.homework6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dharak029 on 10/23/2017.
 */

public class RegistrationDAO {

    private SQLiteDatabase db;

    public RegistrationDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public long save(Registeration registeration){
        Registeration objCheck = null;
        objCheck = get(registeration.getUname());
        long result;
        if (objCheck != null) {
            result = -2;
        }
        else
        {
            ContentValues values = new ContentValues();
            values.put(RegisterTable.COLUMNFNAME, registeration.getUname());
            values.put(RegisterTable.COLUMNLNAME, registeration.getUname());
            values.put(RegisterTable.COLUMNUNAME, registeration.getUname());
            values.put(RegisterTable.COLUMNPASSWORD, registeration.getPassword());
            values.put(RegisterTable.COLUMNIMAGE, registeration.getImage());
            result = db.insert(RegisterTable.TABLENAME, null, values);
        }
        return result;
    }

    public boolean update(Registeration registeration){
        ContentValues values = new ContentValues();
        values.put(RegisterTable.COLUMNFNAME,registeration.getUname());
        values.put(RegisterTable.COLUMNLNAME,registeration.getUname());
        values.put(RegisterTable.COLUMNUNAME,registeration.getUname());
        values.put(RegisterTable.COLUMNPASSWORD,registeration.getPassword());
        values.put(RegisterTable.COLUMNIMAGE,registeration.getImage());
        return db.update(RegisterTable.TABLENAME,values, RegisterTable.COLUMNUNAME +"=?",new String[]{registeration.getUname()})>0;
    }

    public boolean delete(Registeration registeration){
        return db.delete(RegisterTable.TABLENAME, RegisterTable.COLUMNUNAME +"=?",new String[]{registeration.getUname()})>0;
    }

    public Registeration get(String uname){
        Registeration filter = null;

        Cursor c = db.query(true, RegisterTable.TABLENAME,new String[]{RegisterTable.COLUMNID,RegisterTable.COLUMNFNAME,RegisterTable.COLUMNLNAME,RegisterTable.COLUMNUNAME, RegisterTable.COLUMNPASSWORD,RegisterTable.COLUMNIMAGE}
        , RegisterTable.COLUMNUNAME +"=?",new String[]{uname},null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            filter = buildFilterFromCursor(c);
            if(!c.isClosed()){
                c.close();
            }
        }
        return filter;
    }

    public List<Registeration> getAll(){

        List<Registeration> userList = new ArrayList<Registeration>();
        Cursor c = db.query(RegisterTable.TABLENAME,new String[]{RegisterTable.COLUMNID,RegisterTable.COLUMNFNAME,RegisterTable.COLUMNLNAME, RegisterTable.COLUMNUNAME,RegisterTable.COLUMNPASSWORD,RegisterTable.COLUMNIMAGE}
                ,null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            do{
                Registeration filter = buildFilterFromCursor(c);
                if(filter!=null){
                    userList.add(filter);
                }

            }
            while (c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return userList;
    }

    private Registeration buildFilterFromCursor(Cursor c){
        Registeration user=null;

        if(c!=null){
            user = new Registeration();
            user.setId(c.getLong(0));
            user.setFname(c.getString(1));
            user.setLname(c.getString(2));
            user.setUname(c.getString(3));
            user.setPassword(c.getString(4));
            user.setImage(c.getBlob(5));
        }

        return user;
    }
}
