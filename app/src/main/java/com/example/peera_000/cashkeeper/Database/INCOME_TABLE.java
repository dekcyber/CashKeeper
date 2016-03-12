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
    public static final String COLUMNIN_Status = "status";
    private boolean booCheckIncome;
    private Context context;
    public INCOME_TABLE(Context context) {
        objCK_Ophelper = new CK_OpHelper(context);
        writeDatabase = objCK_Ophelper.getWritableDatabase();
        readDatabase = objCK_Ophelper.getReadableDatabase();
        this.context = context;
    }//Constructor

    public Cursor readAllDataIncome() {
        Cursor cursorReadAll = readDatabase.query(TABLE_INCOME, new String[]{COLUMNIN_ID, COLUMNIN_NAME, COLUMNIN_NameID, COLUMNIN_Photo,COLUMNIN_Status}, COLUMNIN_NAME+" = 'Add'", null, null, null,COLUMNIN_NAME+" ASC");
        if (cursorReadAll != null) {
            cursorReadAll.moveToFirst();
        }
        return cursorReadAll;
    }//ReadAllDataincome เอาแต่ Add
    public Cursor readAllDataCateIncome(){
        Cursor cursorCateIncome = readDatabase.query(TABLE_INCOME,new String[]{COLUMNIN_ID, COLUMNIN_NAME, COLUMNIN_NameID, COLUMNIN_Photo,COLUMNIN_Status},COLUMNIN_NAME+" NOT IN ('Add')",null,null,null,COLUMNIN_NAME+" ASC");
        if (cursorCateIncome!=null){
            cursorCateIncome.moveToFirst();
        }
        return cursorCateIncome;
    }//ReadAllDataIncome ไม่เอา Add
    public boolean CheckIncome() {
        Cursor objcursor = readDatabase.query(TABLE_INCOME, new String[]{COLUMNIN_ID, COLUMNIN_NAME}, null, null, null, null, null);
        if (objcursor != null) {
            objcursor.moveToLast();

        }

        return objcursor.isBeforeFirst();
    }

    public long AddCateIncome(String strName, String strPhoto, String strNameid,String strStatus) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNIN_NAME, strName);
        contentValues.put(COLUMNIN_Photo, strPhoto);
        contentValues.put(COLUMNIN_NameID, strNameid);
        contentValues.put(COLUMNIN_Status,strStatus);
        return writeDatabase.insert(TABLE_INCOME, null, contentValues);
    }//AddNewValueIncome

    public void InsertIncome() {
        String[] IncomeName = {context.getString(R.string.Business), context.getString(R.string.Extra_income)
                , context.getString(R.string.Gifts), context.getString(R.string.Salary)};
        String[] IncomePic = {"2130837583", "2130837586", "2130837588", "2130837591"};
        String[] IncomeNameID = {"2131099701", "2131099706", "2131099708", "2131099718"};
        String[] IncomeStatus = {"O","O","O","O","O","O","O","O"};
        for (int i = 0; i < IncomeName.length; i++) {
            ContentValues objContent = new ContentValues();
            objContent.put(COLUMNIN_NAME, IncomeName[i]);
            objContent.put(COLUMNIN_Photo, IncomePic[i]);
            objContent.put(COLUMNIN_NameID, IncomeNameID[i]);
            objContent.put(COLUMNIN_Status,IncomeStatus[i]);
            writeDatabase.insert(TABLE_INCOME, null, objContent);
        }
        ContentValues objContent2 = new ContentValues();
        objContent2.put(COLUMNIN_ID, 5);
        objContent2.put(COLUMNIN_NAME, "Add");
        objContent2.put(COLUMNIN_NameID, "2131099698");
        objContent2.put(COLUMNIN_Photo, "2130837579");
        objContent2.put(COLUMNIN_Status,"O");
        writeDatabase.insert(TABLE_INCOME, null, objContent2);
    }

}//MainClass
