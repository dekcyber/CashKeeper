package com.example.peera_000.cashkeeper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.peera_000.cashkeeper.R;


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
    public static final String COLUMNIN_NameID = "NameId";
    private boolean booCheckIncome;
    private Context context;
    public INCOME_TABLE(Context context) {
        objCK_Ophelper = new CK_OpHelper(context);
        writeDatabase = objCK_Ophelper.getWritableDatabase();
        readDatabase = objCK_Ophelper.getReadableDatabase();
        this.context = context;
    }//Constructor

    public Cursor readAllDataIncome() {
        Cursor cursorReadAll = readDatabase.query(TABLE_INCOME, new String[]{COLUMNIN_ID, COLUMNIN_NAME, COLUMNIN_NameID, COLUMNIN_Photo}, null, null, null, null, null);
        if (cursorReadAll != null) {
            cursorReadAll.moveToFirst();
        }
        return cursorReadAll;
    }

    public boolean CheckIncome() {
        Cursor objcursor = readDatabase.query(TABLE_INCOME, new String[]{COLUMNIN_ID, COLUMNIN_NAME}, null, null, null, null, null);
        if (objcursor != null) {
            objcursor.moveToLast();

        }

        return objcursor.isBeforeFirst();
    }

    public long AddCateIncome(String strName, String strPhoto) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNIN_NAME, strName);
        contentValues.put(COLUMNIN_Photo, strPhoto);
        return writeDatabase.insert(TABLE_INCOME, null, contentValues);
    }//AddNewValueIncome

    public void InsertIncome() {
        String[] IncomeName = {context.getString(R.string.Business), context.getString(R.string.Extra_income)
                , context.getString(R.string.Gifts), context.getString(R.string.Salary)};
        String[] IncomePic = {"2130837578", "2130837581", "2130837583", "2130837586"};
        String[] IncomeNameID = {"2131099700", "2131099705", "2131099707", "2131099717"};
        for (int i = 0; i < IncomeName.length; i++) {
            ContentValues objContent = new ContentValues();
            objContent.put(COLUMNIN_NAME, IncomeName[i]);
            objContent.put(COLUMNIN_Photo, IncomePic[i]);
            objContent.put(COLUMNIN_NameID, IncomeNameID[i]);
            writeDatabase.insert(TABLE_INCOME, null, objContent);
        }
    }

}//MainClass
