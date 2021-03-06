package com.croma.app.foodApp;
import android.app.ProgressDialog;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.utll.global.ActivitySwitcher;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author supriya.pandey
 *         Navigation drawer activity class
 */
public class NavigationActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private static final long DrawerCloseDelay = 500;
    private final Handler mDrawerActionHandler = new Handler();
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private NavigationView navigationView;
    private static final String TAG     =       NavigationActivity.class.getSimpleName();
    public static final int REQUEST_TIMEOUT_MS = 10000;
    public ArrayList<geometry> mArrayList;
    //----progress dialog
    private ProgressDialog progressDialog = null;
    private TextView NavigationName, NavigationEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationView = (NavigationView) findViewById(R.id.homeNavView);
        NavigationName=(TextView)findViewById(R.id.drawerhomeName);
        NavigationEmail=(TextView)findViewById(R.id.drawerhomeEmail);
        navigationView.getMenu().getItem(1).setChecked(true);
        navigationView.setNavigationItemSelectedListener(this);
       // NavigationName.setText(SharedPrefUtil.getString("Reg_UserName", " ", NavigationActivity.this));
       // NavigationEmail.setText(SharedPrefUtil.getString("Reg_Email", " ", NavigationActivity.this));



        mDrawerLayout = (DrawerLayout) findViewById(R.id.homeDrawer);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
                Log.e("uname", SharedPrefUtil.getString("Reg_UserName", "SuppuPande", NavigationActivity.this));
                Log.e("email", SharedPrefUtil.getString("Reg_Email", "suprpand91@gmail.com", NavigationActivity.this));


            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, new ListDetailActivityFragment(),ListDetailActivityFragment.TAG).commit();
        }
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
    protected void onResume() {
        super.onResume();
        jsonRequestWithGet();


    }


    @Override
    protected void onStop() {
        super.onStop();
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
                SharedPrefUtil.clear(NavigationActivity.this);
                ActivitySwitcher.switchActivityWithHandler(NavigationActivity.this, LoginActivity.class);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
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

    // json request with get
    public  void jsonRequestWithGet() {
        //String latitude =  SharedPrefUtil.getString("CurrentLatitude","", NavigationActivity.this);
        //String longitude  = SharedPrefUtil.getString("CurrentLongitude","", NavigationActivity.this);
        progressDialog = ProgressDialog.show(this, "Please wait", "Fetching Nearest Restaurant...", true);
        double latitude = Double.longBitsToDouble(SharedPrefUtil.getLong("CurrentLatitude", 1L, NavigationActivity.this));
        double longitude = Double.longBitsToDouble(SharedPrefUtil.getLong("CurrentLongitude", 1L, NavigationActivity.this));
        // String my_url   =   ServiceConfig.URL + "&location= +SharedPrefUtil.getFloat("CurrentLatitude", "", NavigationActivity.this)," + "77.329119" + "&type=restaurant";
//        String my_url   =   ServiceConfig.URL + "&location="+ latitude +"," +  longitude + "&type=restaurant";
        String my_url   =   "https://maps.googleapis.com/maps/api/place/search/json?radius=1000&sensor=false&key=AIzaSyBj5jPKrBdVNm72tRWbWsqO4UwThdlXTAo&location=28.574489,77.329119&type=restaurant";
        JsonObjectRequest jsonObjectRequestWithGet = new JsonObjectRequest(my_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mArrayList = new ArrayList<geometry>();
                try{
                    JSONArray jsonArray = response.getJSONArray("results");
                    Log.v(TAG,"Json Array Size"+jsonArray.length());
                    for(int i = 0 ;i<jsonArray.length();i++){
                        geometry geometry = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),geometry.class);
                       // geometry geometry = new Gson().fromJson(jsonArray.getJSONObject(i).toString(), com.croma.app.foodApp.geometry.class);
                        mArrayList.add(geometry);
                    }
                    Log.v(TAG,"Array List Size" + mArrayList.size());
                    final ListDetailActivityFragment fragment = (ListDetailActivityFragment)getSupportFragmentManager().findFragmentByTag(ListDetailActivityFragment.TAG);
                    if(fragment!=null) {
                            runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                fragment.fragmentArrayList.clear();
                                fragment.fragmentArrayList.addAll(mArrayList);
                                fragment.listAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    Toast.makeText(NavigationActivity.this,"Data Get-ed From Service",Toast.LENGTH_SHORT).show();

                }catch (Exception e){
                    Toast.makeText(NavigationActivity.this,"There is some problem while getting restarorent",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

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
        RequestQueue mRequestQueue  = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jsonObjectRequestWithGet);

    }
}
