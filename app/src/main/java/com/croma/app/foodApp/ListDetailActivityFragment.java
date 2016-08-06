package com.croma.app.foodApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A placeholder fragment containing a simple view.
 */
public class ListDetailActivityFragment extends Fragment implements GlobalInterFace, View.OnClickListener {

    public static final String TAG = ListDetailActivityFragment.class.getSimpleName();
    public static final int REQUEST_TIMEOUT_MS = 10000;
    public ArrayList<geometry> fragmentArrayList;
    public ListViewAdapter listAdapter;
    private View mView;
    private ListView listView;
    public ArrayList<geometry> mArrayList;

    //-----from navigationActivity-----//

    public ListDetailActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.content_list_detail, container, false);
        findViewById();
        setOnClickListener();
        applyFont();
        OnitemSelect();
        return mView;
    }

    @Override
    public void findViewById() {
        listView = (ListView) mView.findViewById(R.id.detail_listView);
        fragmentArrayList = new ArrayList<>();
        //  listAdapter = new ListViewAdapter((((NavigationActivity)getActivity())), arrayList, ListDetailActivityFragment.this);
        listAdapter = new ListViewAdapter(ListDetailActivityFragment.this, fragmentArrayList, (((NavigationActivity) getActivity())));
        listView.setAdapter(listAdapter);
        fillArrayList();
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
    public void onResume() {
        Log.e("OnResume", "OnResue");
        super.onResume();
        jsonRequestWithGet();
    }

    public void fillArrayList() {
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id._relativeLayout: {
                if (v.getTag() != null) {
                    Integer integer = Integer.parseInt(v.getTag().toString());
                    geometry foodItem = fragmentArrayList.get(integer);
                    Bundle b = new Bundle();
                    b.putString("ItemName", foodItem.name);
                    b.putString("ItemSubItem", foodItem.name);
                    b.putString("ItemAddress", foodItem.vicinity);
                    b.putString("ItemImage", foodItem.icon);
                    b.putString("PlaceItemID", foodItem.place_id);
                    DetailFragment detailFragment = new DetailFragment();
                    detailFragment.setArguments(b);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.home_fragment, detailFragment).addToBackStack(null).commit();
                    // startActivity(b);
                    break;
                }
            }
        }
    }

    // json request with get
    public  void jsonRequestWithGet() {
        double latitude = Double.longBitsToDouble(SharedPrefUtil.getLong("CurrentLatitude", 1L, getActivity()));
        double longitude = Double.longBitsToDouble(SharedPrefUtil.getLong("CurrentLongitude", 1L, getActivity()));
        String my_url   =   ServiceConfig.URL + "&location="+ latitude +"," +  longitude + "&type=restaurant";
        JsonObjectRequest jsonObjectRequestWithGet = new JsonObjectRequest(my_url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                mArrayList = new ArrayList<geometry>();
                try{
                    JSONArray jsonArray = response.getJSONArray("results");
                    for(int i = 0 ;i<jsonArray.length();i++){
                        geometry geometry = new Gson().fromJson(jsonArray.getJSONObject(i).toString(),geometry.class);
                        mArrayList.add(geometry);

                    }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                fragmentArrayList.clear();
                                fragmentArrayList.addAll(mArrayList);
                                listAdapter.notifyDataSetChanged();
                            }
                        });

                }catch (Exception e){
                    Toast.makeText(getActivity(),"There is some problem while getting restarorent",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                Toast.makeText( getActivity(),"Data Get-ed From Service",Toast.LENGTH_SHORT).show();

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
        RequestQueue mRequestQueue  = Volley.newRequestQueue(getActivity().getApplicationContext());
        mRequestQueue.add(jsonObjectRequestWithGet);

    }


}
