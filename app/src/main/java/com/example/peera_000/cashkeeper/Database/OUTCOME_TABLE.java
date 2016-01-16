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
    private static final String TABLE_OUTCOME = "OUTCOME_TABLE";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_Phote = "Photo";

    public OUTCOME_TABLE(Context context) {
        objCK_Ophelper = new CK_OpHelper(context);
        writeDatabase = objCK_Ophelper.getWritableDatabase();
        readDatabase = objCK_Ophelper.getReadableDatabase();
    }//Constructor

    public Cursor readAllDataOutcome() {
        Cursor cursorReadAll = readDatabase.query(TABLE_OUTCOME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_Phote}, null, null, null, null, null);
        if (cursorReadAll != null) {
            cursorReadAll.moveToFirst();
        }
        return cursorReadAll;
    }

    public long AddCateIncome(String strName, String strPhoto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, strName);
        contentValues.put(COLUMN_Phote, strPhoto);
        return writeDatabase.insert(TABLE_OUTCOME, null, contentValues);
    }//AddNewValueIncome

}
