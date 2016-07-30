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

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.utll.global.ActivitySwitcher;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public static final int REQUEST_TIMEOUT_MS = 10000;
    private ArrayList<geometry> mArrayList;
    private static final String TAG     =       NavigationActivity.class.getSimpleName();

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
        double latitude=28.574473;
        double longitude=77.329135;
        getNearestPlacesDataFromVolleyPlus();



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


    public void getNearestPlacesDataFromVolleyPlus(){
        Log.e("get Apis", ServiceConfig.URL);
        final ListenableFuture<JSONObject> getRestaurantListenable = jsonRequestWithGet(ServiceConfig.URL);
        Futures.addCallback(getRestaurantListenable, new FutureCallback<JSONObject>() {
            @Override
            public void onSuccess(JSONObject result) {
                Log.v(TAG,result.toString());
                mArrayList = new ArrayList<geometry>();
                try{
                    JSONArray jsonArray = result.getJSONArray("results");
                    for(int i = 0 ;i<jsonArray.length();i++){
                        geometry geometry = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),geometry.class);
                        mArrayList.add(geometry);
                        Log.e("mArrayList", ""+mArrayList);
                    }
                    Toast.makeText(NavigationActivity.this,"Data Geted From Service",Toast.LENGTH_SHORT).show();
                    Log.v(TAG,jsonArray.length()+"");
                }catch (Exception e){
                    Toast.makeText(NavigationActivity.this,"There is some problem while getting restarorent",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.v(TAG,t.toString());
            }
        });
    }

    // json request with get
    public static ListenableFuture<JSONObject> jsonRequestWithGet(final String url) {

        final SettableFuture<JSONObject> jsonRequestGetSettable = SettableFuture.create();

        JsonObjectRequest jsonObjectRequestWithGet = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                jsonRequestGetSettable.set(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                jsonRequestGetSettable.setException(error);
                Log.v(TAG, "Exception: "+error);
            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map headerMap = new HashMap();
                return headerMap;
            }
        };
        jsonObjectRequestWithGet.setPriority(Request.Priority.IMMEDIATE);
        jsonObjectRequestWithGet.setShouldCache(false);
        jsonObjectRequestWithGet.setRetryPolicy(new DefaultRetryPolicy(REQUEST_TIMEOUT_MS, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        VolleyApplication.getInstance().getRequestQueue().add(jsonObjectRequestWithGet);
        return jsonRequestGetSettable;
    }



}
