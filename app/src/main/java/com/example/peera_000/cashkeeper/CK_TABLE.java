package com.example.peera_000.cashkeeper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Blob;

/**
 * Created by peera_000 on 31/12/2558.
 */
public class CK_TABLE {
    //Explicit
    private CK_OpHelper objCK_OpHelper;
    private SQLiteDatabase writerDB,readDB;
    public static final String TABLE_CK = "CK_TABLE";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_InputDate="inputDate";
    public static final String COLUMN_Name="name";
    public static final String COLUMN_Cate="Cate";
    public static final String COLUMN_Income="income";
    public static final String COLUMN_Outcome="outcome";
    public static final String COLUMN_Photo="photo";

    public CK_TABLE(Context context) {
        objCK_OpHelper = new CK_OpHelper(context);
        writerDB = objCK_OpHelper.getWritableDatabase();
        readDB = objCK_OpHelper.getReadableDatabase();
    }//Constructor

    public Cursor readAllData(){
            Cursor objCursor = readDB.query(TABLE_CK,new String[]{COLUMN_ID,COLUMN_InputDate,COLUMN_Name,COLUMN_Cate,COLUMN_Income,COLUMN_Outcome,COLUMN_Photo},null,null,null,null,null);
        if (objCursor !=null){
            objCursor.moveToFirst();
        }
        return objCursor;
    }
    //Add NewValues
    public long addNewValues(String strInputdate,String strCate,String strName,Double douIncome,byte[] bPhoto){
        ContentValues objContentValues = new ContentValues();
        objContentValues.put(COLUMN_InputDate,strInputdate);
        objContentValues.put(COLUMN_Name,strName);
        objContentValues.put(COLUMN_Cate,strCate);
        objContentValues.put(COLUMN_Income,douIncome);
        objContentValues.put(COLUMN_Photo,bPhoto);
        return writerDB.insert(TABLE_CK,null,objContentValues);
    }//Add NewValues

}//Main Class
