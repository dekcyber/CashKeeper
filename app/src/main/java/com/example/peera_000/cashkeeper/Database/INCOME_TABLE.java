package com.example.peera_000.cashkeeper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by peera_000 on 16/1/2559.
 */
public class INCOME_TABLE {
    //Explicit
    private CK_OpHelper objCK_Ophelper;
    private SQLiteDatabase writeDatabase, readDatabase;
    public static final String TABLE_INCOME = "INCOME_TABLE";
    public static final String COLUMNIN_ID = "_id";
    public static final String COLUMNIN_NAME = "Name";
    public static final String COLUMNIN_Photo = "Photo";

    public INCOME_TABLE(Context context) {
        objCK_Ophelper = new CK_OpHelper(context);
        writeDatabase = objCK_Ophelper.getWritableDatabase();
        readDatabase = objCK_Ophelper.getReadableDatabase();
    }//Constructor

    public Cursor readAllDataIncome() {
        Cursor cursorReadAll = readDatabase.query(TABLE_INCOME, new String[]{COLUMNIN_ID, COLUMNIN_NAME, COLUMNIN_Photo}, null, null, null, null, null);
        if (cursorReadAll != null) {
            cursorReadAll.moveToFirst();
        }
        return cursorReadAll;
    }

    public long AddCateIncome(String strName, String strPhoto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNIN_NAME, strName);
        contentValues.put(COLUMNIN_Photo, strPhoto);
        return writeDatabase.insert(TABLE_INCOME, null, contentValues);
    }//AddNewValueIncome


}//MainClass
