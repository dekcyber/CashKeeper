package com.example.peera_000.cashkeeper;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ActionMenuView;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peera_000.cashkeeper.Adapter.BitmapUtility;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;

import java.io.ByteArrayOutputStream;


public class MainActivity extends AppCompatActivity {

    //Explicit
    private CK_TABLE objCK_TABLE;
    private TextView OverV;
    private Toolbar toolbar;
    private ViewPager pager;
    private ViewPagerAdapter adapter;
    private SlidingTabLayout tabs;
    private NavigationView nviewLmain;
    private DrawerLayout navigationL;
    private Drawer navigationR;
    private CharSequence Titles[] = {"OverView", "Graph"};
    private int Numboftabs = 2;
    private SwitchCompat switchPass;
    private OnCheckedChangeListener OnCheckChangeL = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(IDrawerItem drawerItem, CompoundButton buttonView, boolean isChecked) {
            Toast.makeText(MainActivity.this, "onCheckedChanged: " + (isChecked ? "true" : "false"), Toast.LENGTH_SHORT).show();
        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ConnectDB
        ConnectDB();

        //TestAddDB
        //testAddValues();

        // Creating The Toolbar and setting it as the Toolbar for the activity
        OverV = (TextView) findViewById(R.id.TextHead);
        Typeface customFont = Typeface.createFromAsset(getAssets(), "font/paaymaay_regular.ttf");
        OverV.setTextColor(getResources().getColor(R.color.tabsScrollColor));
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
                return getResources().getColor(R.color.tabsScrollColor);
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
                        if (isChecked){
                            Toast.makeText(MainActivity.this, "Click", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MainActivity.this, "UnClick", Toast.LENGTH_SHORT).show();
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

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }//OnCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation_menu, menu);

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

    private void testAddValues() {

        byte[] Barray = BitmapUtility.getBytes(BitmapFactory.decodeResource(getResources(), R.drawable.food));
        objCK_TABLE.addNewValues("31/12/2558", "Food", "Dekcyber", 234.00, Barray);
        Log.d("AddIncome", "AddIncome SuccessFul");
    }//TestAddValues

    private void ConnectDB() {
        objCK_TABLE = new CK_TABLE(this);
    }//ConnectDB


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.peera_000.cashkeeper/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.peera_000.cashkeeper/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}//Main Class
