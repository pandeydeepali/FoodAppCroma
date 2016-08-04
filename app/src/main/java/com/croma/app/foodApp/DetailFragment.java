package com.croma.app.foodApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailFragment extends Fragment implements GlobalInterFace {
    private static final String TAG = DetailFragment.class.getSimpleName();
    private View mView;
    private TextView foodtitle, foodsubtitle, foodaddress;
    private ImageView foodresImage;
    private ListView lView;
    SharedPreferences prefs;

    public DetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.detailfragment, container, false);
        findViewById();
        setOnClickListener();
        applyFont();
        OnitemSelect();
        return mView;
    }

    @Override
    public void findViewById() {
        foodtitle=(TextView)mView.findViewById(R.id.detail_foodtext);
        foodsubtitle=(TextView)mView.findViewById(R.id.detail_foodsubtext);
        foodresImage=(ImageView)mView.findViewById(R.id.detail_image_left);
        foodaddress=(TextView) mView.findViewById(R.id.detail_foodaddress);

        Bundle b=getArguments();
        b.getString("ItemName");
        b.getString("ItemSubItem");
        b.getString("ItemImage");
        b.getString("ItemAddress");
      //  b.getString("ItemImage");
        foodtitle.setText(b.getString("ItemName"));
        foodsubtitle.setText(b.getString("ItemSubItem"));
        foodresImage.setImageResource(Integer.valueOf(b.getString("ItemImage")));
        //foodresImage.setImageResource(b.getInt("ItemImage"));
        foodaddress.setText(b.getString("ItemAddress"));



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
