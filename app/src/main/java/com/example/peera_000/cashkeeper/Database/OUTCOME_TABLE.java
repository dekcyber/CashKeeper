package com.example.peera_000.cashkeeper.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.Toolbar;

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
    public static final String COLUMNOUTCOME_Status ="status";
    private Context context;
    private Toolbar toolbar;
    public OUTCOME_TABLE(Context context) {
        objCK_Ophelper = new CK_OpHelper(context);
        writeDatabase = objCK_Ophelper.getWritableDatabase();
        readDatabase = objCK_Ophelper.getReadableDatabase();
        this.context = context;
    }//Constructor

    public Cursor readAllDataOutcome() {
        Cursor cursorReadAll = readDatabase.query(TABLE_OUTCOME, new String[]{COLUMNOUTCOME_ID, COLUMNOUTCOME_NAME, COLUMNOUTCOME_NameID, COLUMNOUTCOME_Photo,COLUMNOUTCOME_Status}, COLUMNOUTCOME_NAME+" = 'Add'", null, null, null, COLUMNOUTCOME_NAME + " ASC");
        if (cursorReadAll != null) {
            cursorReadAll.moveToFirst();
        }
        return cursorReadAll;
    }

    public Cursor readAllDataOutcomeCate() {
        Cursor cursorReadAll = readDatabase.query(TABLE_OUTCOME, new String[]{COLUMNOUTCOME_ID, COLUMNOUTCOME_NAME, COLUMNOUTCOME_NameID, COLUMNOUTCOME_Photo,COLUMNOUTCOME_Status}, COLUMNOUTCOME_NAME + " NOT IN ('Add')", null, null, null, COLUMNOUTCOME_NAME + " ASC");
        if (cursorReadAll != null) {
            cursorReadAll.moveToFirst();
        }
        return cursorReadAll;
    }
    public long AddCateOutcome(String strName, String strPhoto,String strNameId,String strStatus) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMNOUTCOME_NAME, strName);
        contentValues.put(COLUMNOUTCOME_Photo, strPhoto);
        contentValues.put(COLUMNOUTCOME_NameID,strNameId);
        contentValues.put(COLUMNOUTCOME_Status,strStatus);
        return writeDatabase.insert(TABLE_OUTCOME, null, contentValues);
    }//AddNewValueIncome

    public void InsertOutcome() {
        String[] OutcomeName = {context.getString(R.string.Bill), context.getString(R.string.Car), context.getString(R.string.Entertain)
                , context.getString(R.string.Food), context.getString(R.string.Love), context.getString(R.string.Shopping),
                context.getString(R.string.Transport), context.getString(R.string.Travel)};
        String[] OutcomePic = {"2130837593", "2130837595", "2130837596", "2130837598", "2130837601",
                "2130837603", "2130837604", "2130837605"};
        String[] OutcomeNameID = {"2131165248", "2131165252", "2131165255", "2131165258", "2131165263"
                , "2131165271", "2131165273", "2131165274"};
        String[] OutcomeStatus = {"O","O","O","O","O","O","O","O"};
        for (int i = 0; i < OutcomeName.length; i++) {
            ContentValues objContent = new ContentValues();
            objContent.put(COLUMNOUTCOME_NAME, OutcomeName[i]);
            objContent.put(COLUMNOUTCOME_Photo, OutcomePic[i]);
            objContent.put(COLUMNOUTCOME_NameID, OutcomeNameID[i]);
            objContent.put(COLUMNOUTCOME_Status,OutcomeStatus[i]);
            writeDatabase.insert(TABLE_OUTCOME, null, objContent);
        }
        ContentValues objContent2 = new ContentValues();
        objContent2.put(COLUMNOUTCOME_ID, 9);
        objContent2.put(COLUMNOUTCOME_NAME, "Add");
        objContent2.put(COLUMNOUTCOME_NameID, "2131165247");
        objContent2.put(COLUMNOUTCOME_Photo, "2130837586");
        objContent2.put(COLUMNOUTCOME_Status,"O");
        writeDatabase.insert(TABLE_OUTCOME, null, objContent2);
    }

}
