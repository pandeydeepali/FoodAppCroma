package com.croma.app.foodApp;

import android.content.Context;
import android.location.LocationManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by suppi on 16/07/16.
 */
public class Locate extends Fragment implements GlobalInterFace, View.OnClickListener {
    private Button locateMe;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_welcome_pager_two, container, false);
        locateMe = (Button) v.findViewById(R.id.locateBtn);
        setOnClickListener();
        return v;
    }

    @Override
    public void findViewById() {

    }

    @Override
    public void setOnClickListener() {
        locateMe.setOnClickListener(this);

    }

    @Override
    public void applyFont() {

    }

    @Override
    public void OnitemSelect() {

    }




    @Override
    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.locateBtn: {
                    checkGPSProviderStatus();
                    break;
                }
            }
    }

    private void checkGPSProviderStatus(){
        GpsCheck gpsCheck=new GpsCheck();
        gpsCheck.gpsStatus(getContext());

    }
}
