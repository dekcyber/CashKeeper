package com.example.peera_000.cashkeeper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddDescript extends AppCompatActivity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private EditText edtMonney;
    private Toolbar toolbar;
    private TextView TxtDatepicker;
    private int intDate;
    private int intMonth;
    private int intYear;
    private static final int DILOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_descript);
        sp = getSharedPreferences("IncomeData", Context.MODE_PRIVATE);
        editor = sp.edit();
        String str = sp.getString("MoneyIncome", null);
        edtMonney = (EditText) findViewById(R.id.edtAdmoney);
        toolbar = (Toolbar) findViewById(R.id.Toobar_Descrip);
        edtMonney.setText(str);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }//OnCreate

    public void ShowDailog() {
        TxtDatepicker = (TextView) findViewById(R.id.TxtDatePicker);
        TxtDatepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}//MainClass
