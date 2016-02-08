package com.example.peera_000.cashkeeper.EditRowOverview;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.Adapter.AdapterAdmoney;
import com.example.peera_000.cashkeeper.Adapter.EditAdapterAdmoney;
import com.example.peera_000.cashkeeper.MainCode.AddDescript;
import com.example.peera_000.cashkeeper.R;

public class EditmoneyCate extends AppCompatActivity {
    //Explicit
    private Toolbar toolbarAd;
    private EditText edtAdmoney;
    private TabLayout TabAdmoney;
    private ViewPager VPAdmoney;
    private EditAdapterAdmoney VPaAdmoney;
    private String EdtText;
    private SharedPreferences Edsp;
    private SharedPreferences.Editor edit_Edsp;
    private int CheckTab;
    private Typeface customFont;
    private String strEdtAdmoney;
    private ImageView img;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_money);
        Edsp = getSharedPreferences("EditRowOverData", MODE_PRIVATE);
        edit_Edsp = Edsp.edit();
        customFont = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
        BlindWidget();
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
        TabLayoutAndpager();

    }//OnCreate
    public void TabLayoutAndpager(){
        VPaAdmoney = new EditAdapterAdmoney(getSupportFragmentManager());
        VPAdmoney.setAdapter(VPaAdmoney);

        final TabLayout.Tab income = TabAdmoney.newTab();
        final TabLayout.Tab outcome = TabAdmoney.newTab();

        View IncomeView = getLayoutInflater().inflate(R.layout.custom_tabadmoney, null);
        final TextView TxIn = (TextView) IncomeView.findViewById(R.id.TxtcustomTab);
        TxIn.setTextColor(getResources().getColorStateList(R.color.selector));
        TxIn.setText(getString(R.string.Income));

        TxIn.setTypeface(customFont);

        View OutcomeView = getLayoutInflater().inflate(R.layout.custom_tabadmoney, null);
        final TextView TxOu = (TextView) OutcomeView.findViewById(R.id.TxtcustomTab);
        TxOu.setTextColor(getResources().getColorStateList(R.color.selector));
        TxOu.setText(getString(R.string.Outcome));
        TxOu.setTypeface(customFont);


        income.setCustomView(IncomeView);
        outcome.setCustomView(OutcomeView);

        TabAdmoney.addTab(outcome, 0, true);
        TabAdmoney.addTab(income, 1, false);
        String checkTab = Edsp.getString("CheckCate",null);
        if (checkTab!=null){
            VPAdmoney.setCurrentItem(1);
            income.select();

        }else {
            VPAdmoney.setCurrentItem(0);
            outcome.select();
        }
        TabAdmoney.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));
        TabAdmoney.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                VPAdmoney.setCurrentItem(position);

                if (position == 0) {
                    CheckTab = 1;
                    edit_Edsp.putInt("EditCheckTab", CheckTab);
                    edit_Edsp.commit();
                    TxIn.setTextColor(getResources().getColorStateList(R.color.selector));
                    Log.d("CheckTab", "=" + CheckTab);
                } else if (position == 1) {
                    VPAdmoney.setCurrentItem(position);
                    CheckTab = 2;
                    edit_Edsp.putInt("EditCheckTab", CheckTab);
                    edit_Edsp.commit();
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


    }//TabLayoutAndPager
    public void BlindWidget(){
        toolbarAd = (Toolbar) findViewById(R.id.Toolbar_Admoney);
        edtAdmoney = (EditText) findViewById(R.id.edtAdmoney);
        TabAdmoney = (TabLayout) findViewById(R.id.TabAdMoney);
        VPAdmoney = (ViewPager) findViewById(R.id.VPAdmoney);
        edtAdmoney.setTypeface(customFont);
        strEdtAdmoney = Edsp.getString("IncomeM",null);
        edtAdmoney.setText(strEdtAdmoney);
        img = (ImageView) findViewById(R.id.SelectImg);
        int intImg = Edsp.getInt("Photo",0);
        img.setImageResource(intImg);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_admoney, menu);
        MenuItem item = menu.findItem(R.id.OK);
        item.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        EdtText = edtAdmoney.getText().toString();
        String Rowid = Edsp.getString("EditRowId", null);
        Log.d("Edit","RowId = "+ Rowid);

        int menuId = item.getItemId();
        if (menuId == R.id.AddmoneyNext) {
            EdtText = edtAdmoney.getText().toString();
            edit_Edsp.putString("IncomeMChange", EdtText);
            edit_Edsp.commit();
            Log.d("EditMoney", "Money = " + EdtText);
            Intent in = new Intent(this,EditDescript.class);
            in.putExtra("ID",Rowid);
            startActivity(in);
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostResume() {
        /*edit_Edsp.remove("EditOutcomePhoto");
        edit_Edsp.commit();
        edit_Edsp.remove("EditIncomePhoto");
        edit_Edsp.commit();*/
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


