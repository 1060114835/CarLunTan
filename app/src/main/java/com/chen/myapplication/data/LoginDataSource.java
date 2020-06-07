package com.chen.myapplication.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.chen.myapplication.EditActivity;
import com.chen.myapplication.MyApplication;
import com.chen.myapplication.data.model.LoggedInUser;
import com.chen.myapplication.db.CarDataBase;
import com.chen.myapplication.ui.home.UserBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {
        return null;
    }

    private List<UserBean> query(SQLiteDatabase database) {
        List<UserBean> data = new ArrayList<>();
        Cursor cursor = database.query("user", null,
                null,null,null,null,null);
        int size = cursor.getCount();
        for (int i = 0; i < size; i++) {
            cursor.moveToNext();
            String account = cursor.getString(cursor.getColumnIndex("account"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String age = cursor.getString(cursor.getColumnIndex("age"));
            String location = cursor.getString(cursor.getColumnIndex("location"));
            String interest = cursor.getString(cursor.getColumnIndex("interest"));
            String school = cursor.getString(cursor.getColumnIndex("school"));
            String intro = cursor.getString(cursor.getColumnIndex("intro"));
            data.add(new UserBean(account, password, name, age, location, interest, school, intro));
        }
        cursor.close();
        return data;
    }




    public void logout() {
        // TODO: revoke authentication
    }
}