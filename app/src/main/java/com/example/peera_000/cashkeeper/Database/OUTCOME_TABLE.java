package com.example.peera_000.cashkeeper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.peera_000.cashkeeper.R;

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
    public static final String COLUMNOUTCOME_NameID = "NameId";
    private Context context;
    public OUTCOME_TABLE(Context context) {
        objCK_Ophelper = new CK_OpHelper(context);
        writeDatabase = objCK_Ophelper.getWritableDatabase();
        readDatabase = objCK_Ophelper.getReadableDatabase();
        this.context = context;
    }//Constructor

    public Cursor readAllDataOutcome() {
        Cursor cursorReadAll = readDatabase.query(TABLE_OUTCOME, new String[]{COLUMNOUTCOME_ID, COLUMNOUTCOME_NAME, COLUMNOUTCOME_NameID, COLUMNOUTCOME_Photo}, null, null, null, null, COLUMNOUTCOME_ID + " ASC");
        if (cursorReadAll != null) {
            cursorReadAll.moveToFirst();
        }
        return cursorReadAll;
    }

    public Cursor readAllDataOutcomeCate() {
        Cursor cursorReadAll = readDatabase.query(TABLE_OUTCOME, new String[]{COLUMNOUTCOME_ID, COLUMNOUTCOME_NAME, COLUMNOUTCOME_NameID, COLUMNOUTCOME_Photo}, COLUMNOUTCOME_NAME + " NOT IN ('Add')", null, null, null, null);
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

    public void InsertOutcome() {
        String[] OutcomeName = {context.getString(R.string.Bill), context.getString(R.string.Car), context.getString(R.string.Entertain)
                , context.getString(R.string.Food), context.getString(R.string.Love), context.getString(R.string.Shopping),
                context.getString(R.string.Transport), context.getString(R.string.Travel)};
        String[] OutcomePic = {"2130837578", "2130837580", "2130837581", "2130837583", "2130837586",
                "2130837588", "2130837589", "2130837590"};
        String[] OutcomeNameID = {"2131099699", "2131099702", "2131099704", "2131099707", "2131099712"
                , "2131099720", "2131099721", "2131099722"};
        for (int i = 0; i < OutcomeName.length; i++) {
            ContentValues objContent = new ContentValues();
            objContent.put(COLUMNOUTCOME_NAME, OutcomeName[i]);
            objContent.put(COLUMNOUTCOME_Photo, OutcomePic[i]);
            objContent.put(COLUMNOUTCOME_NameID, OutcomeNameID[i]);
            writeDatabase.insert(TABLE_OUTCOME, null, objContent);
        }
        ContentValues objContent2 = new ContentValues();
        objContent2.put(COLUMNOUTCOME_ID, 99);
        objContent2.put(COLUMNOUTCOME_NAME, "Add");
        objContent2.put(COLUMNOUTCOME_NameID, "2131099698");
        objContent2.put(COLUMNOUTCOME_Photo, "2130837577");
        writeDatabase.insert(TABLE_OUTCOME, null, objContent2);
    }

}
