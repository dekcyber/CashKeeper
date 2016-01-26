package com.example.peera_000.cashkeeper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class AddDescript extends AppCompatActivity {
    //Explicit
    private SharedPreferences sp;
    private SharedPreferences Outsp;
    private SharedPreferences.Editor Outeditor;
    private SharedPreferences.Editor editor;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_descript);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
        sp = getSharedPreferences("IncomeData", Context.MODE_PRIVATE);
        Outsp = getSharedPreferences("OutcomeData", Context.MODE_PRIVATE);
        editor = sp.edit();
        Outeditor = Outsp.edit();
        Money = sp.getString("Money", null);
        img = (ImageView) findViewById(R.id.SelectImg);
        //int photoOut = Outsp.getInt("OutcomePhoto", -1);
        int photoIn = sp.getInt("IncomePhoto", -1);
        Log.d("Photo", "=" + photoIn);
            img.setImageResource(photoIn);
            imgPhoto = photoIn;

        edtMonney = (EditText) findViewById(R.id.edtAdmoney);
        edtMonney.setTypeface(customFont);
        toolbar = (Toolbar) findViewById(R.id.Toobar_Descrip);
        TxtDatepicker = (TextView) findViewById(R.id.TxtDatePicker);
        edtNote = (EditText) findViewById(R.id.edtNote);
        edtNote.setTypeface(customFont);
        edtMonney.setText(Money);
        MoneyComp = edtMonney.getText().toString();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                Log.d("Note", "=" + Note);
            }
        });
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

            }
        };

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
        Note = edtNote.getText().toString();
        Date = TxtDatepicker.getText().toString();
        Money = edtMonney.getText().toString();
        String strImgPhoto = Integer.toString(imgPhoto);
        int CheckIncome = sp.getInt("IncomePosition", -1);
        int CheckTab = sp.getInt("CheckTab", 0);

        ck_table = new CK_TABLE(getApplicationContext());
        if (MoneyComp.equals(Money)) {
            Money = sp.getString("Money", null);
            Log.d("Money", "=" + Money);
        }
        if (CheckIncome == -1) {
            Category = Outsp.getString("OutcomeName", null);
            Nameid = Outsp.getString("OutcomeNameId", null);
            Log.d("Name", "=" + Category);
        } else {
            Category = sp.getString("IncomeName", null);
            Nameid = sp.getString("IncomeNameId", null);
            Log.d("Name", "=" + Category);
        }
        douMoney = Double.parseDouble(Money);
        Log.d("CheckTab", "=" + CheckTab);
        editor.clear();
        editor.commit();
        switch (item.getItemId()) {
            case R.id.OK:

                if (CheckTab == 2) {
                    ck_table.addNewValuesIncome(Date, Category, Nameid, "Dekcyber", Note, douMoney, strImgPhoto);
                    Intent In = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(In);
                    Log.d("Date", "=" + Date);
                    Log.d("Note", "=" + Note);
                    Log.d("photo", "=" + imgPhoto);
                    Log.d("CheckTab", "=" + CheckTab);
                } else if (CheckTab == 0) {
                    ck_table.addNewValuesOutcome(Date, Category, Nameid, "Dekcyber", Note, douMoney, strImgPhoto);
                    Intent In = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(In);
                    Log.d("Date", "=" + Date);
                    Log.d("Note", "=" + Note);
                    Log.d("photo", "=" + imgPhoto);
                    Log.d("CheckTab", "=" + CheckTab);

                }

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

    @Override
    protected void onPostResume() {

        super.onPostResume();
    }

    @Override
    protected void onPause() {
        Outeditor.remove("OutcomePhoto");
        Outeditor.commit();
        editor.remove("IncomePhoto");
        editor.commit();
        editor.remove("CheckTab");
        editor.commit();
        Outeditor.remove("OutcomeNameId");
        Outeditor.commit();
        editor.remove("IncomeNameId");
        editor.commit();
        super.onPause();
    }
}//MainClass
