package com.example.peera_000.cashkeeper;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.Adapter.ViewPagerAdapter;
import com.example.peera_000.cashkeeper.Convert_ID.ID_Picture;
import com.example.peera_000.cashkeeper.Convert_ID.ID_String;
import com.example.peera_000.cashkeeper.Database.CK_TABLE;
import com.example.peera_000.cashkeeper.Database.INCOME_TABLE;
import com.example.peera_000.cashkeeper.Database.OUTCOME_TABLE;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;


public class MainActivity extends AppCompatActivity {

    //Explicit

    private CK_TABLE objCK_TABLE;
    private ID_Picture id_picture;
    private ID_String id_string;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    private TextView OverV;
    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private NavigationView nviewLmain;
    private DrawerLayout navigationL;
    private Drawer navigationR;
    public String tab1;
    public String tab2;
    private int Numboftabs;
    private int Count = 0;
    private INCOME_TABLE income_table;
    private OUTCOME_TABLE outcome_table;
    private SwitchCompat switchPass;
    private OnCheckedChangeListener OnCheckChangeL = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(MainActivity.this, "onCheckedChanged: " + (isChecked ? "true" : "false"), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        income_table = new INCOME_TABLE(this);
        outcome_table = new OUTCOME_TABLE(this);
        sp = getSharedPreferences("CheckCount", MODE_PRIVATE);
        editor = sp.edit();
        tab1 = getString(R.string.Overview);
        tab2 = getString(R.string.Graph);
        CharSequence Titles[] = {tab1, tab2};
        Numboftabs = 2;
        //ConnectDB
        //ConnectDB();
        int srtdata = getResources().getIdentifier("Bill", "strings", getPackageName());
        Log.d("StringData", "=" + srtdata);
        //int count =sp.getInt("Count",0)+Count++;
        if (income_table.CheckIncome()) {
            income_table.InsertIncome();
            outcome_table.InsertOutcome();
        }

        //TestAddDB
        //testAddValues();

        // Creating The Toolbar and setting it as the Toolbar for the activity
        OverV = (TextView) findViewById(R.id.TextHead);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
        OverV.setTextColor(getResources().getColor(R.color.White));
        OverV.setTypeface(customFont);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(false);*/


        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
        //ActionBarToggle
        navigationL = (DrawerLayout) findViewById(R.id.drawerMain);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, navigationL, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                switchPass = (SwitchCompat) findViewById(R.id.switchPass);
                switchPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String Language;
                        if (isChecked) {

                               /*Language = "th";
                            Locale locale = new Locale(Language);
                            Locale.setDefault(locale);
                            Configuration config = new Configuration();
                            config.locale = locale;
                            getBaseContext().getResources().updateConfiguration(config,
                                    getBaseContext().getResources().getDisplayMetrics());
                            this.setContentView(R.layout.activity_main,R.menu.navigation_menu);*/


                        } else {

                        }
                    }
                });


                super.onDrawerOpened(drawerView);
            }
//Setting the actionbarToggle to drawer layout

        };
        navigationL.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //NavigationL
        nviewLmain = (NavigationView) findViewById(R.id.NavigationMain_view);
        nviewLmain.setItemIconTintList(null);
        nviewLmain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                                                         @Override

                                                         public boolean onNavigationItemSelected(MenuItem item) {
                                                             if (item.isChecked())
                                                                 item.setChecked(false);
                                                             else item.setChecked(true);

                                                             //navigationL.closeDrawers();

                                                             switch (item.getItemId()) {
                                                                 case R.id.Budget:
                                                                     Toast.makeText(MainActivity.this, "Click Budget", Toast.LENGTH_SHORT).show();
                                                                     return true;
                                                                 case R.id.Password:

                                                                     return true;
                                                                 //case R.id.Language:
                                                                     //Intent Int = new Intent(getApplicationContext(),LanguageChange.class);
                                                                     //startActivity(Int);

                                                             }


                                                             return false;
                                                         }


                                                     }


        );


        //EndOfNavigationL

        navigationR = new DrawerBuilder()
                .withActivity(this)
                        //.withToolbar(toolbar)
                .withActionBarDrawerToggleAnimated(true)
                .withDrawerGravity(Gravity.END)
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(-1)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(MainActivity.this, "onItemClick:" + position, Toast.LENGTH_LONG).show();
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int position, IDrawerItem drawerItem) {
                        Toast.makeText(MainActivity.this, "onItemLongClick:" + position, Toast.LENGTH_LONG).show();
                        return false;
                    }
                })
                .addDrawerItems(new SwitchDrawerItem().withName("Limit").withIcon(R.drawable.picconfig).withChecked(false).withOnCheckedChangeListener(new OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
                                if (isChecked == true) {
                                }

                            }
                        })

                )
                .build();
        id_picture = new ID_Picture(this);
        id_string = new ID_String(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }//OnCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}//Main Class
