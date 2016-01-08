package com.example.peera_000.cashkeeper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

/**
 * Created by peera_000 on 31/12/2558.
 */
public class CK_OpHelper extends SQLiteOpenHelper {
    //Explicit
    private static final String DATABASE_NAME = "CK_DB.db";
    private static final int DATABASE_VERSION = 1;
    private static final String CREATE_TABLE = "create table CK_TABLE (_id integer primary key,"+
            "inputDate text,name text,Cate text,income double,outcome double,photo blob)";
    public CK_OpHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }//Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE);
        Log.d("CreateTable","CreateTable Successful");
    }//OnCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }//OnUpgrade
}//MainClass
