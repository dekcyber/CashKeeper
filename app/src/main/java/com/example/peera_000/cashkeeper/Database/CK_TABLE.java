package com.example.peera_000.cashkeeper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.peera_000.cashkeeper.Database.CK_OpHelper;

/**
 * Created by peera_000 on 31/12/2558.
 */
public class CK_TABLE {
    //Explicit
    private CK_OpHelper objCK_OpHelper;
    private SQLiteDatabase writerDB, readDB;
    public static final String TABLE_CK = "CK_TABLE";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_InputDate = "inputDate";
    public static final String COLUMN_Name = "user";
    public static final String COLUMN_Cate = "cate";
    public static final String COLUMN_CateID = "cateId";
    public static final String COLUMN_Note = "note";
    public static final String COLUMN_Income = "income";
    public static final String COLUMN_Outcome = "outcome";
    public static final String COLUMN_Photo = "photo";

    public CK_TABLE(Context context) {
        objCK_OpHelper = new CK_OpHelper(context);
        writerDB = objCK_OpHelper.getWritableDatabase();
        readDB = objCK_OpHelper.getReadableDatabase();

    }//Constructor

    public Cursor readAllData() {
        Cursor objCursor = readDB.query(TABLE_CK, new String[]{COLUMN_ID, COLUMN_InputDate, COLUMN_Name, COLUMN_Cate, COLUMN_Note, COLUMN_Income, COLUMN_Outcome, COLUMN_Photo}, null, null, null, null, null);
        if (objCursor != null) {
            objCursor.moveToFirst();
        }
        return objCursor;
    }

    //Add NewValues
    public long addNewValuesIncome(String strInputdate, String strCate, String strCateId, String strName, String strNote, Double douIncome, String strPhoto) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_InputDate, strInputdate);
        objContentValues.put(COLUMN_Name, strName);
        objContentValues.put(COLUMN_Cate, strCate);
        objContentValues.put(COLUMN_CateID, strCateId);
        objContentValues.put(COLUMN_Note, strNote);
        objContentValues.put(COLUMN_Income, douIncome);
        objContentValues.put(COLUMN_Photo, strPhoto);
        return writerDB.insert(TABLE_CK, null, objContentValues);
    }//Add NewValues

    public long addNewValuesOutcome(String strInputdate, String strCate, String strCateId, String strName, String strNote, Double douOutcome, String strPhoto) {
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_InputDate, strInputdate);
        objContentValues.put(COLUMN_Name, strName);
        objContentValues.put(COLUMN_Cate, strCate);
        objContentValues.put(COLUMN_CateID, strCateId);
        objContentValues.put(COLUMN_Note, strNote);
        objContentValues.put(COLUMN_Outcome, douOutcome);
        objContentValues.put(COLUMN_Photo, strPhoto);
        return writerDB.insert(TABLE_CK, null, objContentValues);
    }//Add NewValues

    public Cursor readDESC() {
        Cursor desc = readDB.query(TABLE_CK, new String[]{COLUMN_ID, COLUMN_InputDate, COLUMN_Name, COLUMN_Cate, COLUMN_CateID, COLUMN_Note, COLUMN_Income, COLUMN_Outcome, COLUMN_Photo}, null, null, null, null, COLUMN_InputDate + " DESC");
        if (desc != null) {
            desc.moveToFirst();
        }
        Log.d("ReadDesc", "Success");
        return desc;
    }

    public int SumMoneyOfDay(String strDate){
        String Date = strDate;
        int Value=0;
        Cursor objCursorIncome = readDB.rawQuery("SELECT SUM(income),SUM(outcome) FROM CK_TABLE WHERE inputdate='" + Date + "';", null);
        if (objCursorIncome!=null){
            objCursorIncome.moveToFirst();
            Value = objCursorIncome.getInt(0)-objCursorIncome.getInt(1);
        }
        return Value;
    }
    public int SumOutcomeAll(){
        int ValueOutcome=0;
        Cursor objCursorOut = readDB.rawQuery("SELECT SUM(outcome) FROM CK_TABLE;",null);
        if (objCursorOut!=null){
            objCursorOut.moveToFirst();
            ValueOutcome = objCursorOut.getInt(0);
        }
       return ValueOutcome;
    }


}//Main Class
