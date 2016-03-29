package com.example.peera_000.cashkeeper.MainCode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.Adapter.AdapterAdmoney;
import com.example.peera_000.cashkeeper.R;

public class AdMoney extends AppCompatActivity {
    //Explicit
    private Toolbar toolbarAd;
    private EditText edtAdmoney;
    private TabLayout TabAdmoney;
    private ViewPager VPAdmoney;
    private AdapterAdmoney VPaAdmoney;
    private String EdtText;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private SharedPreferences Outsp;
    private SharedPreferences.Editor Outeditor;
    private int CheckTab;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_money);
        sp = getSharedPreferences("IncomeData", Context.MODE_PRIVATE);
        Outsp = getSharedPreferences("OutcomeData", Context.MODE_PRIVATE);
        editor = sp.edit();
        Outeditor = Outsp.edit();
        toolbarAd = (Toolbar) findViewById(R.id.Toolbar_Admoney);
        edtAdmoney = (EditText) findViewById(R.id.edtAdmoney);
        EdtText = edtAdmoney.getText().toString();
        editor.putString("Money", EdtText);
        editor.commit();
        TabAdmoney = (TabLayout) findViewById(R.id.TabAdMoney);
        VPAdmoney = (ViewPager) findViewById(R.id.VPAdmoney);
        VPaAdmoney = new AdapterAdmoney(getSupportFragmentManager());
        VPAdmoney.setAdapter(VPaAdmoney);
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
        /*
        Creating Adapter and setting that adapter to the viewPager
        setSupportActionBar method takes the toolbar and sets it as
        the default action bar thus making the toolbar work like a normal
        action bar.
         */



        /*
        TabLayout.newTab() method creates a tab view, Now a Tab view is not the view
        which is below the tabs, its the tab itself.
         */

        final TabLayout.Tab income = TabAdmoney.newTab();
        final TabLayout.Tab outcome = TabAdmoney.newTab();

        View IncomeView = getLayoutInflater().inflate(R.layout.custom_tabadmoney, null);
        final TextView TxIn = (TextView) IncomeView.findViewById(R.id.TxtcustomTab);
        TxIn.setTextColor(getResources().getColorStateList(R.color.selector));
        TxIn.setText(getString(R.string.Income));

        TxIn.setTypeface(customFont);

        View OutcomeView = getLayoutInflater().inflate(R.layout.custom_tabadmoney, null);
        final TextView TxOu = (TextView) OutcomeView.findViewById(R.id.TxtcustomTab);
        TxOu.setTextColor(getResources().getColorStateList(R.color.White));
        TxOu.setText(getString(R.string.Outcome));
        TxOu.setTypeface(customFont);
        income.isSelected();

        income.setCustomView(IncomeView);
        outcome.setCustomView(OutcomeView);

        TabAdmoney.addTab(outcome, 0, true);
        TabAdmoney.addTab(income, 1, false);


        /*
        Setting Title text for our tabs respectively
         */

        //income.setText("INCOME");
        //outcome.setText("OUTCOME");


        /*
        Adding the tab view to our tablayout at appropriate positions
        As I want home at first position I am passing home and 0 as argument to
        the tablayout and like wise for other tabs as well
         */
        //TabAdmoney.addTab(income, 0);
        //TabAdmoney.addTab(outcome, 1);


        /*
        TabTextColor sets the color for the title of the tabs, passing a ColorStateList here makes
        tab change colors in different situations such as selected, active, inactive etc

        TabIndicatorColor sets the color for the indiactor below the tabs
         */


        TabAdmoney.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));
        TabAdmoney.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();

                VPAdmoney.setCurrentItem(tab.getPosition());

                if (position == 0) {
                    CheckTab = 1;
                    editor.putInt("CheckTab", CheckTab);

                    TxIn.setTextColor(getResources().getColorStateList(R.color.selector));
                    Log.d("CheckTab", "=" + CheckTab);
                } else if (position == 1) {
                    CheckTab = 2;
                    editor.putInt("CheckTab", CheckTab);
                    TxOu.setTextColor(getResources().getColorStateList(R.color.selector));
                    Log.d("CheckTab", "=" + CheckTab);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        /*
        Adding a onPageChangeListener to the viewPager
        1st we add the PageChangeListener and pass a TabLayoutPageChangeListener so that Tabs Selection
        changes when a viewpager page changes.
         */

        VPAdmoney.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(TabAdmoney));
        VPAdmoney.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }//OnCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_admoney, menu);
        MenuItem item = menu.findItem(R.id.OK);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        edtAdmoney = (EditText) findViewById(R.id.edtAdmoney);
        EdtText = edtAdmoney.getText().toString();
        editor.putString("Money", EdtText);
        editor.commit();
        int menuId = item.getItemId();
        if (menuId == R.id.AddmoneyNext) {
            if (EdtText.equals("")) {
                Toast.makeText(getApplicationContext(), "กรุณากรอกจำนวนเงิน", Toast.LENGTH_SHORT).show();
            } else {
                Intent In = new Intent(AdMoney.this, AddDescript.class);
                startActivity(In);
            }
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        Outeditor.remove("OutcomePhoto");
        Outeditor.commit();
        /*editor.remove("IncomePhoto");
        editor.commit();*/
        super.onPostResume();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    public static String POSITION = "POSITION";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(POSITION, TabAdmoney.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        VPAdmoney.setCurrentItem(savedInstanceState.getInt(POSITION));
    }
}//MainClass


