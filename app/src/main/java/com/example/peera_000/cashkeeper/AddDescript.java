package com.example.peera_000.cashkeeper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class AddDescript extends Activity {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_descript);
        sp = getSharedPreferences("IncomeData", Context.MODE_PRIVATE);
        editor = sp.edit();

        String str = sp.getString("MoneyIncome", null);
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }
}
