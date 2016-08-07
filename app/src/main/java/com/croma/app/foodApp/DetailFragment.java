package com.croma.app.foodApp;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.squareup.picasso.Picasso;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment implements GlobalInterFace {
    public static final String placeid = "placeId";
    private static final String TAG = DetailFragment.class.getSimpleName();
    private View mView;
    private TextView foodtitle, foodsubtitle, phoneNumber;
    private ImageView foodresImage;
    private ListView lView;
    SharedPreferences prefs;
    private GoogleMap mMap;
    private ListViewAdapter listViewAdapter;
    public ArrayList<Fooddetail> restaurantListArrayList;
    ProgressDialog progressDialog=null;

    public DetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.detailfragment, container, false);
        setOnClickListener();
        applyFont();
        OnitemSelect();
        return mView;

    }

    @Override
    public void onResume() {
        super.onResume();
        findViewById();
    }


    @Override
    public void findViewById() {
        foodtitle=(TextView)mView.findViewById(R.id.detail_foodtext);
        foodsubtitle=(TextView)mView.findViewById(R.id.detail_foodsubtext);
        foodresImage=(ImageView)mView.findViewById(R.id.detail_image_left);
        phoneNumber=(TextView) mView.findViewById(R.id.contact);
        lView=(ListView)mView.findViewById(R.id.detail_listView_restaurant);
        getRestaurantDetails();
    }


    public void getRestaurantDetails(){
        progressDialog = ProgressDialog.show(this.getContext(), "Please wait", "Fetching Restaurant Details...", true);
        final Bundle b=getArguments();
        final String placeId=b.getString("PlaceItemID");
        String PlaceDetailUrl   =   ServiceConfig.PlaceDetailUrl + "&placeid="+ placeId;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, PlaceDetailUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject=new JSONObject(response).getJSONObject("result");
                            JSONObject jsonObject1=new JSONObject(response).getJSONObject("result").getJSONObject("geometry").getJSONObject("location");
                            String placeAddress=jsonObject.getString("formatted_address");
                            String placeContact=jsonObject.getString("formatted_phone_number");
                            String locationIcon=jsonObject.getString("icon");
                            final String iphone=jsonObject.getString("international_phone_number");
                            String name=jsonObject.getString("name");
                            double placeLatitude=jsonObject1.getDouble("lat");
                            double placelongitude=jsonObject1.getDouble("lng");
                            foodtitle.setText(name);
                            foodsubtitle.setText(placeAddress);
                            Picasso.with(getContext()).load(locationIcon).into(foodresImage);
                            phoneNumber.setText(iphone);
                             phoneNumber.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                                    callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    callIntent.setData(Uri.parse("tel:"+iphone));
                                    startActivity(callIntent);
                                }
                            });
                             }catch (Exception e){
                            Log.e("Exception", "Exception");
                        }
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("REsopnse","error"+error );

                    }
                }){

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(stringRequest);
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



}
