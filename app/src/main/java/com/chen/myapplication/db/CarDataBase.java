package com.chen.myapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CarDataBase extends SQLiteOpenHelper {


    private static CarDataBase carDataBase;

    public static CarDataBase  getInstance(Context context, String name) {
        if (carDataBase == null) {
            carDataBase = new CarDataBase(context, name, null, 1);
        }
        return carDataBase;
    }





    public static final String CreateCarInfo = "create table car ("
            + "name text , "
            + "date text , "
            + "grade text)";

    public static final String UserInfo = "create table user ("
            + "account text , "
            + "password text , "
            + "name text , "
            + "age text , "
            + "location text , "
            + "interest text , "
            + "school text , "
            + "intro text)";



    public CarDataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CreateCarInfo);
        db.execSQL(UserInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
