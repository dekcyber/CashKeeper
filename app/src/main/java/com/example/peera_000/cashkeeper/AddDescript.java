package com.example.peera_000.cashkeeper;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.zip.Inflater;

import javax.xml.transform.dom.DOMLocator;

public class AddDescript extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private EditText edtMonney;
    private Toolbar toolbar;
    private TextView TxtDatepicker;
    private EditText edtNote;
    private int intDate;
    private int intMonth;
    private int intYear;
    private static final int DILOG_ID = 0;
    private static DatePickerDialog.OnDateSetListener Datesetpicker;
    String Money;
    String MoneyComp;
    String Category;
    String Note;
    String Date;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_descript);
        sp = getSharedPreferences("IncomeData", Context.MODE_PRIVATE);
        editor = sp.edit();
        Money = sp.getString("Money", null);
        edtMonney = (EditText) findViewById(R.id.edtAdmoney);
        toolbar = (Toolbar) findViewById(R.id.Toobar_Descrip);
        TxtDatepicker = (TextView) findViewById(R.id.TxtDatePicker);
        edtNote = (EditText) findViewById(R.id.edtNote);
        edtNote.getText().toString();
        edtMonney.setText(Money);
        MoneyComp = edtMonney.getText().toString();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Calendar calendar = Calendar.getInstance();
        intYear = calendar.get(Calendar.YEAR);
        intMonth = calendar.get(Calendar.MONTH);
        intDate = calendar.get(Calendar.DAY_OF_MONTH);

        TxtDatepicker.setText(intYear + "/" + (intMonth + 1) + "/" + intDate);


        Datesetpicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                intYear = year;
                intMonth = monthOfYear + 1;
                intDate = dayOfMonth;
                TxtDatepicker.setText(intYear + "/" + intMonth + "/" + intDate);

            }
        };

        Date = String.valueOf(TxtDatepicker);
        Note = String.valueOf(edtNote);



    }//OnCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_admoney, menu);
        MenuItem item = menu.findItem(R.id.AddmoneyNext);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int CheckIncome = sp.getInt("IncomePosition", -1);
        Date = String.valueOf(TxtDatepicker);
        Note = String.valueOf(edtNote);
        if (MoneyComp.equals(Money)) {
            Money = sp.getString("Money", null);
        } else {
            Money = edtMonney.getText().toString();
        }
        if (CheckIncome == -1) {
            Log.d("Money", "=" + CheckIncome);
        } else {
            Category = sp.getString("IncomeName", null);
            Log.d("Name", "=" + Category);
        }
        editor.clear();
        editor.commit();
        switch (item.getItemId()) {
            case R.id.OK:
                //Log.d("Money","="+CheckIncome);
        }
        return super.onOptionsItemSelected(item);
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
}//MainClass
