package com.example.peera_000.cashkeeper;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.Adapter.CustomTypefaceSpan;
import com.example.peera_000.cashkeeper.Adapter.NonSwipeableViewPager;
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

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;


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
    private int CountItem = 1;
    private static final int WRITE_External = 3;
    private INCOME_TABLE income_table;
    private OUTCOME_TABLE outcome_table;
    private SwitchCompat switchPass;
    private ImageButton ImgBtt;
    int Valoutcome = 0;
    private TextView NavTxtOutcome;
    private TextView NavTxtIncome;
    private TextView NavTxtTotal;
    private Typeface customFont;
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
        objCK_TABLE = new CK_TABLE(this);
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
        //Create Folder Appg
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED) {
            CreateFolder();
        }else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_External);
            }else {
                CreateFolder();
            }
        }
        //TestAddDB
        //testAddValues();

        // Creating The Toolbar and setting it as the Toolbar for the activity
        OverV = (TextView) findViewById(R.id.TextHead);
        customFont = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
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
        nviewLmain = (NavigationView) findViewById(R.id.NavigationMain_view);

        navigationL = (DrawerLayout) findViewById(R.id.drawerMain);
        final Menu Nmenu = nviewLmain.getMenu();
        for (int i = 0; i < Nmenu.size(); i++) {
            MenuItem mi = Nmenu.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, navigationL, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                nviewLmain.getMenu().clear();
                nviewLmain.inflateMenu(R.menu.navigation_menu);
                Menu Nmenu = nviewLmain.getMenu();
                Fontmenu(Nmenu);

                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                //View headNav = nviewLmain.getHeaderView(0);

                /*View HeadNav = nviewLmain.inflateHeaderView(R.layout.header);
                HeadNav.findViewById(R.id.ImgDropdown).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_SHORT).show();
                    }
                });*/
                CallMoneyNav();
                ImgBtt = (ImageButton) findViewById(R.id.ImgDropdown);
                ImgBtt.setOnClickListener(new View.OnClickListener() {
                    ArrayList<String> budget = new ArrayList<String>();

                    @Override
                    public void onClick(View v) {

                        if (v.isSelected()) {
                            v.setSelected(false);
                            nviewLmain.getMenu().clear();
                            nviewLmain.inflateMenu(R.menu.navigation_menu);
                            Menu Nmenu = nviewLmain.getMenu();
                            Fontmenu(Nmenu);

                        } else {
                            v.setSelected(true);
                            nviewLmain.getMenu().clear();
                            nviewLmain.inflateMenu(R.menu.budget_menu);
                            final Menu Nmenu = nviewLmain.getMenu();
                            //budget.clear();
                            if (budget.size() != 0) {
                                for (int j = 0; j < budget.size(); j++) {
                                    Nmenu.add(budget.get(j));
                                }

                                for (int i = 0; i < Nmenu.size(); i++) {
                                    Nmenu.getItem(i).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                        @Override
                                        public boolean onMenuItemClick(MenuItem item) {
                                            Toast.makeText(getApplicationContext(), "TestClick", Toast.LENGTH_SHORT).show();
                                            return false;
                                        }
                                    });
                                }
                            }

                            MenuItem budgetM = Nmenu.getItem(0);
                            budgetM.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                @Override
                                public boolean onMenuItemClick(MenuItem item) {

                                    Nmenu.add(R.id.budgetgroup, CountItem, Menu.NONE, "Item" + CountItem);
                                    budget.add("Item" + CountItem);
                                    CountItem++;

                                    Fontmenu(Nmenu);
                                    return false;
                                }

                            });
                            Fontmenu(Nmenu);
                        }

                    }
                });
                switchPass = (SwitchCompat) findViewById(R.id.switchPass);

                super.onDrawerOpened(drawerView);
            }
//Setting the actionbarToggle to drawer layout

        };
        navigationL.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //NavigationL



        nviewLmain.setItemIconTintList(null);
        nviewLmain.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                                                         @Override

                                                         public boolean onNavigationItemSelected(MenuItem item) {
                                                             //navigationL.closeDrawers();

                                                             switch (item.getItemId()) {
                                                                 case R.id.Budget:
                                                                     Toast.makeText(MainActivity.this, "Click Budget", Toast.LENGTH_SHORT).show();
                                                                     return true;
                                                                 case R.id.Password:

                                                                     Toast.makeText(MainActivity.this, "Click Budget", Toast.LENGTH_SHORT).show();
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

        id_picture = new ID_Picture(this);
        id_string = new ID_String(this);


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        //client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }//OnCreate
    public void CallMoneyNav(){
        NavTxtOutcome = (TextView) findViewById(R.id.NavTxt_outcome);
        NavTxtIncome = (TextView) findViewById(R.id.NavTxt_income);
        NavTxtTotal = (TextView) findViewById(R.id.NavTxt_Total);
        NavTxtOutcome.setTextColor(getResources().getColor(R.color.Crimson));
        NavTxtIncome.setTextColor(getResources().getColor(R.color.DodgerBlue));
        NavTxtTotal.setTextColor(getResources().getColor(R.color.MediumSeaGreen));
        NavTxtOutcome.setTypeface(customFont);
        NavTxtIncome.setTypeface(customFont);
        NavTxtTotal.setTypeface(customFont);
        Valoutcome = objCK_TABLE.SumOutcomeAll();
        NavTxtOutcome.setText(String.valueOf(Valoutcome));
        NavTxtIncome.setText("5555");
        NavTxtTotal.setText("4305");
    }
    public void CreateFolder(){
            File folder = new File(Environment.getExternalStorageDirectory()+
                    File.separator + "PocketManagement/Picture");
           boolean success = true;
            if (!folder.exists()) {
                success = folder.mkdirs();
                Log.d("Create Folder","Success = "+success);
            }
            if (success) {
                Log.d("Create Folder","Success = "+success);
            } else {
                // Do something else on failure
                Log.d("Create Folder","Not success ="+success);
            }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
       switch (requestCode) {
           case WRITE_External:
               if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
               CreateFolder();
               }else {
                   Toast.makeText(MainActivity.this, "Create Directory Denied", Toast.LENGTH_SHORT)
                           .show();
               }
               break;
           default:
               super.onRequestPermissionsResult(requestCode, permissions, grantResults);

       }

    }

    public Menu Fontmenu(Menu menu){
        Menu Nmenu;
        Nmenu = menu;
        for (int i = 0; i < Nmenu.size(); i++) {
            MenuItem mi = Nmenu.getItem(i);

            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu != null && subMenu.size() > 0) {
                for (int j = 0; j < subMenu.size(); j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
        return menu;
    }//Set font NavMenu

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("", font), 0, mNewTitle.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }

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
