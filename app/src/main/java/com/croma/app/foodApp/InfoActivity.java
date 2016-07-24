package com.croma.app.foodApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InfoActivity extends AppCompatActivity implements GlobalInterFace, AdapterView.OnItemClickListener {
    private ListView infoLView;
    List<String> infoItems;
    private Iterator iterator;

    public static final String[] titles = new String[] { "Partner with us",
            "Terms and Conditions", "Privacy Policy", "How It Works" };

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
