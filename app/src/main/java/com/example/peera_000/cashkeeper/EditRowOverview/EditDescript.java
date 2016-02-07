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

import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.MainActivity;
import com.example.peera_000.cashkeeper.R;

import java.util.Calendar;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_descript);
        customFont = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
        Edsp = getSharedPreferences("EditRowOverData", MODE_PRIVATE);
        edit_Edsp = Edsp.edit();
        BlindWidget();
        CallRowdataTotext();
        CalendarEdit();

    }//OnCreate
    public void CallRowdataTotext(){
            Bundle bundle = getIntent().getExtras();
            String strID = bundle.getString("ID");
            Cursor objReadrowCursor = ck_table.readRowOfId(strID);

            int IncomeMIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Income);
            String strIncomeM = objReadrowCursor.getString(IncomeMIndex);
            int OutcomeMIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Outcome);
            String strOutcomeM = objReadrowCursor.getString(OutcomeMIndex);
            if (strIncomeM.equals(null)){
                edtMonney.setText(strOutcomeM);
                edit_Edsp.putString("IncomeM",strOutcomeM);
            }else {
                edtMonney.setText(strIncomeM);
                edit_Edsp.putString("IncomeM",strIncomeM);
            }
            int NoteIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Note);
            String strNote = objReadrowCursor.getString(NoteIndex);
            if (strNote.equals(null)){

            }else {
                edtNote.setText(strNote);
                edit_Edsp.putString("Note",strNote);
            }
        int DateIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_InputDate);
        String strDate = objReadrowCursor.getString(DateIndex);
        TxtDatepicker.setText(strDate);
        edit_Edsp.putString("Date",String.valueOf(strDate));
        int ImgIndex = objReadrowCursor.getColumnIndex(CK_TABLE.COLUMN_Photo);
        String strImg = objReadrowCursor.getString(ImgIndex);
        int intImg = Integer.parseInt(strImg);
        edit_Edsp.putInt("Photo",intImg);
        img.setImageResource(intImg);
    }//CallRowOfId
    public void BlindWidget(){
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
                Money = edtMonney.getText().toString();
                edit_Edsp.putString("IncomeM",Money);//SharedPreference ตัวนี้เพราะตอนกลับมาค่อยเช็คกับหมวดอีกที
                Log.d("EditRowOverData", "Money" + Money);
                Note = edtNote.getText().toString();
                edit_Edsp.putString("Note",Note);
                Log.d("EditRowOverData", "Note" + Note);
                onBackPressed();

            }
        });
    }//BlindWidget
    public void CalendarEdit(){
        Calendar calendar = Calendar.getInstance();
        intYear = calendar.get(Calendar.YEAR);
        intMonth = calendar.get(Calendar.MONTH);
        intDate = calendar.get(Calendar.DAY_OF_MONTH);
        TxtDatepicker.setTypeface(customFont);
        TxtDatepicker.setText(intYear + "/" + (intMonth + 1) + "/" + intDate);


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
        switch (item.getItemId()){

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostResume() {

        super.onPostResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }
}//MainClass
