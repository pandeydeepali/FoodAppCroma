package com.croma.app.foodApp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity implements GlobalInterFace, AdapterView.OnItemClickListener {
    private ListView infoLView;
    List<InfoItem> infoItems;

    public static final String[] titles = new String[] { "Partner with us",
            "Terms and Conditions", "Privacy Policy", "How It Works" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        findViewById();
        setOnClickListener();
        infoItems=new ArrayList<InfoItem>();
        for (int i = 0; i < titles.length; i++) {
            InfoItem item = new InfoItem( titles[i]);
            infoItems.add(item);
        }
    }

    @Override
    public void findViewById() {
        infoLView=(ListView)findViewById(R.id.infoList);
        InfoAdapter adapter = new InfoAdapter(this, infoItems);
        infoLView.setAdapter(adapter);
        infoLView.setOnItemClickListener(this);




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
