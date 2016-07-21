package com.croma.app.foodApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class InfoActivity extends AppCompatActivity implements GlobalInterFace, View.OnClickListener {
    private ListView infoLView;
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
    public void onClick(View v) {

    }
}
