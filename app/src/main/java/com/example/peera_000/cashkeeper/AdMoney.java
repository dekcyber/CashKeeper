package com.example.peera_000.cashkeeper;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;

public class AdMoney extends AppCompatActivity {
    //Explicit
    private Toolbar toolbarAd;
    private EditText edtAdmoney;
    private TabLayout TabAdmoney;
    private ViewPager VPAdmoney;
    private
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_money);
        toolbarAd = (Toolbar) findViewById(R.id.toolbarAdmoney);
        edtAdmoney = (EditText) findViewById(R.id.edtAdmoney);
        TabAdmoney = (TabLayout) findViewById(R.id.TabAdMoney);
        VPAdmoney = (ViewPager) findViewById(R.id.VPAdmoney);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
        edtAdmoney.setTypeface(customFont);
        setSupportActionBar(toolbarAd);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbarAd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        VPaAdmoney = new
        VPAdmoney.setAdapter(VPaAdmoney);
        setSupportActionBar(toolbarAd);

    }
}
