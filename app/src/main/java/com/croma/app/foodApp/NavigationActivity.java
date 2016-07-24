package com.croma.app.foodApp;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.utll.global.ActivitySwitcher;

import java.util.ArrayList;

/**
 * @author supriya.pandey
 *         Navigation drawer activity class
 */
public class NavigationActivity extends AppCompatActivity implements GlobalInterFace, View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {
    private static final long DrawerCloseDelay = 500;
    private final Handler mDrawerActionHandler = new Handler();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.homeNavView);
        navigationView.getMenu().getItem(1).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.homeDrawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, new ListDetailActivityFragment()).commit();

        }

    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.homeDrawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home_list_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_logout: {
                SharedPreferences preferences = getSharedPreferences(GlobalsharedPreference.loginsharedPREFERENCES, getApplicationContext().MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.commit();
                ActivitySwitcher.switchActivityWithHandler(NavigationActivity.this, LoginActivity.class);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setOnClickListener() {

    }

    @Override
    public void applyFont() {

    }

    @Override
    public void OnitemSelect() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    public boolean onNavigationItemSelected(final MenuItem item) {
        item.setCheckable(true);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.homeDrawer);
        drawer.closeDrawer(GravityCompat.START);
        mDrawerActionHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (item.getItemId()) {
                    case R.id.homedrawerLocation: {
                        ActivitySwitcher.switchActivityWithHandler(NavigationActivity.this, AddressActivity.class);
                        break;
                    }
                    case R.id.homedrawerRestaurant: {
                        ActivitySwitcher.switchActivityWithHandler(NavigationActivity.this, NavigationActivity.class);
                        break;
                    }
                    case R.id.homedrawerDeal: {
                        break;
                    }
                    case R.id.homedrawerOrder: {
                        break;
                    }
                    case R.id.homedrawerAccount: {
                        ActivitySwitcher.switchActivityWithHandler(NavigationActivity.this, AccountActivity.class);
                        break;
                    }
                    case R.id.homedrawerAddress: {
                        break;
                    }
                    case R.id.homedrawerSettigs: {
                        ActivitySwitcher.switchActivityWithHandler(NavigationActivity.this, SettingsActivity.class);
                        break;
                    }
                    case R.id.homeDrawerInfo: {
                        ActivitySwitcher.switchActivityWithHandler(NavigationActivity.this, InfoActivity.class);
                        break;
                    }
                }
            }
        }, DrawerCloseDelay);
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}
