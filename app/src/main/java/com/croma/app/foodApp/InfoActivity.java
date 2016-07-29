package com.croma.app.foodApp;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoActivity extends AppCompatActivity implements GlobalInterFace, View.OnClickListener {
    private ListView infoLView;
    private ImageView backImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        findViewById();
        setOnClickListener();
    }

    @Override
    public void findViewById() {
        infoLView=(ListView)findViewById(R.id.infoList);
        backImg=(ImageView)findViewById(R.id.infoBack);

    }

    @Override
    public void setOnClickListener() {
        backImg.setOnClickListener(this);

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
            case R.id.infoBack: {
                backActivity();
                break;
            }
        }

    }

    private void backActivity(){
        finish();

    }
}
