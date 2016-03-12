package com.example.peera_000.cashkeeper.EditRowOverview;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.MainActivity;
import com.example.peera_000.cashkeeper.R;

import java.util.Calendar;
import java.util.zip.DeflaterOutputStream;

public class EditDescript extends AppCompatActivity {
    //Explicit

    private EditText edtMonney;
    private Toolbar toolbar;
    private ImageView img;
    private TextView TxtDatepicker;
    private EditText edtNote;
    private int intDate;
    private int intMonth;
    private int intYear;
    private static final int DILOG_ID = 0;
    private static DatePickerDialog.OnDateSetListener Datesetpicker;
    private String Money;
    private String MoneyComp;
    private String Category;
    private String Note;
    private String Date;
    private int imgPhoto;
    private CK_TABLE ck_table;
    double douMoney;
    private String Nameid;
    private Typeface customFont;
    private SharedPreferences Edsp;
    private SharedPreferences.Editor edit_Edsp;
    private String strID;
    private String strCate;
    private String strCateId;
    private int intPhoto;
    private int comparePhoto;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_descript);
        customFont = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
        Edsp = getSharedPreferences("EditRowOverData", MODE_PRIVATE);
        edit_Edsp = Edsp.edit();
        BlindWidget();
        CallRowdataTotext();
        CalendarEdit();
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Money = edtMonney.getText().toString();
                Date = TxtDatepicker.getText().toString();
                edit_Edsp.putString("DateN", Date);
                edit_Edsp.putString("IncomeM", Money);//SharedPreference ตัวนี้เพราะตอนกลับมาค่อยเช็คกับหมวดอีกที
                Log.d("EditRowOverData", "Money = " + Money);
                Note = edtNote.getText().toString();
                edit_Edsp.putString("Note", Note);
                edit_Edsp.commit();
                Log.d("EditRowOverData", "Note = " + Note);
                Intent intent = new Intent(EditDescript.this, EditmoneyCate.class);
                startActivity(intent);
            }
        });


    }//OnCreate

    public void CallRowdataTotext() {
        Bundle bundle = getIntent().getExtras();
        strID = bundle.getString("ID");
        Log.d("Edit", "RowId = " + strID);
        edit_Edsp.putString("EditRowId", strID);
        //edit_Edsp.commit();
        Cursor objReadrowCursor = ck_table.readRowOfId(strID);

        int IncomeMIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Income);
        String strIncomeM = objReadrowCursor.getString(IncomeMIndex);
        edit_Edsp.putString("CheckCate", strIncomeM);
        edit_Edsp.commit();
        //Log.d("Edit", "MoneyIn = " + strIncomeM);
        int OutcomeMIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Outcome);
        String strOutcomeM = objReadrowCursor.getString(OutcomeMIndex);
        //Log.d("Edit", "MoneyOut = " + strOutcomeM);
        if (strIncomeM == null) {
            Money = strOutcomeM;

            Log.d("Edit", "MoneyOut = " + strOutcomeM);
        } else {
            Money = strIncomeM;
            Log.d("Edit", "MoneyIn = " + strIncomeM);
        }
        MoneyComp = Edsp.getString("IncomeMChange", null);
        Log.d("Edit", "MoneyChange = " + MoneyComp);
        if (MoneyComp != null) {
            Money = MoneyComp;
            Log.d("Edit", "MoneyChange = " + Money);
        }
        edtMonney.setText(Money);

        int NoteIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Note);
        String strNote = objReadrowCursor.getString(NoteIndex);
        Note = Edsp.getString("Note", null);
        if (Note != null) {
            edtNote.setText(Note);
        } else {
            if (strNote != null) {
                edtNote.setText(strNote);
                edit_Edsp.putString("Note", strNote);
            }
        }
        int DateIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_InputDate);
        String strDate = objReadrowCursor.getString(DateIndex);
        edit_Edsp.putString("Date", String.valueOf(strDate));
        edit_Edsp.commit();
        String DateN = Edsp.getString("DateN", null);
        Date = Edsp.getString("Date",null);
        Log.d("Edit", "DateNew = " + Date);
        if (Date==DateN){

            TxtDatepicker.setText(Date);
            Log.d("Edit", "Date = " + Date);
        }else {
            if (DateN!=null){
                Date = DateN;
                TxtDatepicker.setText(Date);
            }
            TxtDatepicker.setText(Date);
            Log.d("Edit", "DateNew = " + Date);
        }
        int ImgIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_CPhoto);
        String strImg = objReadrowCursor.getString(ImgIndex);
        imgPhoto = Integer.parseInt(strImg);
        edit_Edsp.putInt("Photo", imgPhoto);
        int CateIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Cate);
        strCate = objReadrowCursor.getString(CateIndex);
        int CateIdIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_CateID);
        strCateId = objReadrowCursor.getString(CateIdIndex);

        intPhoto = Edsp.getInt("EditIncomePhoto", 0);
        if (intPhoto != 0) {
            strCate = Edsp.getString("EditIncomeName", null);
            Log.d("Edit", "CateName = " + strCate);
            strCateId = Edsp.getString("EditIncomeNameId", null);
            Log.d("Edit", "CateNameID = " + strCateId);
            imgPhoto = intPhoto;
            edit_Edsp.putInt("Photo", imgPhoto);
        }
        img.setImageResource(imgPhoto);


        edit_Edsp.commit();

    }//CallRowOfId

    public void BlindWidget() {
        ck_table = new CK_TABLE(this);
        img = (ImageView) findViewById(R.id.SelectImg);
        edtMonney = (EditText) findViewById(R.id.edtAdmoney);
        edtMonney.setTypeface(customFont);
        toolbar = (Toolbar) findViewById(R.id.Toobar_Descrip);
        TxtDatepicker = (TextView) findViewById(R.id.TxtDatePicker);
        edtNote = (EditText) findViewById(R.id.edtNote);
        edtNote.setTypeface(customFont);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });
    }//BlindWidget

    public void CalendarEdit() {
        Calendar calendar = Calendar.getInstance();
        intYear = calendar.get(Calendar.YEAR);
        intMonth = calendar.get(Calendar.MONTH);
        intDate = calendar.get(Calendar.DAY_OF_MONTH);
        TxtDatepicker.setTypeface(customFont);
        //TxtDatepicker.setText(intYear + "/" + (intMonth + 1) + "/" + intDate);


        Datesetpicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                intYear = year;
                intMonth = monthOfYear + 1;
                intDate = dayOfMonth;
                TxtDatepicker.setText(intYear + "/" + intMonth + "/" + intDate);
                edit_Edsp.putString("Date", String.valueOf(TxtDatepicker));
                Log.d("EditRowOverData", "Date" + TxtDatepicker);
            }
        };

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 0:
                return new DatePickerDialog(this, Datesetpicker, intYear, intMonth, intDate);


        }
        return null;
    }

    public void ShowDialog(View view) {
        showDialog(DILOG_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_admoney, menu);
        MenuItem item = menu.findItem(R.id.AddmoneyNext);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Date = TxtDatepicker.getText().toString();
        if (Date == null) {
            Date = Edsp.getString("Date", null);
        }
        Note = edtNote.getText().toString();
        if (Note == null) {
            Note = Edsp.getString("Note", null);
        }
        Double douMoney = Double.parseDouble(Money);
        String strcheckM = edtMonney.getText().toString();
        Double douCheckM = Double.parseDouble(strcheckM);
        if (douMoney!=douCheckM){
            douMoney = douCheckM;
        }

        String strPhoto = Integer.toString(imgPhoto);
        String strRowId = strID;
        int intChecktab = Edsp.getInt("EditCheckTab", 0);
        Log.d("Edit", "CheckTab = " + intChecktab);
        switch (item.getItemId()) {

            case R.id.OK:
                if (intChecktab==2){
                ck_table.EditRowDataIncome(Date, strCate, strCateId, Note, douMoney, strPhoto, strRowId);
                Toast.makeText(this, "CLick", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                edit_Edsp.clear();
                edit_Edsp.commit();
                startActivity(intent);
                }else if (intChecktab==1 || intChecktab==0){
                    ck_table.EditRowDataOutcome(Date, strCate, strCateId, Note, douMoney, strPhoto, strRowId);
                    Toast.makeText(this, "CLick", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, MainActivity.class);
                    edit_Edsp.clear();
                    edit_Edsp.commit();
                    startActivity(intent);
                }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostResume() {

        super.onPostResume();
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }
}//MainClass
