package com.example.peera_000.cashkeeper.Database;

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
    private static final String CREATE_TABLE_CKTABLE = "create table CK_TABLE (_id integer primary key," +
            "inputDate text,name text,cate text,note text,income double,outcome double,photo text)";
    private static final String CREATE_TABLE_CATINCOME = "create table INCOME_TABLE(_id integer primary key," +
            "Name text,Photo text)";
    private static final String CREATE_TABLE_CATOUTCOME = "create table OUTCOME_TABLE(_id integer primary key," +
            "Name text,Photo text)";
    public CK_OpHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }//Constructor

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CKTABLE);
        db.execSQL(CREATE_TABLE_CATINCOME);
        db.execSQL(CREATE_TABLE_CATOUTCOME);
        Log.d("CreateTable", "CreateTable Successful");
    }//OnCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }//OnUpgrade

}//MainClass
