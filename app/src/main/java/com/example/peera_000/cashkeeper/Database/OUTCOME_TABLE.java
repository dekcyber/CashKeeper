package com.example.peera_000.cashkeeper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by peera_000 on 16/1/2559.
 */
public class OUTCOME_TABLE {
    //Explicit
    private CK_OpHelper objCK_Ophelper;
    private SQLiteDatabase writeDatabase, readDatabase;
    public static final String TABLE_OUTCOME = "OUTCOME_TABLE";
    public static final String COLUMNOUTCOME_ID = "_id";
    public static final String COLUMNOUTCOME_NAME = "Name";
    public static final String COLUMNOUTCOME_Photo = "Photo";

    public OUTCOME_TABLE(Context context) {
        objCK_Ophelper = new CK_OpHelper(context);
        writeDatabase = objCK_Ophelper.getWritableDatabase();
        readDatabase = objCK_Ophelper.getReadableDatabase();
    }//Constructor

    public Cursor readAllDataOutcome() {
        Cursor cursorReadAll = readDatabase.query(TABLE_OUTCOME, new String[]{COLUMNOUTCOME_ID, COLUMNOUTCOME_NAME, COLUMNOUTCOME_Photo}, null, null, null, null, null);
        if (cursorReadAll != null) {
            cursorReadAll.moveToFirst();
        }
        return cursorReadAll;
    }

    public long AddCateOutcome(String strName, String strPhoto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNOUTCOME_NAME, strName);
        contentValues.put(COLUMNOUTCOME_Photo, strPhoto);
        return writeDatabase.insert(TABLE_OUTCOME, null, contentValues);
    }//AddNewValueIncome

}
